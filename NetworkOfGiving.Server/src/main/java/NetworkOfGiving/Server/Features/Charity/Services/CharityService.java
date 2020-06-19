package NetworkOfGiving.Server.Features.Charity.Services;

import NetworkOfGiving.Server.Data.Charity;
import NetworkOfGiving.Server.Data.Donation;
import NetworkOfGiving.Server.Data.Participant;
import NetworkOfGiving.Server.Data.User;
import NetworkOfGiving.Server.Features.Charity.CharityRepository;
import NetworkOfGiving.Server.Features.Charity.Models.GET.CharityGetResponseModel;
import NetworkOfGiving.Server.Features.Donation.DonationRepository;
import NetworkOfGiving.Server.Features.User.UserRepository;
import NetworkOfGiving.Server.Features.Participant.ParticipantRepository;
import NetworkOfGiving.Server.Infrastucture.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
@Service
public class CharityService {
    @Autowired
    private CharityRepository charityRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private JWTUtil jwtUtil;

    public Charity getCharityById(int id){
        return charityRepository.findById(id).orElse(null);
    }

    public List<Charity> getAllCharities(){
        return charityRepository.findAll();
    }

    public Charity saveCharity(Charity charity){
        return charityRepository.save(charity);
    }

    public ResponseEntity<String> deleteCharityById(int id, User user){

        if (charityRepository.deleteByIdAndUser(id,user) != 1) {
            return new ResponseEntity<>("You don't have permission to do that", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Successful deletion of charity.", HttpStatus.NO_CONTENT);
    }

    public Charity findCharityByIdAndUser(int id, User user){
        return charityRepository.findByIdAndUser(id, user);
    }

    public Blob parseToBlob(MultipartFile file) throws IOException, SQLException {
        return new SerialBlob(file.getBytes());
    }

    public Charity updateCharityById(Charity charity, String description, BigDecimal budgetRequired, Integer numberOfParticipants) {
        if (description != null) {
            charity.setDescription(description);
        }

        if (budgetRequired != null) {
            if (charity.getBudgetRequired().compareTo(budgetRequired) > 0 && charity.getDonatedMoney().compareTo(budgetRequired) > 0) {
                BigDecimal difference = budgetRequired.subtract(charity.getBudgetRequired());

                List<Donation> currentDonations = donationRepository.findByCharityId(charity.getId());

                for (int i = currentDonations.size() - 1; i >= 0; i--) {
                    if (difference.compareTo(new BigDecimal(0)) < 0 && charity.getDonatedMoney().compareTo(budgetRequired) > 0) {
                        Donation donation = currentDonations.get(i);
                        difference = difference.add(donation.getAmount());
                        donationRepository.deleteById(donation.getId());
                        charity.setDonatedMoney(charity.getDonatedMoney().subtract(donation.getAmount()));
                        currentDonations.remove(donation);
                    }
                }

            }
            charity.setBudgetRequired(budgetRequired);
        }

        if (numberOfParticipants != null) {
            if (charity.getNumberOfParticipants() > numberOfParticipants) {
                List<Participant> currentParticipants = participantRepository.findByCharityId(charity.getId());
                for (int i = numberOfParticipants; i < currentParticipants.size(); i++) {
                    participantRepository.deleteById(currentParticipants.get(i).getId());
                }
            }
            charity.setNumberOfParticipants(numberOfParticipants);
        }

        return saveCharity(charity);
    }

    public Charity getCharityByName(String name) {
        return charityRepository.findByName(name);
    }

    public CharityGetResponseModel getResponse(Charity charity) {
        return new CharityGetResponseModel(
                charity.getId(),
                charity.getUser().getUsername(),
                charity.getUser().getName(),
                charity.getName(),
                charity.getDescription(),
                charity.getBudgetRequired(),
                charity.getDonatedMoney(),
                charity.getNumberOfParticipants(),
                charity.getParticipants().size(),
                charity.getThumbnail());
    }
}

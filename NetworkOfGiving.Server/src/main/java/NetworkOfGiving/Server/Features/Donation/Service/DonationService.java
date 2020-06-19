package NetworkOfGiving.Server.Features.Donation.Service;

import NetworkOfGiving.Server.Data.Charity;
import NetworkOfGiving.Server.Data.Donation;
import NetworkOfGiving.Server.Features.Charity.CharityRepository;
import NetworkOfGiving.Server.Features.Donation.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private CharityRepository charityRepository;

    public List<Donation> findByCharityId(int id){
        return donationRepository.findByCharityId(id);
    }
    public Donation donate(Donation d){
        charityRepository.findById(d.getCharity().getId());
        return donationRepository.save(d);
    }

    public BigDecimal getSuggestion(int charityId, int userId) {
        final BigDecimal tenDollarDonation = new BigDecimal(10);
        List<Donation> donations = donationRepository.findByUserId(userId);
        Charity charity = charityRepository.findById(charityId).orElse(null);

        if (charity == null) throw new NotFoundException("Charity not found");

        BigDecimal neededMoney = charity.getBudgetRequired().subtract(charity.getDonatedMoney());

        if (donations == null) {
            if (neededMoney.compareTo(tenDollarDonation) < 0) {
                return neededMoney;
            }
            else {
                return tenDollarDonation;
            }
        }

        BigDecimal averageDonations = new BigDecimal(0);

        for (Donation donation : donations) {
            averageDonations = averageDonations.add(donation.getAmount());
        }

        averageDonations = averageDonations.divide(BigDecimal.valueOf(donations.size()));

        if (neededMoney.compareTo(averageDonations) >= 0) {
            return averageDonations;
        }
        else {
            return neededMoney;
        }
    }
}

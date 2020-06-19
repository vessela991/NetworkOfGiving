package NetworkOfGiving.Server.Features.User.Service;

import NetworkOfGiving.Server.Data.Donation;
import NetworkOfGiving.Server.Data.Participant;
import NetworkOfGiving.Server.Data.User;
import NetworkOfGiving.Server.Features.Donation.DonationRepository;
import NetworkOfGiving.Server.Features.Participant.ParticipantRepository;
import NetworkOfGiving.Server.Features.User.Service.Models.ActivityModel;
import NetworkOfGiving.Server.Features.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public ActivityModel getActivity(int userId) {
        List<Donation> donations = donationRepository.findByUserId(userId);
        List<Participant> participants = participantRepository.findByUserId(userId);

        return new ActivityModel(donations, participants);
    }

    public User getUser(int userId) {
        return userRepository.findById(userId).orElse(null);
    }
}

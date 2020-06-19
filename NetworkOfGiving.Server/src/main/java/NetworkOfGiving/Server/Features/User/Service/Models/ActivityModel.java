package NetworkOfGiving.Server.Features.User.Service.Models;

import NetworkOfGiving.Server.Data.Donation;
import NetworkOfGiving.Server.Data.Participant;

import java.util.List;

public class ActivityModel {
    public List<Donation> donations;
    public List<Participant> participants;

    public ActivityModel(List<Donation> donations, List<Participant> participants) {
        this.donations = donations;
        this.participants = participants;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}

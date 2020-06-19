package NetworkOfGiving.Server.Features.Participant.Models;

public class ParticipantPostRequestModel {
    private int charityId;

    public ParticipantPostRequestModel(int charityId) {
        this.charityId = charityId;
    }

    public int getCharityId() {
        return charityId;
    }

    public void setCharityId(int charityId) {
        this.charityId = charityId;
    }
}

package NetworkOfGiving.Server.Features.Donation.Models;

public class SuggestionRequestModel {

    private int id;

    public SuggestionRequestModel() {
    }

    public SuggestionRequestModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

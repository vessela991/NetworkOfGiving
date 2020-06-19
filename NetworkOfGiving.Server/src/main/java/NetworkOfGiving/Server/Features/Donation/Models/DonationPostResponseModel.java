package NetworkOfGiving.Server.Features.Donation.Models;

import NetworkOfGiving.Server.Data.Charity;
import NetworkOfGiving.Server.Data.User;

import java.math.BigDecimal;

public class DonationPostResponseModel{
    private BigDecimal amount;
    private int charityId;

    public DonationPostResponseModel(BigDecimal amount, int charityId) {
        this.amount = amount;
        this.charityId = charityId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getCharityId() {
        return charityId;
    }

    public void setCharityId(int charityId) {
        this.charityId = charityId;
    }
}

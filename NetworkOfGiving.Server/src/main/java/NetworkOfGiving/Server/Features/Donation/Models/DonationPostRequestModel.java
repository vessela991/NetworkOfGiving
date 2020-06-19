package NetworkOfGiving.Server.Features.Donation.Models;

import NetworkOfGiving.Server.Data.Charity;
import NetworkOfGiving.Server.Infrastucture.Properties.ValidationConstraints;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class DonationPostRequestModel {
    @Min(ValidationConstraints.MIN_VALUE_DONATION)
    private String amount;
    private Integer charityId;

    public DonationPostRequestModel(String amount, Integer charityId) {
        this.amount = amount;
        this.charityId = charityId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getCharityId() {
        return charityId;
    }

    public void setCharityId(Integer charityId) {
        this.charityId = charityId;
    }
}

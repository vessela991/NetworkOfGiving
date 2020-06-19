package NetworkOfGiving.Server.Features.Charity.Models.PUT;

import NetworkOfGiving.Server.Features.Charity.Models.BaseModels.BaseCharityModel;
import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;

public class CharityPutRequestModel {

    @Size(min = 5, max = 300)
    private String description;
    @Min(value = 1)
    private Integer numberOfParticipants;
    @Min(value = 1)
    private BigDecimal budgetRequired;

    public CharityPutRequestModel(@Size(min = 5, max = 300) String description, Integer numberOfParticipants, BigDecimal budgetRequired) {
        this.description = description;
        this.numberOfParticipants = numberOfParticipants;
        this.budgetRequired = budgetRequired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public BigDecimal getBudgetRequired() {
        return budgetRequired;
    }

    public void setBudgetRequired(BigDecimal budgetRequired) {
        this.budgetRequired = budgetRequired;
    }
}

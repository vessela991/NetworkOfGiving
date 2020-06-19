package NetworkOfGiving.Server.Features.Charity.Models.BaseModels;

import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

public class BaseCharityModel {
    @Size(min = 6, max = 30)
    private String name;
    @Size(min = 6, max = 300)
    private String description;
    private Integer numberOfParticipants;
    private BigDecimal budgetRequired;

    public BaseCharityModel(String name, String description, Integer numberOfParticipants, BigDecimal budgetRequired) {
        this.name = name;
        this.description = description;
        this.numberOfParticipants = numberOfParticipants;
        this.budgetRequired = budgetRequired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

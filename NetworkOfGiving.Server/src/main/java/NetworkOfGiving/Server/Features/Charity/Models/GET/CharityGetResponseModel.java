package NetworkOfGiving.Server.Features.Charity.Models.GET;


import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Base64;

public class CharityGetResponseModel {
    private int id;
    private String username;
    private String userFullname;
    private String name;
    private String description;
    private BigDecimal budgetRequired;
    private BigDecimal donatedMoney;
    private Integer numberOfParticipants;
    private Integer participants;
    private String thumbnail;

    public CharityGetResponseModel(int id,String username, String userFullname, String name, String description, BigDecimal budgetRequired, BigDecimal donatedMoney, Integer numberOfParticipants, Integer participants, Blob thumbnail) {
        this.id = id;
        this.username = username;
        this.userFullname = userFullname;
        this.name = name;
        this.description = description;
        this.budgetRequired = budgetRequired;
        this.donatedMoney = donatedMoney;
        this.numberOfParticipants = numberOfParticipants;
        this.participants = participants;
        this.thumbnail = encodeBlob(thumbnail);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String encodeBlob(Blob thumbnail) {
        try {
            return new String(Base64.getEncoder().encode(thumbnail.getBytes(1, (int) thumbnail.length())));
        }catch(Exception e){
            return "Encoding failed. " + e.getMessage();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
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

    public BigDecimal getBudgetRequired() {
        return budgetRequired;
    }

    public void setBudgetRequired(BigDecimal budgetRequired) {
        this.budgetRequired = budgetRequired;
    }

    public BigDecimal getDonatedMoney() {
        return donatedMoney;
    }

    public void setDonatedMoney(BigDecimal donatedMoney) {
        this.donatedMoney = donatedMoney;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}


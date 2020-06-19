package NetworkOfGiving.Server.Data;

import NetworkOfGiving.Server.Features.Charity.Models.POST.CharityCreateRequestModel;
import NetworkOfGiving.Server.Infrastucture.Properties.ValidationConstraints;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;
@Entity
@Table(name="charity")
public class Charity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(nullable = false)
    @Size(min = ValidationConstraints.MIN_LENGTH_CHARITY_NAME, max = ValidationConstraints.MAX_LENGTH_CHARITY_NAME)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Blob thumbnail;

    @NotNull
    @Column(nullable = false)
    @Size(min = ValidationConstraints.MIN_LENGTH_DESCRIPTION, max = ValidationConstraints.MAX_LENGTH_DESCRIPTION)
    private String description;

    @Min(ValidationConstraints.MIN_VALUE_PARTICIPANTS)
    private Integer maxNumberOfParticipants;

    @Min(ValidationConstraints.MIN_VALUE_BUDGET)
    private BigDecimal budgetRequired;

    @NotNull
    @Column(nullable = false)
    private BigDecimal donatedMoney= new BigDecimal(0);

    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId", nullable = false)
    private User user;

    @OneToMany(mappedBy="charity",cascade = CascadeType.ALL)
    //@JoinColumn(name="id", nullable = false)
    private List<Participant> participants;

    @OneToMany(mappedBy="charity",cascade = CascadeType.ALL)
    //@JoinColumn(name="id", nullable = false)
    private List<Donation> donations;

    public void setId(int id) {
        this.id = id;
    }

    public Charity(String name, Blob thumbnail, String description, Integer maxNumberOfParticipants, BigDecimal budgetRequired, User user) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.maxNumberOfParticipants = maxNumberOfParticipants;
        this.budgetRequired = budgetRequired;
        this.user = user;
    }

    public Charity(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Blob thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfParticipants() {
        return maxNumberOfParticipants;
    }

    public void setNumberOfParticipants(Integer maxNumberOfParticipants) {
        this.maxNumberOfParticipants = maxNumberOfParticipants;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

}

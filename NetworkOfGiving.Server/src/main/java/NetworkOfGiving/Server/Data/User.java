package NetworkOfGiving.Server.Data;

import NetworkOfGiving.Server.Infrastucture.Properties.ValidationConstraints;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(nullable = false)
    @Size(min = ValidationConstraints.MIN_LENGTH_USERNAME, max = ValidationConstraints.MAX_LENGTH_USERNAME)
    private String username;

    @NotNull
    @Column(nullable = false)
    @Size(min = ValidationConstraints.MIN_LENGTH_PASSWORD, max = ValidationConstraints.MAX_LENGTH_PASSWORD)
    private String password;

    @NotNull
    @Column(nullable = false)
    @Size(min = ValidationConstraints.MIN_LENGTH_NAME, max = ValidationConstraints.MAX_LENGTH_NAME)
    private String name;

    @NotNull
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private Gender gender;

    private String location;
    @Column(nullable = false)
    private String roles;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Donation> donations;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Charity> charities;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Participant> participatedCharities;

    public User(){

    }

    public User(String username, String password, String name, int age, Gender gender, String location, String roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<String> getRolesList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<Charity> getCharities() {
        return charities;
    }

    public void setCharities(List<Charity> charities) {
        this.charities = charities;
    }

    public List<Participant> getParticipatedCharities() {
        return participatedCharities;
    }

    public void setParticipatedCharities(List<Participant> participatedCharities) {
        this.participatedCharities = participatedCharities;
    }

    public void setId(int userId) {
        this.id = userId;
    }
}

package NetworkOfGiving.Server.Features.Identity.Models.Register;

import NetworkOfGiving.Server.Data.Gender;
import org.springframework.lang.Nullable;

public class RegisterRequestModel {
    private String username;
    private String password;
    private String name;
    private int age;
    private Gender gender;
    private String location;

    public RegisterRequestModel(String username, String password, String name, int age, Gender gender, String location) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
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
}

package NetworkOfGiving.Server.Features.User;

import NetworkOfGiving.Server.Data.Gender;

public class GetUserResponseModel {
    private String username;
    private String name;
    private int age;
    private Gender gender;
    private String location;

    public GetUserResponseModel(String username , String name, int age, Gender gender, String location) {
        this.username = username;
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

package NetworkOfGiving.Server.Features.Identity.Models.Register;

import NetworkOfGiving.Server.Data.Gender;

public class RegisterResponseModel extends RegisterRequestModel {
    private String roles;
    public RegisterResponseModel(String username, String password, String name, int age, Gender gender, String location, String roles) {
        super(username, password, name, age, gender, location);
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }

}

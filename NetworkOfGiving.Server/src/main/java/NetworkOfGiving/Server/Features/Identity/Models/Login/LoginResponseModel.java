package NetworkOfGiving.Server.Features.Identity.Models.Login;

import lombok.Getter;

public class LoginResponseModel {
    public LoginResponseModel(String jwt) {
        this.jwt = jwt;
    }

    private final String jwt;
    public String getJwt() {
        return jwt;
    }
}

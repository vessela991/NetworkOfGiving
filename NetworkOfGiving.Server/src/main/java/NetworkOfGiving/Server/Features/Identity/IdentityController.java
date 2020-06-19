package NetworkOfGiving.Server.Features.Identity;


import NetworkOfGiving.Server.Data.User;
import NetworkOfGiving.Server.Features.Identity.Models.Login.LoginRequestModel;
import NetworkOfGiving.Server.Features.Identity.Models.Login.LoginResponseModel;
import NetworkOfGiving.Server.Features.Identity.Models.Register.RegisterRequestModel;
import NetworkOfGiving.Server.Features.Identity.Models.Register.RegisterResponseModel;
import NetworkOfGiving.Server.Features.Identity.Service.IdentityService;
import NetworkOfGiving.Server.Infrastucture.Properties.ExceptionMessage;
import NetworkOfGiving.Server.Infrastucture.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identity")
public class IdentityController {

    @Autowired
    private IdentityService identityService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequestModel model) throws Exception {
        String jwt;
        try{
            jwt = identityService.login(model.getUsername(),model.getPassword());
        }catch(Exception e){
            return new ResponseEntity<>(ExceptionMessage.BAD_CREDENTIALS,HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<LoginResponseModel>(new LoginResponseModel(jwt), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequestModel model) {
        User user = identityService.saveUser(new User(model.getUsername(), model.getPassword(), model.getName(), model.getAge(), model.getGender(), model.getLocation(), "USER"));
        if (user == null) {
            return new ResponseEntity<String>("Username already taken.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new RegisterResponseModel(user.getUsername(), user.getPassword(), user.getName(), user.getAge(), user.getGender(), user.getLocation(), user.getRoles()), HttpStatus.CREATED);
    }
}

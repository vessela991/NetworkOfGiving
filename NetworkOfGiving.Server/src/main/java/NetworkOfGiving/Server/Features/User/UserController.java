package NetworkOfGiving.Server.Features.User;

import NetworkOfGiving.Server.Data.User;
import NetworkOfGiving.Server.Features.Identity.Service.IdentityService;
import NetworkOfGiving.Server.Features.User.Service.Models.ActivityModel;
import NetworkOfGiving.Server.Features.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private IdentityService identityService;

    @GetMapping("/activity")
    public ResponseEntity<?> activity(@RequestHeader("Authorization") String token) {
        ActivityModel model = userService.getActivity(identityService.getUserId(token));
        return new ResponseEntity<>(model , HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token) {
        User user = userService.getUser(identityService.getUserId(token));
        if (user == null) {
            new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new GetUserResponseModel(user.getUsername(), user.getName(), user.getAge(),user.getGender(), user.getLocation()), HttpStatus.OK);
    }
}

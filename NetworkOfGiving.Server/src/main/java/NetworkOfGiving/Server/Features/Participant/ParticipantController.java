package NetworkOfGiving.Server.Features.Participant;

import NetworkOfGiving.Server.Data.Charity;
import NetworkOfGiving.Server.Data.Participant;
import NetworkOfGiving.Server.Data.User;
import NetworkOfGiving.Server.Features.Charity.Services.CharityService;
import NetworkOfGiving.Server.Features.Identity.Service.IdentityService;
import NetworkOfGiving.Server.Features.Participant.Models.ParticipantPostRequestModel;
import NetworkOfGiving.Server.Features.Participant.Service.ParticipantService;
import NetworkOfGiving.Server.Features.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participant")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private UserService userService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private CharityService charityService;

    @PostMapping
    public ResponseEntity<?> participateInCharity(@ModelAttribute ParticipantPostRequestModel model, @RequestHeader("Authorization") String token){
        User user = userService.findById(identityService.getUserId(token));
        Charity charity = charityService.getCharityById(model.getCharityId());
        if(participantService.getParticipantsByCharityId(model.getCharityId()).size()>=charity.getNumberOfParticipants()){
            return new ResponseEntity<String>("Max number of participants reached, cannot participate", HttpStatus.BAD_REQUEST);
        }
        participantService.participate(new Participant(user,charity));
        return new ResponseEntity<>("Participation in charity successful", HttpStatus.OK);
    }
}

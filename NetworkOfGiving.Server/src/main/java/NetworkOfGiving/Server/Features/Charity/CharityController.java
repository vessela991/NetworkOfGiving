package NetworkOfGiving.Server.Features.Charity;

import NetworkOfGiving.Server.Data.Charity;
import NetworkOfGiving.Server.Data.User;
import NetworkOfGiving.Server.Features.Charity.Models.POST.CharityCreateRequestModel;
import NetworkOfGiving.Server.Features.Charity.Models.POST.CharityCreateResponseModel;
import NetworkOfGiving.Server.Features.Charity.Models.GET.CharityGetResponseModel;
import NetworkOfGiving.Server.Features.Charity.Models.PUT.CharityPutRequestModel;
import NetworkOfGiving.Server.Features.Charity.Services.CharityService;
import NetworkOfGiving.Server.Features.Identity.Service.IdentityService;
import NetworkOfGiving.Server.Features.Participant.Service.ParticipantService;
import NetworkOfGiving.Server.Features.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/charity")
@CrossOrigin(origins = "http://localhost:4200")
public class CharityController {

    @Autowired
    private CharityService charityService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private UserService userService;

    @GetMapping()
    @RequestMapping("/{id}")
    public ResponseEntity<?> getCharityById(@PathVariable int id) {
        Charity charity = charityService.getCharityById(id);
        if (charity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(charityService.getResponse(charity), HttpStatus.OK);
    }

    @GetMapping()
    @RequestMapping("/search/{name}")
    public ResponseEntity<?> getCharityByName(@PathVariable String name) {
        Charity charity = charityService.getCharityByName(name);
        if (charity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(charityService.getResponse(charity), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CharityGetResponseModel>> getAllCharities() {
        List<Charity> charities = charityService.getAllCharities();
        List<CharityGetResponseModel> response = new ArrayList<>();
        for (Charity charity : charities) {
            response.add(charityService.getResponse(charity));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> createCharity(@ModelAttribute CharityCreateRequestModel model, @RequestHeader("Authorization") String token) throws IOException, SQLException {
        if (model.getBudgetRequired() == null && model.getNumberOfParticipants() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int userId = identityService.getUserId(token);

        User user = userService.findById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Charity charity = charityService.saveCharity(new Charity(model.getName(), charityService.parseToBlob(model.getThumbnail()), model.getDescription(), model.getNumberOfParticipants(), model.getBudgetRequired(), user));
        return new ResponseEntity<>(new CharityCreateResponseModel(charity.getId(),charity.getName(), charity.getDescription(), charity.getNumberOfParticipants(), charity.getBudgetRequired(), charity.getThumbnail()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharity(@PathVariable int id, @RequestHeader("Authorization") String token) {
        int userId = identityService.getUserId(token);
        User user = userService.findById(userId);

        return charityService.deleteCharityById(id, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCharity(@PathVariable("id") int id, @ModelAttribute CharityPutRequestModel model, @RequestHeader("Authorization") String token) {
        int userId = identityService.getUserId(token);

        User user = userService.findById(userId);

        Charity charity = charityService.findCharityByIdAndUser(id, user);

        if (charity == null) {
            return new ResponseEntity<>("You don't have permission to do that", HttpStatus.FORBIDDEN);
        }

        Charity c = charityService.updateCharityById(charity, model.getDescription(), model.getBudgetRequired(), model.getNumberOfParticipants());
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }
}
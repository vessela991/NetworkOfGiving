package NetworkOfGiving.Server.Features.Donation;

import NetworkOfGiving.Server.Data.Charity;
import NetworkOfGiving.Server.Data.Donation;
import NetworkOfGiving.Server.Data.User;
import NetworkOfGiving.Server.Features.Charity.Services.CharityService;
import NetworkOfGiving.Server.Features.Donation.Models.DonationPostRequestModel;
import NetworkOfGiving.Server.Features.Donation.Models.DonationPostResponseModel;
import NetworkOfGiving.Server.Features.Donation.Models.SuggestionRequestModel;
import NetworkOfGiving.Server.Features.Donation.Service.DonationService;
import NetworkOfGiving.Server.Features.Identity.Service.IdentityService;
import NetworkOfGiving.Server.Features.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/donation")
public class DonationController {
    @Autowired
    private DonationService donationService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private CharityService charityService;
    @Autowired
    private UserService userService;


@PostMapping
public ResponseEntity<?> makeDonation(@RequestBody DonationPostRequestModel model, @RequestHeader("Authorization") String token){
    BigDecimal money = new BigDecimal(model.getAmount().replaceAll(",", ""));

    int userId = identityService.getUserId(token);
    User user = userService.findById(userId);

    if(user== null){
        return new ResponseEntity<>("First you have to login, your session has expired",HttpStatus.BAD_REQUEST);
    }

    Charity charity = charityService.getCharityById(model.getCharityId());

    if(charity==null){
        return new ResponseEntity<String>("Charity does not exist.", HttpStatus.NOT_FOUND);
    }

    if(charity.getDonatedMoney().compareTo(charity.getBudgetRequired())>=0){
        return new ResponseEntity<String>("Charity required money already collected, choose another charity.", HttpStatus.BAD_REQUEST);
    }

    if((charity.getDonatedMoney().add(money)).compareTo(charity.getBudgetRequired())==1){
        money= (charity.getBudgetRequired().subtract(charity.getDonatedMoney()));
    }
    Donation donation = donationService.donate(new Donation(money,user,charityService.getCharityById(model.getCharityId())));
    charity.setDonatedMoney(charity.getDonatedMoney().add(money));
    if(charity.getDonatedMoney().compareTo(charity.getBudgetRequired())==1){
        charity.setDonatedMoney(charity.getBudgetRequired());
    }
    charityService.saveCharity(charity);
    return new ResponseEntity<>(new DonationPostResponseModel(donation.getAmount(),donation.getCharity().getId()),HttpStatus.OK);
}

    @PostMapping("/suggestion")
    public ResponseEntity<?> suggestion(@RequestBody SuggestionRequestModel model, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(donationService.getSuggestion(model.getId(),identityService.getUserId(token)), HttpStatus.OK);
    }
}

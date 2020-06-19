package NetworkOfGiving.Server.Features.Charity.Models.POST;

import NetworkOfGiving.Server.Features.Charity.Models.BaseModels.BaseResponseCharityModel;

import java.math.BigDecimal;
import java.sql.Blob;


public class CharityCreateResponseModel extends BaseResponseCharityModel {

    private int id;

    public CharityCreateResponseModel(int id,String name, String description, Integer maxNumberOfParticipants, BigDecimal budgetRequired, Blob thumbnail) {
        super(name, description, maxNumberOfParticipants, budgetRequired, thumbnail);
            this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
}

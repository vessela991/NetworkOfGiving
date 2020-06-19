package NetworkOfGiving.Server.Features.Charity.Models.BaseModels;

import NetworkOfGiving.Server.Features.Charity.Models.BaseModels.BaseCharityModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Base64;

public class BaseResponseCharityModel extends BaseCharityModel {
    private String thumbnail;

    public BaseResponseCharityModel(String name, String description, Integer numberOfParticipants, BigDecimal budgetRequired, Blob thumbnail) {
        super(name, description, numberOfParticipants, budgetRequired);
        this.thumbnail=encodeBlob(thumbnail);
    }

    public String encodeBlob(Blob thumbnail) {
        try {
            return new String(Base64.getEncoder().encode(thumbnail.getBytes(1, (int) thumbnail.length())));
        }catch(Exception e){
            return "Encoding failed. " + e.getMessage();
        }
    }
}

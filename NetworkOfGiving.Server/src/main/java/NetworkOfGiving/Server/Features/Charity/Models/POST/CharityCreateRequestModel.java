package NetworkOfGiving.Server.Features.Charity.Models.POST;

import NetworkOfGiving.Server.Features.Charity.Models.BaseModels.BaseCharityModel;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.math.BigDecimal;
public class CharityCreateRequestModel extends BaseCharityModel {
    private MultipartFile thumbnail;

    public CharityCreateRequestModel(String name, String description, Integer numberOfParticipants, BigDecimal budgetRequired, MultipartFile thumbnail) {
        super(name, description, numberOfParticipants, budgetRequired);
        this.thumbnail=thumbnail;
    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }

}

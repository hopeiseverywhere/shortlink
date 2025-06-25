package com.fran.shortlink.admin.remote.dto.req;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 * Short Link Update Request DTO
 */
@Data
public class ShortLinkUpdateReqDTO {


    private String originUrl;
    private String fullShortUrl;
    /**
     * Group identifier
     */
    private String gid;


    /**
     * Validity Type: 0 = Permanent, 1 = Custom
     */
    private Integer validDateType;
    /**
     * Expiration Date
     */
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm:ss a", timezone = "PST")
    private Date validDate;
    private String description;
}

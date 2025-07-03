package com.fran.shortlink.project.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 * Short Link Creation Request DTO
 */
@Data
public class ShortLinkCreateReqDTO {

    private String domainProtocol;
    private String domain;

    private String originUrl;
    /**
     * Group identifier
     */
    private String gid;

    /**
     * Creation Type: 0 = Console, 1 = API
     */
    private Integer createType;

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

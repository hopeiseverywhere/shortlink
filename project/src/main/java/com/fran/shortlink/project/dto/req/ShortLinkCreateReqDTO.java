package com.fran.shortlink.project.dto.req;

import java.util.Date;
import lombok.Data;

/**
 * Short Link Creation Request DTO
 */
@Data
public class ShortLinkCreateReqDTO {

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
    private Date validDate;
    private String description;
}

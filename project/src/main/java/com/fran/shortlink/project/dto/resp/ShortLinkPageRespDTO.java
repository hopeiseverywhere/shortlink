package com.fran.shortlink.project.dto.resp;

import java.util.Date;
import lombok.Data;

/**
 * Short Link page DTO
 */
@Data
public class ShortLinkPageRespDTO {

    /**
     * id
     */
    private Long id;
    private String domain;
    private String shortUri;

    private String fullShortUrl;
    private String originUrl;
    /**
     * Group identifier
     */
    private String gid;

    /**
     * Web icon
     */
    private String favicon;
    /**
     * Validity Type: 0 = Permanent, 1 = Custom
     */
    private Integer validDateType;
    /**
     * Expiration Date
     */
    private Date validDate;


}

package com.fran.shortlink.admin.remote.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm:ss a", timezone = "PST")
    private Date validDate;
    /**
     * Creation Time
     */

    @JsonFormat(pattern = "MM-dd-yyyy hh:mm:ss a", timezone = "PST")
    private Date createTime;

}

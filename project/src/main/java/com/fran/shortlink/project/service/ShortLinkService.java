package com.fran.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fran.shortlink.project.dao.entity.ShortLinkDO;
import com.fran.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.fran.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.fran.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.google.protobuf.ServiceException;
import java.util.List;

/**
 * Short Link Service
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * Create Short Link
     *
     * @param requestParam params for creating a short link
     * @return short link creation info
     */
    ShortLinkCreateRespDTO createShortUrl(ShortLinkCreateReqDTO requestParam)
        throws ServiceException;

    /**
     * Update Short Link
     *
     * @param requestParam params for update shor link
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * Query Short Link by Page
     *
     * @param requestParam params for query short link by page
     * @return short link query result by page
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * Query Short Link Count under a group
     *
     * @param requestParam a list of gids
     * @return a list of response dto
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);


}

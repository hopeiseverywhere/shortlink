package com.fran.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fran.shortlink.project.dao.entity.ShortLinkDO;
import com.fran.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.google.protobuf.ServiceException;

/**
 * Short Link Service
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * Create Short Link
     * @param requestParam params for creating a short link
     * @return short link creation info
     */
    ShortLinkCreateRespDTO createShortUrl(ShortLinkCreateReqDTO requestParam)
        throws ServiceException;
}

package com.fran.shortlink.admin.remote.dto;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fran.shortlink.admin.common.convention.result.Result;
import com.fran.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.fran.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.fran.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.fran.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.fran.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Short Link Project Remote Access Service
 */
public interface ShortLinkRemoteService {

    /**
     * Create Short Link
     */
    default Result<ShortLinkCreateRespDTO> createShortUrl(ShortLinkCreateReqDTO requestParam) {
        String resultBodyStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create",
            JSON.toJSONString(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {

        });
    }

    /**
     * Query Short Link by Page
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gid", requestParam.getGid());
        requestMap.put("current", requestParam.getCurrent());
        requestMap.put("size", requestParam.getSize());
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page",
            requestMap);
        return JSON.parseObject(resultPageStr,
            new TypeReference<>() {

            });
    }

    /**
     * Query Short Link Count under a group
     */
    default Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(List<String> requestParam) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("requestParam", requestParam);
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/count",
            requestMap);
        return JSON.parseObject(resultPageStr,
            new TypeReference<>() {

            });
    }

}

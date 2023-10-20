package com.muxin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name ="${public.cloud.service.name.rgas-adapter:bsfit-graph-adapter}",
        path = "${public.cloud.service.path.rgas-adapter:}",
        url = "${public.cloud.service.url.rgas-adapter:}",
        contextId="GDBDriverServiceApi")
public interface GDBDriverServiceApi {

    String PATH_PREFIX = "/graph/driver";

    @RequestMapping(value = PATH_PREFIX+"/info", method = RequestMethod.GET)
    BsfitGraphResponse<Map> getDriverInfo(@RequestParam(value = "clusterCode") String clusterCode);

}

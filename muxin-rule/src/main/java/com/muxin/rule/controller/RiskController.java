package com.muxin.rule.controller;

import com.muxin.rule.domain.DynamicContext;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/risk")
public class RiskController {

    @Autowired
    private FlowExecutor flowExecutor;

    @PostMapping("/check")
    public Map<String, Object> checkRisk(@RequestBody Map<String, Object> body) {
        DynamicContext context = new DynamicContext();
        context.setData(body);

        LiteflowResponse response = flowExecutor.execute2Resp("riskChain", null, context);

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("success", response.isSuccess());
        stringObjectHashMap.put("riskFlag", context.isRisk());
        stringObjectHashMap.put("originalData", body);
        return stringObjectHashMap;
    }
}

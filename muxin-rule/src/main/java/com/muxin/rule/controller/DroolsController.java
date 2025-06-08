package com.muxin.rule.controller;

import com.muxin.rule.domain.DynamicFact;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rules")
public class DroolsController {

    private final KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();

    @PostMapping("/execute")
    public String executeRules(@RequestBody Map<String, Object> data) throws Exception {
        DynamicFact fact = new DynamicFact(data);
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        kieSession.insert(fact);
        int count = kieSession.fireAllRules();
        kieSession.dispose();
        return "规则命中数：" + count + "\n" + fact.getFields();
    }
}

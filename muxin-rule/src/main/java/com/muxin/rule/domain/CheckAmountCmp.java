package com.muxin.rule.domain;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @projectname: muxin
 * @filename: CheckAmountCmp
 * @author: yangxz
 * @data:2025/6/8 17:20
 * @description:
 */
@Component("checkAmount")
public class CheckAmountCmp extends NodeComponent {
    @Override
    public void process() {
        DynamicContext context = this.getFirstContextBean();
        Object amountObj = context.get("amount");
        if (amountObj != null && Integer.parseInt(amountObj.toString()) > 10000) {
            context.markRisk();
            System.out.println("⚠ 金额超限");
        }
    }
}

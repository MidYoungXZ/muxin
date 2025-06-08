package com.muxin.rule.domain;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @projectname: muxin
 * @filename: CheckUserTypeCmp
 * @author: yangxz
 * @data:2025/6/8 17:20
 * @description:
 */
@Component("checkUserType")
public class CheckUserTypeCmp extends NodeComponent {
    @Override
    public void process() {
        DynamicContext context = this.getFirstContextBean();
        if ("black".equalsIgnoreCase(String.valueOf(context.get("userType")))) {
            context.markRisk();
            System.out.println("⚠ 用户类型为黑名单");
        }
    }
}

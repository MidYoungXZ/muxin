package com.muxin.rule.domain;

import java.util.Map;

public class DynamicContext {
    private Map<String, Object> data;
    private boolean riskFlag = false;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Object get(String key) {
        return data.get(key);
    }

    public void markRisk() {
        this.riskFlag = true;
    }

    public boolean isRisk() {
        return riskFlag;
    }
}

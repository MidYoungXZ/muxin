package com.muxin.rule.domain;

import java.util.HashMap;
import java.util.Map;

public class DynamicFact {
    private final Map<String, Object> fields = new HashMap<>();

    public DynamicFact() {
    }

    public DynamicFact(Map<String, Object> data) {
        this.fields.putAll(data);
    }

    public void set(String key, Object value) {
        fields.put(key, value);
    }

    public Object get(String key) {
        return fields.get(key);
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return fields.toString();
    }
}

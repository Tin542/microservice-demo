package com.tinnt.validation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "validation")
public class ValidationProperties {

    private Map<String, String> mandatoryFields;

    public Map<String, String> getMandatoryFields() {
        return mandatoryFields;
    }

    public void setMandatoryFields(Map<String, String> mandatoryFields) {
        this.mandatoryFields = mandatoryFields;
    }

    public List<String> getFieldsForType(String type) {
        String fields = mandatoryFields.get(type);
        return fields != null ? Arrays.asList(fields.split(",")) : List.of();
    }
}

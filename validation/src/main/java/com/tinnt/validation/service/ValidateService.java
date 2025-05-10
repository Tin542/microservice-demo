package com.tinnt.validation.service;

import com.tinnt.validation.config.ValidationProperties;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class ValidateService {
    private final ValidationProperties properties;

    public ValidateService(ValidationProperties properties) {
        this.properties = properties;
    }

    public List<String> validateMandatoryFields (Object obj, String type, String pathPrefix) {
        List<String> error = new ArrayList<>();
        List<String> tmp = properties.getFieldsForType(type);
        for (String fieldName : tmp) {
            try {
                Field field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value == null || (value instanceof String s && s.isBlank())) {
                    error.add(pathPrefix + "." + fieldName + " is required");
                }
            } catch (Exception e) {
                error.add(pathPrefix + "." + fieldName + " is invalid or not found");
            }
        }
        return error;
    }
}

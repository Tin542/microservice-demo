package com.tinnt.validation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnt.validation.model.UserModel;
import com.tinnt.validation.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/validate")
public class ValidateController {
    @Autowired
    private ValidateService validateService;

    @PostMapping("/{type}")
    public ResponseEntity<List<String>> validateObject(
            @PathVariable String type,
            @RequestBody Object data) {

        List<String> errors = new ArrayList<>();

        String pathPrefix = type;

        // Use Jackson to convert LinkedHashMap to a typed object
        Object convertedObj = convertToTypedObject(data, type);

        if (convertedObj != null) {
            errors.addAll(validateService.validateMandatoryFields(convertedObj, type, pathPrefix));
        } else {
            errors.add("Unsupported type: " + type);
        }

        return ResponseEntity.ok(errors);
    }

    // Helper method to convert raw Object into specific class based on "type"
    private Object convertToTypedObject(Object raw, String type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if ("user".equals(type)) {
                return mapper.convertValue(raw, UserModel.class);
            }
            // add other types here
        } catch (IllegalArgumentException e) {
            return null;
        }
        return null;
    }
}

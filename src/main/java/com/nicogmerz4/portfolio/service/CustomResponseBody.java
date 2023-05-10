package com.nicogmerz4.portfolio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomResponseBody {
    private List data;
    private Map errors;
    private String message;
    
    public CustomResponseBody() {
        data = new ArrayList();
        errors = new HashMap<String, Map<String, String>>();
        message = "";
    }
    
    public void addData(Object obj) {
        data.add(obj);
    }
}

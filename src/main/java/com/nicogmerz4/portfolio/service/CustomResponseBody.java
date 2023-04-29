package com.nicogmerz4.portfolio.service;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomResponseBody {
    private List data;
    private String message;
    
    public CustomResponseBody() {
        data = new ArrayList();
        message = "";
    }
    
    public void addData(Object obj) {
        data.add(obj);
    }
}

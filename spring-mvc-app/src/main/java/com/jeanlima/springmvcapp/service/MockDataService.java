package com.jeanlima.springmvcapp.service;

import org.springframework.stereotype.Component;

@Component
public class MockDataService {

    private final String[] linguagens = {
        "Java", "JavaScript", "C", "Python"
    };
    private final String[] sistemasOperacionais = {
        "OSX", "Windows", "Linux"
    };
    public String[] getLinguagens() {
        return linguagens;
    }
    public String[] getSistemasOperacionais() {
        return sistemasOperacionais;
    }

    
    
}

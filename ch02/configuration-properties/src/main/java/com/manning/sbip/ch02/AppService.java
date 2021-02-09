package com.manning.sbip.ch02;

import com.manning.sbip.ch02.configurationproperties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    private AppProperties appProperties;

    public AppProperties getAppProperties() {
        return this.appProperties;
    }
}

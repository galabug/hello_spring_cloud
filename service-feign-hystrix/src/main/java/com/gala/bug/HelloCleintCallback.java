package com.gala.bug;

import org.springframework.stereotype.Component;

@Component
public class HelloCleintCallback implements HelloCleint {

    public String sayHello(String name) {
        return "sorry HelloCleintCallback.sayHello "+name;
    }
}

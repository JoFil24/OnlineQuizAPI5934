package org.example.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyProperties {
    private static String APIKey = "123";

    public String getAPIKey(){
        return APIKey;
    }
}

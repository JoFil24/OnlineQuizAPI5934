package org.example.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyProperties {
    private static String APIKey = "e1c11dc9-9f1b-4d13-b3a1-a7f00ea39aca";

    public String getAPIKey(){
        return APIKey;
    }
}

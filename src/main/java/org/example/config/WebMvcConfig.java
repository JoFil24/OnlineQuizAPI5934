package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final ApiKeyInterceptor apiKeyInterceptor;

    public WebMvcConfig(ApiKeyInterceptor apiKeyInterceptor) {
        this.apiKeyInterceptor = apiKeyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(apiKeyInterceptor)
                .addPathPatterns("/questions/**")
                .addPathPatterns("/quizzes/**")
                .addPathPatterns("/submissions/**")
                .addPathPatterns("/students/**")
                .addPathPatterns("/answers/**")
                .addPathPatterns("/submissions/**")


                .excludePathPatterns("/h2-console/**")
                .excludePathPatterns("/courses-admin/**");


    }
}

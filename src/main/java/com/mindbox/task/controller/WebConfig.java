package com.mindbox.task.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");

    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer ) {
        configurer
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .defaultContentType(MediaType.APPLICATION_JSON).mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("text/html", MediaType.TEXT_HTML)
                .mediaType("x-yaml", MEDIA_TYPE_YML);
    }
}

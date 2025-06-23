package com.example.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("DEBUG: Conversores de mensagem antes da modificação:");
        converters.forEach(c -> System.out.println("  - " + c.getClass().getName() + " suporta: " + c.getSupportedMediaTypes()));

        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

        jsonConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("application", "json", StandardCharsets.UTF_8),
                new MediaType("application", "json")
        ));

        converters.add(0, jsonConverter);

        System.out.println("DEBUG: Conversores de mensagem APÓS a modificação:");
        converters.forEach(c -> System.out.println("  - " + c.getClass().getName() + " suporta: " + c.getSupportedMediaTypes()));
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .favorPathExtension(false)
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(true);
    }
}

package com.mindbox.task.controller.service;

import com.mindbox.task.models.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class ProjectService {

    private final RestTemplate restTemplate;

    public ProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(cacheNames = "landscape-projects")
    public List<Project> getProjects(final String URL) {
        log.info("Getting data and saving to cache");
        return restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Project>>(){})
                .getBody();
    }
}

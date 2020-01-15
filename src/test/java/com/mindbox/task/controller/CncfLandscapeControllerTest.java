package com.mindbox.task.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindbox.task.controller.service.ProjectService;
import com.mindbox.task.models.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CncfLandscapeControllerTest {

    private static final String URL = "https://landscape.cncf.io/data.json";

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private CncfLandscapeController controller;

    @Test
    void test() throws IOException {
        final List<Project> projects = getProjectsFromJson();

        when(projectService.getProjects(URL)).thenReturn(projects);

        assertFalse(controller.getAll().isEmpty());
    }

    private List<Project> getProjectsFromJson() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final List<Project> projects = mapper.readValue(new File("src/test/resources/projects.json"),
                new TypeReference<List<Project>>(){});
        return projects;
    }
}
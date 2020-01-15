package com.mindbox.task.controller;

import com.mindbox.task.controller.service.ProjectService;
import com.mindbox.task.models.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/landscape")
public class CncfLandscapeController {

    private static final String URL = "https://landscape.cncf.io/data.json";

    private final ProjectService service;

    public CncfLandscapeController(ProjectService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json", "application/x-yaml"})
    public @ResponseBody List<Project> getAll() {
        return service.getProjects(URL);
    }

    @GetMapping(produces = {"text/html"})
    public String showAll(Model model) {
        model.addAttribute("projects", service.getProjects(URL));
        return "projects";
    }
}

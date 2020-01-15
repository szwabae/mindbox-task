package com.mindbox.task.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project implements Serializable {

    private static final long serialVersionUID = -7868828206845061019L;

    private String name;
    @JsonProperty("homepage_url") private String homepageUrl;
    @JsonProperty("repo_url") private String repoUrl;
    private String twitter;
    private String crunchbase;
    private String contributorsLink;
    private int stars;
    private CrunchbaseData crunchbaseData;
    private String landscape;
}

package com.mindbox.task.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class CrunchbaseData implements Serializable {

    private static final long serialVersionUID = -7868828206845061019L;

    private String city;
    private String country;
    private String linkedin;
}

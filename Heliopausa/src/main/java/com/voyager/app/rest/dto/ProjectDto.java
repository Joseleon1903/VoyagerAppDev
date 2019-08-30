package com.voyager.app.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by jose on 8/29/2019.
 */
@Data
public class ProjectDto {

    private String projectName;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String[] userManager = new String[0];

    private String[] userMembers = new String[0];


}

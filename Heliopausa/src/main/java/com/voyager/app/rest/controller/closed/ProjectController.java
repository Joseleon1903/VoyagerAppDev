package com.voyager.app.rest.controller.closed;

import com.voyager.app.rest.dto.ProjectDto;
import com.voyager.app.security.auth.JwtAuthenticationToken;
import com.voyager.app.security.model.UserContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    @PostMapping("/creation")
    public ResponseEntity creationProject(@RequestBody ProjectDto projectDto) {


        return ResponseEntity.ok().build();
    }


}

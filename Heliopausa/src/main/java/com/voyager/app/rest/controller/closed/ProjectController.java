package com.voyager.app.rest.controller.closed;

import com.voyager.app.common.GenericErrorResponse;
import com.voyager.app.constant.ConstantApplication;
import com.voyager.app.entity.Project;
import com.voyager.app.entity.User;
import com.voyager.app.rest.dto.ProjectDto;
import com.voyager.app.service.impl.ProjectServiceImpl;
import com.voyager.app.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private static Logger log = LogManager.getLogger(ProfileEndpoint.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProjectServiceImpl projectService;

    @PostMapping("/creation")
    public ResponseEntity creationProject(@RequestBody ProjectDto projectDto, BindingResult bindingResult) {
        log.info("entering method creationProject");
        if (bindingResult.hasErrors()){
            GenericErrorResponse reponse = new  GenericErrorResponse();
            reponse.setErrorCode(ConstantApplication.REQUIRED_FIELD_ERROR_CODE);
            reponse.setMessage("Existing field required");
            reponse.setStatus(HttpStatus.BAD_REQUEST);
            reponse.setTimestamp(new Date());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
        }
        Project projectEntity = new Project();
        projectEntity.setName(projectDto.getProjectName());
        projectEntity.setTitle(projectDto.getTitle());
        projectEntity.setDescription(projectDto.getDescription());
        projectEntity.setCreationDate(new Date());
        projectEntity.setOpen(true);
        User userEntity = null;

        //validation user name projectManger
        Iterable<User> listadoUsuarios = userService.findAllUsers();
        List<String> usersSystem = new ArrayList<>();
        listadoUsuarios.forEach(join -> usersSystem.add(join.getUsername()));
        if(projectDto.getUserManager().length > 0){
            for (String name:projectDto.getUserManager()) {
                if(!usersSystem.contains(name)){
                    GenericErrorResponse reponse = new  GenericErrorResponse();
                    reponse.setErrorCode(ConstantApplication.INVALID_USER_ERROR_CODE);
                    reponse.setMessage("User in Manager User don't exist in the system");
                    reponse.setStatus(HttpStatus.BAD_REQUEST);
                    reponse.setTimestamp(new Date());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponse);
                }
                userEntity = userService.findByUsername(name).get();
                projectEntity.getUserManagements().add(userEntity);
            }
        }
        //validation user name member list
        if(projectDto.getUserMembers().length > 0){
            for (String name:projectDto.getUserMembers()) {
                if(!usersSystem.contains(name)){
                    GenericErrorResponse response = new  GenericErrorResponse();
                    response.setErrorCode(ConstantApplication.INVALID_USER_ERROR_CODE);
                    response.setMessage("User in Member User don't exist in the system");
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    response.setTimestamp(new Date());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
                userEntity = userService.findByUsername(name).get();
                projectEntity.getMembers().add(userEntity);
            }
        }
        //ready for save project data
        Project outProject = projectService.save(projectEntity);
        log.info("exiting method creationProject");
        return ResponseEntity.ok().body(outProject);
    }

    @GetMapping("/search/member")
    public ResponseEntity creationProject(@RequestParam("name") String name) {
        log.info("entering method creationProject");
        log.info("param "+name);

        List<String> membersName = new ArrayList<>();
        userService.searchByUsernameActive(name).forEach( user -> {
            membersName.add(user.getUsername());
        });
        log.info("exiting method creationProject");
        return ResponseEntity.ok().body(membersName);
    }

}


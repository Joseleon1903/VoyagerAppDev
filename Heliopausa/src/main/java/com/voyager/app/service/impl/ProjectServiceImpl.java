package com.voyager.app.service.impl;

import com.voyager.app.entity.Project;
import com.voyager.app.repository.ProjectRepository;
import com.voyager.app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }
}

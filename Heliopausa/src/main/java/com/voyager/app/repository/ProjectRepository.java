package com.voyager.app.repository;

import com.voyager.app.entity.ImagesData;
import com.voyager.app.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Long > {
}

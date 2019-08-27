package com.voyager.app.repository;

import com.voyager.app.entity.ImagesData;
import org.springframework.data.repository.CrudRepository;

public interface ImagesDataRepository extends CrudRepository<ImagesData,Long >{

    ImagesData findByName(String name);

}

package com.voyager.app.repository;

import com.voyager.app.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jleon on 6/11/2018.
 */
public interface RoleRepository extends CrudRepository<Role, Long>{

    Role findByName(String name);

}

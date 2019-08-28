package com.voyager.app.service;

import com.voyager.app.entity.Role;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(String name);

}

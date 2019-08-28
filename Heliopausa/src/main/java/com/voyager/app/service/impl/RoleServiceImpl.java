package com.voyager.app.service.impl;

import com.voyager.app.entity.Role;
import com.voyager.app.entity.User;
import com.voyager.app.repository.RoleRepository;
import com.voyager.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}

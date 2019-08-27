package com.voyager.app.common;

import com.voyager.app.constant.ConstantApplication;
import com.voyager.app.entity.Profile;
import com.voyager.app.entity.Role;
import com.voyager.app.entity.User;
import com.voyager.app.repository.RoleRepository;
import com.voyager.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jleon on 6/5/2018.
 */
@Component
public class MockUsuario implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initUserData();
    }

    public void initUserData(){

        System.out.println("inicializando data de usuario");
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        String pass = bCryptPasswordEncoder.encode("admin123");
        user.setPassword(pass);
        user.setStatus(ConstantApplication.STATUS_A);
        Profile profile = new Profile();
        profile.setId(1);
        profile.setFirstName("Jose");
        profile.setLastName("De Leon");
        profile.setEmail("joseleon@gmail.com");
        user.setProfile(profile);
        Role rol =roleRepository.findById(1L).get();
        user.getRoles().add(rol);
        User userC = userService.save(user);
        System.out.println("Usuario: "+ userC );
        System.out.println("Terminando inicializando data de usuario");

    }
   





}

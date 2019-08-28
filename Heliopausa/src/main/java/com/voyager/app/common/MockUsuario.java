package com.voyager.app.common;

import com.voyager.app.constant.ConstantApplication;
import com.voyager.app.entity.ErrorException;
import com.voyager.app.entity.Profile;
import com.voyager.app.entity.Role;
import com.voyager.app.entity.User;
import com.voyager.app.repository.RoleRepository;
import com.voyager.app.repository.UserRepository;
import com.voyager.app.service.impl.ErrorExceptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private ErrorExceptionServiceImpl errorExceptionService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initUserData();
    }

    public void initUserData(){

        catalogErrorInit();

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
        user.setEmail("joseleon@gmail.com");
        user.setProfile(profile);
        Role rol =roleRepository.findById(1L).get();
        user.getRoles().add(rol);
        User userC = userService.save(user);
        System.out.println("Usuario: "+ userC );
        System.out.println("Terminando inicializando data de usuario");

    }

    public void catalogErrorInit(){
        List<ErrorException> listError = new ArrayList<>();
        ErrorException  errorE = null;
        //invalid Autentication Error
        errorE = new ErrorException();
        errorE.setCode(501);
        errorE.setDescription("Check that you have used the correct email and password combination for the account you are trying to access.");
        errorE.setStatus(true);
        listError.add(errorE);

        //invalid confirm password
        errorE = new ErrorException();
        errorE.setCode(502);
        errorE.setDescription("Password does not match the confirm password.");
        errorE.setStatus(true);
        listError.add(errorE);

        //invalid username
        errorE = new ErrorException();
        errorE.setCode(503);
        errorE.setDescription("Duplicate Username: the username already exists");
        errorE.setStatus(true);
        listError.add(errorE);

        errorE = new ErrorException();
        errorE.setCode(800);
        errorE.setDescription("There are required data not provided");
        errorE.setStatus(true);
        listError.add(errorE);

        errorE = new ErrorException();
        errorE.setCode(801);
        errorE.setDescription("The password format does not represent a format accepted by the system");
        errorE.setStatus(true);
        listError.add(errorE);

        errorE = new ErrorException();
        errorE.setCode(802);
        errorE.setDescription("The email format does not represent a format accepted by the system");
        errorE.setStatus(true);
        listError.add(errorE);

        errorE = new ErrorException();
        errorE.setCode(504);
        errorE.setDescription("Otp code does not match");
        errorE.setStatus(true);
        listError.add(errorE);

        ///registration error
        System.out.println("List Error : "+listError );
        for (ErrorException var : listError) {
            errorExceptionService.save(var);
        }
    }




}

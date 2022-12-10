package com.proyecto.ClinicaOdontologica.service.securityConf;

import com.proyecto.ClinicaOdontologica.entity.security.User;
import com.proyecto.ClinicaOdontologica.entity.security.UserRoles;
import com.proyecto.ClinicaOdontologica.repository.security.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UserRolesRepository userRolesRepository;

    @Autowired
    public DataLoader(UserRolesRepository userRolesRepository) {

        this.userRolesRepository = userRolesRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        String password2 = passwordEncoder.encode("password2");

        userRolesRepository.save(new User("Grisel", "grisoc930@gmail.com", "grisoc930@gmail.com" , password, UserRoles.ADMIN));
        userRolesRepository.save(new User("Frank", "frank@gmail.com", "frank@gmail.com" , password2, UserRoles.USER));

    }
}

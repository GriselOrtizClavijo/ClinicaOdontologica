package com.proyecto.ClinicaOdontologica.service.securityConf;

import com.proyecto.ClinicaOdontologica.repository.security.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRolesService implements UserDetailsService {
    @Autowired
    private final UserRolesRepository userRolesRepository;

    @Autowired
    public UserRolesService(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRolesRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email de usuario no encontrado"));
    }
}

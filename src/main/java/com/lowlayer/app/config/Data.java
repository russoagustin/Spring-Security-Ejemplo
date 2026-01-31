package com.lowlayer.app.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lowlayer.app.model.AppUser;
import com.lowlayer.app.model.Role;
import com.lowlayer.app.repositories.AppUserRepository;
import com.lowlayer.app.repositories.RoleRepository;

@Configuration
public class Data {

    @Bean
    CommandLineRunner initDatabase(AppUserRepository repo, RoleRepository roleRepo, PasswordEncoder encoder) {
        return args -> {
            // Agrego roles 
            Role adminRole = new Role();
            adminRole.setRoleName("ADMIN");

            Role userRole = new Role();
            userRole.setRoleName("USER");
            if (roleRepo.findByRoleName("ADMIN").isEmpty()) {
                roleRepo.save(adminRole);
            }
            if (roleRepo.findByRoleName("USER").isEmpty()) {
                roleRepo.save(userRole);
            }

            // Solo creamos el usuario si no existe
            if (repo.findByUsername("admin").isEmpty()) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                // AQUÍ OCURRE LA MAGIA: Encriptamos antes de guardar
                admin.setPassword(encoder.encode("123")); 
                admin.setRole(Set.of(adminRole));

                repo.save(admin);
                System.out.println("Usuario ADMIN creado en base de datos");
            }

            if (repo.findByUsername("user").isEmpty()) {
                AppUser user = new AppUser();
                user.setUsername("user");
                // AQUÍ OCURRE LA MAGIA: Encriptamos antes de guardar
                user.setPassword(encoder.encode("123")); 
                user.setRole(Set.of(userRole));
                repo.save(user);
                System.out.println("Usuario creado en base de datos");
            }

      };
    }
}

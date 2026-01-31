package com.lowlayer.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lowlayer.app.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer>{

    /**
     * Búsqueda con lazy fetch (por defecto). Devuelve el usuario sin forzar carga de roles.
     */
    Optional<AppUser> findByUsername(String username);

    /**
     * Trae el AppUser junto con sus roles usando JOIN FETCH (carga EAGER para esta consulta).
     */
    @Query("select u from AppUser u join fetch u.role where u.username = :username")
    Optional<AppUser> findByUsernameWithRoles(@Param("username") String username);
}

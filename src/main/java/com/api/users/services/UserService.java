package com.api.users.services;

import com.api.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Mostrar todos los usuarios
    List<User>findAll()throws Exception;

    // Buscar Por Id
    Optional<User> findById(Long id)throws Exception;

    // Guardar
    User save (User user)throws Exception;

    // Eliminar

    void  remove (Long id) throws Exception;



}

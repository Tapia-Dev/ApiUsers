package com.api.users.services;

import com.api.users.Repository.UserRepository;
import com.api.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() throws Exception {
        try {
            return userRepository.findAll();
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) throws Exception {
        try {
            return userRepository.findById(id);
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public User save(User user) throws Exception {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void remove(Long id) throws Exception {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}

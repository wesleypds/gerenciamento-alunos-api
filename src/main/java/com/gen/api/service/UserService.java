package com.gen.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gen.api.exception.CriptoExistsException;
import com.gen.api.exception.EmailExistsException;
import com.gen.api.exception.ServiceExc;
import com.gen.api.model.entity.UserEntity;
import com.gen.api.repository.UserRepository;
import com.gen.api.util.Util;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void userSave(UserEntity user) throws CriptoExistsException {

        try {
            
            if (repository.findByEmail(user.getEmail()) != null) {
                throw new EmailExistsException("Este email já esta cadastrado: " + user.getEmail());
            }

            user.setPassword(Util.md5(user.getPassword()));

        } catch (Exception e) {
            throw new CriptoExistsException("Error na criptografia da senha");
        }

        repository.save(user);
    }

    public UserEntity loginUser(String user, String password) throws ServiceExc {

        return repository.getLogin(user, password);
    }
}

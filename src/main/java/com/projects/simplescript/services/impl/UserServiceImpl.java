package com.projects.simplescript.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.simplescript.model.ResponseData;
import com.projects.simplescript.model.Simple;
import com.projects.simplescript.model.Storage;
import com.projects.simplescript.model.User;
import com.projects.simplescript.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Override
    public List<Simple> getPositions() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getPositions'");
    }

    @Override
    public List<Simple> getDivisions() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getDivisions'");
    }

    @Override
    public List<Simple> getDepartements() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getDepartements'");
    }

    @Override
    public List<Simple> getLevels() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getLevels'");
    }

    @Override
    public void save(User entity) {
        
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public ResponseData<?> authenticate(String employeeId, String password) {
        final String user = "Admin";
        final String pass = "Admin";

        if(!(employeeId.equals(user))){
            return new ResponseData<>(true, "User ID Invalid", null);
        }

        if(!(password.equals(pass))){
            return new ResponseData<>(true, "Incorrect Password", null);
        }

                        Storage.checkConfig();
        return new ResponseData<>(false, "Login Success!", user);
    }

    @Override
    public User update(User entity) {
        
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(User entity) {
        
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void delete(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<User> find(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public List<User> findAll() {
        
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public void deleteInBatch(List<User> users) {
        
        throw new UnsupportedOperationException("Unimplemented method 'deleteInBatch'");
    }
    
}

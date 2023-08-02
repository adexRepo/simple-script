package com.projects.simplescript.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.simplescript.dao.UserRepository;
import com.projects.simplescript.model.ResponseData;
import com.projects.simplescript.model.Simple;
import com.projects.simplescript.model.User;
import com.projects.simplescript.services.UserService;
import com.projects.simplescript.utils.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepo;

    @Override
    public List<Simple> getPositions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPositions'");
    }

    @Override
    public List<Simple> getDivisions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDivisions'");
    }

    @Override
    public List<Simple> getDepartements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDepartements'");
    }

    @Override
    public List<Simple> getLevels() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLevels'");
    }

    @Override
    public void save(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public ResponseData<?> authenticate(String employeeId, String password) {
        Integer id = Integer.parseInt(employeeId);
        Optional<User> user = userRepo.findById(id);

        if (!user.isPresent()) {
            return new ResponseData<>(true, "Invalid Employee ID!", null);
        }

        boolean isPasswordMatch = PasswordEncoder.matches(password, user.get().getPassword());
        if (!isPasswordMatch) {
            return new ResponseData<>(true, "Incorrect Password!", null);
        }

        return new ResponseData<>(false, "Login Success!", user);
    }

    @Override
    public User update(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<User> find(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public void deleteInBatch(List<User> users) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteInBatch'");
    }
    
}

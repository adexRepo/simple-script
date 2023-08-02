package com.projects.simplescript.services;

import java.util.List;
import java.util.Optional;

import com.projects.simplescript.model.ResponseData;
import com.projects.simplescript.model.Simple;
import com.projects.simplescript.model.User;

public interface UserService {

    /* --------------------------------- Common --------------------------------- */
    
    public List<Simple> getPositions    ();
    public List<Simple> getDivisions    ();
    public List<Simple> getDepartements ();
    public List<Simple> getLevels       ();

    /* ------------------------------- Operational ------------------------------- */
    public void save(User entity);
    public ResponseData<?> authenticate(String employeeId, String password);

    public User update(User entity);
    public void delete(User entity);
    public void delete(Long id) ;
    public Optional<User> find(Long id);
    public List<User> findAll();
    public Optional<User> findByEmail(String email);
    public void deleteInBatch(List<User> users);

}

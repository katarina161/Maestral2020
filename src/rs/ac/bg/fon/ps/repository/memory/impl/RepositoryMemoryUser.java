/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.memory.impl;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.repository.memory.MemoryRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryMemoryUser implements MemoryRepository<User, Long>{
    
    private final List<User> users;

    public RepositoryMemoryUser() {
        this.users = new ArrayList<>();
        users.add(new User(1l, "Pera", "Peric", "admin", "admin"));
    }
    
    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void add(User param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(User param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User get(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

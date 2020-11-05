/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Katarina
 */
public class RepositoryUser {
    
    private final List<User> users;

    public RepositoryUser() {
        this.users = new ArrayList<>();
        users.add(new User(1l, "Pera", "Peric", "admin", "admin"));
    }
    
    public List<User> getAll() {
        return users;
    }
    
}

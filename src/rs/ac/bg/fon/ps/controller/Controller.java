/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.util.List;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.exception.IncorrectPasswordException;
import rs.ac.bg.fon.ps.exception.UnknownUserException;
import rs.ac.bg.fon.ps.repository.RepositoryCategory;
import rs.ac.bg.fon.ps.repository.RepositoryProduct;
import rs.ac.bg.fon.ps.repository.RepositorySize;
import rs.ac.bg.fon.ps.repository.RepositoryUser;

/**
 *
 * @author Katarina
 */
public class Controller {
    
    private static Controller instance;
    private final RepositoryUser storageUser;
    private final RepositoryCategory storageCategory;
    private final RepositorySize storageSize;
    private final RepositoryProduct storageProduct;
    
    private User currentUser;
    
    private Controller() {
        storageUser = new RepositoryUser();
        storageCategory = new RepositoryCategory();
        storageSize = new RepositorySize();
        storageProduct = new RepositoryProduct();
    }
    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        
        return instance;
    }
    
    public User logIn(String username, String password) throws Exception {
        List<User> users = storageUser.getAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    currentUser = user;
                    return user;
                }
                throw new IncorrectPasswordException("Incorrect password.");
            }
        }
        throw new UnknownUserException("Unknown user.");
    }
    
    public void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public List<Category> getAllCategories() {
        return storageCategory.getAll();
    }
    
    public List<Size> getAllSizes() {
        return storageSize.getAll();
    }
    
    public void addProduct(Product product) throws Exception {
        storageProduct.add(product);
    }
    
    public List<Product> getAllProducts() {
        return storageProduct.getAll();
    }
    
}

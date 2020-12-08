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
import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbCategory;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbProduct;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbSize;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbUser;

/**
 *
 * @author Katarina
 */
public class Controller {
    
    private static Controller instance;
    private final Repository storageUser;
    private final Repository storageCategory;
    private final Repository storageSize;
    private final Repository storageProduct;
    
    private Controller() {
        storageUser = new RepositoryDbUser();
        storageCategory = new RepositoryDbCategory();
        storageSize = new RepositoryDbSize();
        storageProduct = new RepositoryDbProduct();
    }
    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        
        return instance;
    }
    
    public User logIn(String username, String password) throws Exception {
        storageUser.connect();
        List<User> users = null;
        try {
            users = storageUser.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            storageUser.disconnect();
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
                throw new IncorrectPasswordException("Incorrect password.");
            }
        }
        throw new UnknownUserException("Unknown user.");
    }
    
    public List<Category> getAllCategories() throws Exception {
        storageCategory.connect();
        List<Category> categories = null;
        try {
            categories = storageCategory.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            storageCategory.disconnect();
        }
        return categories;
    }
    
    public List<Size> getAllSizes() throws Exception {
        storageSize.connect();
        List<Size> sizes = null;
        try {
            sizes = storageSize.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            storageSize.disconnect();
        }
        return sizes;
    }
    
    public void addProduct(Product product) throws Exception {
        storageProduct.connect();
        try {
            storageProduct.add(product);
        } catch (Exception e) {
            throw e;
        } finally {
            storageProduct.disconnect();
        }
    }
    
    public List<Product> getAllProducts() throws Exception {
        storageProduct.connect();
        List<Product> products = null;
        try {
            products = storageProduct.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            storageProduct.disconnect();
        }
        return products;
    }

    public void updateProduct(Product product) throws Exception {
        storageProduct.connect();
        try {
            storageProduct.update(product);
        } catch (Exception e) {
            throw e;
        } finally {
            storageProduct.disconnect();
        }
    }

    public void deleteProduct(Product product) throws Exception {
        storageProduct.connect();
        try {
            storageProduct.delete(product);
        } catch (Exception e) {
            throw e;
        } finally {
            storageProduct.disconnect();
        }
    }
    
}

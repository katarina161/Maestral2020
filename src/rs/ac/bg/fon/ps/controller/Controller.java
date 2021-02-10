/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.controller;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.exception.IncorrectPasswordException;
import rs.ac.bg.fon.ps.exception.UnknownUserException;
import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.DbRepository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbCategory;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDbInvoice;
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
    private final Repository storageInvoice;
    
    private Controller() {
        storageUser = new RepositoryDbUser();
        storageCategory = new RepositoryDbCategory();
        storageSize = new RepositoryDbSize();
        storageProduct = new RepositoryDbProduct();
        storageInvoice = new RepositoryDbInvoice();
    }
    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        
        return instance;
    }
    
    public User logIn(String username, String password) throws Exception {
        ((DbRepository)storageUser).connect();
        List<User> users = null;
        try {
            users = storageUser.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            ((DbRepository)storageUser).disconnect();
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
        ((DbRepository)storageCategory).connect();
        List<Category> categories = null;
        try {
            categories = storageCategory.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            ((DbRepository)storageCategory).disconnect();
        }
        return categories;
    }
    
    public List<Size> getAllSizes() throws Exception {
        ((DbRepository)storageSize).connect();
        List<Size> sizes = null;
        try {
            sizes = storageSize.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            ((DbRepository)storageSize).disconnect();
        }
        return sizes;
    }
    
    public void addProduct(Product product) throws Exception {
        ((DbRepository)storageProduct).connect();
        try {
            storageProduct.add(product);
            ((DbRepository)storageProduct).commit();
        } catch (Exception e) {
            ((DbRepository)storageProduct).rollback();
            throw e;
        } finally {
            ((DbRepository)storageProduct).disconnect();
        }
    }
    
    public List<Product> getAllProducts() throws Exception {
        ((DbRepository)storageProduct).connect();
        List<Product> products = null;
        try {
            products = storageProduct.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            ((DbRepository)storageProduct).disconnect();
        }
        return products;
    }

    public void updateProduct(Product product) throws Exception {
        ((DbRepository)storageProduct).connect();
        try {
            storageProduct.update(product);
            ((DbRepository)storageProduct).commit();
        } catch (Exception e) {
            ((DbRepository)storageProduct).rollback();
            throw e;
        } finally {
            ((DbRepository)storageProduct).disconnect();
        }
    }

    public void deleteProduct(Product product) throws Exception {
        ((DbRepository)storageProduct).connect();
        try {
            storageProduct.delete(product);
            ((DbRepository)storageProduct).commit();
        } catch (Exception e) {
            ((DbRepository)storageProduct).rollback();
            throw e;
        } finally {
            ((DbRepository)storageProduct).disconnect();
        }
    }

    public void saveInvoice(Invoice invoice) throws Exception {
        ((DbRepository)storageInvoice).connect();
        try {
            storageInvoice.add(invoice);
            ((DbRepository)storageInvoice).commit();
        } catch (Exception e) {
            ((DbRepository)storageInvoice).rollback();
            throw e;
        } finally {
            ((DbRepository)storageInvoice).disconnect();
        }
    }
    
    public List<Invoice> getAllInvoices() throws Exception {
        ((DbRepository)storageInvoice).connect();
        List<Invoice> invoices = new ArrayList<>();
        try {
            invoices = storageInvoice.getAll();
        } catch (Exception e) {
            throw e;
        } finally {
            ((DbRepository)storageInvoice).disconnect();
        }
        
        return invoices;
    }
    
}

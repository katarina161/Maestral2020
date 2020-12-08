/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.memory.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.exception.ProductAlreadyExistException;
import rs.ac.bg.fon.ps.repository.memory.MemoryRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryMemoryProduct implements MemoryRepository<Product, Long>{
    
    private final List<Product> products;
    
    public RepositoryMemoryProduct() {
        List<Size> s = new ArrayList<>();
        s.add(new Size(1l, 68));
        s.add(new Size(2l, 74));
        this.products = new ArrayList<>();
        products.add(new Product(755l, "Sweatshirt", new Category(1l, "Sweatshirts and Blouses"), 
                "With car print.", new BigDecimal(750), new BigDecimal(900), s));
    }
    
    @Override
    public void add(Product product) throws Exception {
        if (!products.contains(product)) {
            products.add(product);
        } else {
            throw new ProductAlreadyExistException("Product already exist!");
        }
    }
    
    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void update(Product product) throws Exception {
        int index = products.indexOf(product);
        if (index >= 0) {
            products.remove(index);
            products.add(product);
        } else {
            throw new Exception("Product does not exist.");
        }
    }

    @Override
    public void delete(Product product) throws Exception {
        int index = products.indexOf(product);
        if (index >= 0) {
            products.remove(index);
        } else {
            throw new Exception("Product does not exist.");
        }
    }

    @Override
    public Product get(Long article) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

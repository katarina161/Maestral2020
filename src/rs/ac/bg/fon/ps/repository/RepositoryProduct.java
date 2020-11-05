/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.exception.ProductAlreadyExistException;

/**
 *
 * @author Katarina
 */
public class RepositoryProduct {
    
    private final List<Product> products;
    
    public RepositoryProduct() {
        this.products = new ArrayList<>();
    }
    
    public void add(Product product) throws Exception {
        if (!products.contains(product)) {
            products.add(product);
        } else {
            throw new ProductAlreadyExistException("Product already exist!");
        }
    }
    
}

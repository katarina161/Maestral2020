/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.memory.impl;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.repository.memory.MemoryRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryMemoryCategory implements MemoryRepository<Category, Long>{
    
    private final List<Category> categories;

    public RepositoryMemoryCategory() {
        this.categories = new ArrayList<>();
        categories.add(new Category(1l, "Sweatshirts and Blouses"));
        categories.add(new Category(2l, "Dresses and Skirts"));
        categories.add(new Category(3l, "T-shirts"));
        categories.add(new Category(4l, "Bodysuits"));
        categories.add(new Category(5l, "Holiday clothing"));
    }
    
    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public void add(Category param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Category param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Category param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category get(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Category;

/**
 *
 * @author Katarina
 */
public class RepositoryCategory {
    
    private final List<Category> categories;

    public RepositoryCategory() {
        this.categories = new ArrayList<>();
        categories.add(new Category(1l, "Sweatshirts and Blouses"));
        categories.add(new Category(2l, "Dresses and Skirts"));
        categories.add(new Category(3l, "T-shirts"));
        categories.add(new Category(4l, "Bodysuits"));
        categories.add(new Category(5l, "Holiday clothing"));
    }
    
    public List<Category> getAll() {
        return categories;
    }
   
}

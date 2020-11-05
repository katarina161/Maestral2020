/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Size;

/**
 *
 * @author Katarina
 */
public class RepositorySize {
    
    private final List<Size> sizes;
    
    public RepositorySize() {
        this.sizes = new ArrayList<>();
        sizes.add(new Size(1l, 68));
        sizes.add(new Size(2l, 74));
        sizes.add(new Size(3l, 80));
        sizes.add(new Size(4l, 86));
        sizes.add(new Size(5l, 92));
    }
    
    public List<Size> getAll() {
        return sizes;
    }
    
}

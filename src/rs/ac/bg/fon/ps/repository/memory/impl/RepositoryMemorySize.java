/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.memory.impl;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.repository.memory.MemoryRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryMemorySize implements MemoryRepository<Size, Long>{
    
    private final List<Size> sizes;
    
    public RepositoryMemorySize() {
        this.sizes = new ArrayList<>();
        sizes.add(new Size(1l, 68));
        sizes.add(new Size(2l, 74));
        sizes.add(new Size(3l, 80));
        sizes.add(new Size(4l, 86));
        sizes.add(new Size(5l, 92));
    }
    
    @Override
    public List<Size> getAll() {
        return sizes;
    }

    @Override
    public void add(Size param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Size param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Size param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Size get(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

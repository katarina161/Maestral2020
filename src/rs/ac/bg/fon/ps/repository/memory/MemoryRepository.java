/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.memory;

import rs.ac.bg.fon.ps.repository.Repository;

/**
 *
 * @author Katarina
 */
public interface MemoryRepository <T, K> extends Repository<T, K>{

    @Override
    public default void connect() throws Exception {
    }

    @Override
    public default void disconnect() throws Exception {
    }
    
}

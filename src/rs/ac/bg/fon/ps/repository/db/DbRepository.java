/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import rs.ac.bg.fon.ps.repository.Repository;

/**
 *
 * @author Katarina
 */
public interface DbRepository <T> extends Repository<T>{

    public default void connect() throws Exception {
        DbConnectionFactory.getInstance().getConnection();
    }

    public default void disconnect() throws Exception {
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    public default void commit() throws Exception {
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    public default void rollback() throws Exception {
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
    
}

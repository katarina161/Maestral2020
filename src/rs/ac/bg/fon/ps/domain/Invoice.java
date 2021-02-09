/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Katarina
 */
public class Invoice {
    
    private int id;
    private String number;
    private String partner;
    private Date date;
    private BigDecimal total;
    private boolean processed;
    private boolean canceld;
    private User user;

    public Invoice() {
    }

    public Invoice(String number, String partner, Date date, BigDecimal total, boolean processed, boolean canceld, User user) {
        this.number = number;
        this.partner = partner;
        this.date = date;
        this.total = total;
        this.processed = processed;
        this.canceld = canceld;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isCanceld() {
        return canceld;
    }

    public void setCanceld(boolean canceld) {
        this.canceld = canceld;
    }
    
}

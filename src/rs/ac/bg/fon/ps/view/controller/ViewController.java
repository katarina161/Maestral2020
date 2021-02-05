/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.form.FrmLogIn;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmProduct;
import rs.ac.bg.fon.ps.view.form.FrmSearchProducts;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class ViewController {
//    private static ViewController instance;
//    
//    private FrmMain mainForm;
//    private FrmSearchProducts searchProductsForm;
//    
//    private User currentUser;
//    private Map<String, Object> paramMap = new HashMap<>();
//
//    private ViewController() {
//    }
//    
//    public static ViewController getInstance() {
//        if (instance == null) {
//            instance = new ViewController();
//        }
//        return instance;
//    }
//    
//    public void startApp() {
//        new FrmLogIn().setVisible(true);
//    }
//    
//    public void openMainForm(User user) {
//        this.currentUser = user;
//        mainForm = new FrmMain();
//        mainForm.setVisible(true);
//    }
//    
//    public User getCurrentUser() {
//        return currentUser;
//    }
//    
//    public void logOUt() {
//        currentUser = null;
//        startApp();
//    }
//    
//    public void openSearchProductsForm() {
//        searchProductsForm = new FrmSearchProducts(mainForm, true);
//        searchProductsForm.setLocationRelativeTo(mainForm);
//        searchProductsForm.setVisible(true);
//    }
//    
//    public void openProductForm(Product product) {
//        paramMap.put("PRODUCT_FORM_DETAILS", product);
//        FrmProduct frmProduct = new FrmProduct(mainForm, true, FormMode.FORM_DETAIL);
//        frmProduct.setLocationRelativeTo(mainForm);
//        frmProduct.setVisible(true);
//    }
//    
//    public void openProductForm() {
//        FrmProduct frmProduct = new FrmProduct(mainForm, true, FormMode.FORM_ADD);
//        frmProduct.setLocationRelativeTo(mainForm);
//        frmProduct.setVisible(true);
//    }
//
//    public Map<String, Object> getParamMap() {
//        return paramMap;
//    }
//
//    public void refreshProductsView() {
//        if (searchProductsForm != null) {
//            searchProductsForm.refreshProductData();
//        }
//    }
    
}

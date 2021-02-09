/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.cordinator;

import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.controller.InvoiceController;
import rs.ac.bg.fon.ps.view.controller.LogInController;
import rs.ac.bg.fon.ps.view.controller.MainController;
import rs.ac.bg.fon.ps.view.controller.ProductController;
import rs.ac.bg.fon.ps.view.controller.SearchProductsController;
import rs.ac.bg.fon.ps.view.form.FrmInvoice;
import rs.ac.bg.fon.ps.view.form.FrmLogIn;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmProduct;
import rs.ac.bg.fon.ps.view.form.FrmSearchProducts;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class MainCordinator {
    
    private static MainCordinator instance;
    private final Map<String, Object> params;
    
    private MainController mainController;
    private SearchProductsController searchProductsController;

    private MainCordinator() {
//        mainController = new MainController(new FrmMain());
        params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public void openLogInForm() {
        LogInController logInController = new LogInController(new FrmLogIn());
        logInController.openForm();
    }

    public MainController getMainController() {
        return mainController;
    }
    
    public Object getParam(String name) {
        return params.get(name);
    }
    
    public void addParam(String name, Object object) {
        params.put(name, object);
    }

    public void openMainForm() {
        mainController = new MainController(new FrmMain());
        mainController.openForm();
    }

    public void openAddNewProductForm() {
        ProductController productController = new ProductController(new FrmProduct(mainController.getFrmMain(), true));
        productController.openForm(FormMode.FORM_ADD);
    }

    public void openViewAllProductsForm() {
        searchProductsController = 
                new SearchProductsController(new FrmSearchProducts(mainController.getFrmMain(), true));
        searchProductsController.openForm();
    }

    public void openProductDetailsForm() {
        ProductController productController = new ProductController(new FrmProduct(mainController.getFrmMain(), true));
        productController.openForm(FormMode.FORM_DETAIL);
    }

    public void refreshProductsView() {
        searchProductsController.refreshProductsView();
    }

    public void openAddNewInvoiceForm() {
        InvoiceController invoiceController = new InvoiceController(new FrmInvoice(mainController.getFrmMain(), true));
        invoiceController.openForm(FormMode.FORM_ADD);
    }
}

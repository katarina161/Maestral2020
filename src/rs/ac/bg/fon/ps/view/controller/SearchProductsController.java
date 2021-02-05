/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.view.component.table.ProductTableModel;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmSearchProducts;

/**
 *
 * @author Katarina
 */
public class SearchProductsController {

    private final FrmSearchProducts frmSearchProducts;
    private final FrmMain parent;

    public SearchProductsController(FrmSearchProducts frmSearchProducts) {
        this.frmSearchProducts = frmSearchProducts;
        this.parent = MainCordinator.getInstance().getMainController().getFrmMain();
        addActionListener();
    }

    private void addActionListener() {
        frmSearchProducts.btnDeleteAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                int selectedRow = frmSearchProducts.getTblProducts().getSelectedRow();
                if (selectedRow >= 0) {
                    int answer = JOptionPane.showConfirmDialog(frmSearchProducts, "Are you sure you want to delete selected product?",
                            "Confirm", JOptionPane.YES_NO_OPTION);
                    if (answer == 0) {
                        ProductTableModel model = (ProductTableModel) frmSearchProducts.getTblProducts().getModel();
                        try {
                            Controller.getInstance().deleteProduct(model.getProduct(selectedRow));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frmSearchProducts, "Error occured. Delete product failed.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frmSearchProducts, "You must select a product.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmSearchProducts.btnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetail();
            }

            private void showDetail() {
                int selectedRow = frmSearchProducts.getTblProducts().getSelectedRow();
                if (selectedRow >= 0) {
                    ProductTableModel model = (ProductTableModel) frmSearchProducts.getTblProducts().getModel();
                    Product selectedProduct = model.getProduct(selectedRow);
                    MainCordinator.getInstance().addParam(Constants.PARAM_PRODUCT, selectedProduct);
                    MainCordinator.getInstance().openProductDetailsForm();
                } else {
                    JOptionPane.showMessageDialog(frmSearchProducts, "You must select a product.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        frmSearchProducts.btnAddAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }

            private void add() {
                MainCordinator.getInstance().openAddNewProductForm();
            }
        });
    }

    public void openForm() {
        frmSearchProducts.setLocationRelativeTo(parent);
        frmSearchProducts.setResizable(false);
        try {
            prepareView();
            frmSearchProducts.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frmSearchProducts, "View initialisation failed!", "Error", JOptionPane.ERROR_MESSAGE);
            frmSearchProducts.dispose();
        }
    }

    private void prepareView() throws Exception {
        fillTableProducts();
    }

    private void fillTableProducts() throws Exception {
        List<Product> products = Controller.getInstance().getAllProducts();
        ProductTableModel model = new ProductTableModel(products);
        frmSearchProducts.getTblProducts().setModel(model);

        List<Category> categories = Controller.getInstance().getAllCategories();
        JComboBox cbCategories = new JComboBox(categories.toArray());

        TableColumn tc = frmSearchProducts.getTblProducts().getColumnModel().getColumn(2);
        tc.setCellEditor(new DefaultCellEditor(cbCategories));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.view.component.table.InvoiceItemTableModel;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmInvoice;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class InvoiceController {
    
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy.");

    private FrmInvoice frmInvoice;
    private FrmMain parent;
    private boolean listen = true;

    public InvoiceController(FrmInvoice frmInvoice) {
        this.frmInvoice = frmInvoice;
        this.parent = MainCordinator.getInstance().getMainController().getFrmMain();
        addActionListener();
    }

    private void addActionListener() {
        frmInvoice.cmbProductAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listen) {
                    fillCmbSizes();
                    frmInvoice.getTxtPrice().setText(
                            ((Product) frmInvoice.getCmbProducts().getSelectedItem()).getPriceWithVAT().setScale(2, RoundingMode.HALF_UP).doubleValue()+"");
                }
            }
        });
    }

    public void openForm(FormMode formMode) {
        frmInvoice.setLocationRelativeTo(parent);
        frmInvoice.setResizable(false);
        try {
            prepareView(formMode);
            frmInvoice.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frmInvoice, "View initialisation failed!", "Error", JOptionPane.ERROR_MESSAGE);
            frmInvoice.dispose();
        }
    }

    private void prepareView(FormMode formMode) throws Exception {
        fillCmbProducts();
        fillTblItems();
        frmInvoice.getCmbSizes().removeAllItems();
        fillDefaultValues();
        setupComponents(formMode);
    }

    private void fillCmbProducts() throws Exception {
        listen = false;
        frmInvoice.getCmbProducts().removeAllItems();
        List<Product> products = Controller.getInstance().getAllProducts();

        for (Product product : products) {
            frmInvoice.getCmbProducts().addItem(product);
        }

        frmInvoice.getCmbProducts().setSelectedIndex(-1);
        listen = true;
    }

    private void fillCmbSizes() {
        frmInvoice.getCmbSizes().removeAllItems();
        List<Size> sizes = ((Product) frmInvoice.getCmbProducts().getSelectedItem()).getSizes();

        for (Size size : sizes) {
            frmInvoice.getCmbSizes().addItem(size);
        }

        frmInvoice.getCmbSizes().setSelectedIndex(-1);
    }

    private void fillTblItems() {
        InvoiceItemTableModel model = new InvoiceItemTableModel();
        frmInvoice.getTblItems().setModel(model);
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmInvoice.getTxtNumber().setEnabled(true);
                frmInvoice.getTxtDate().setEnabled(true);
                frmInvoice.getTxtPartner().setEnabled(true);
                frmInvoice.getBtnRemoveItem().setVisible(true);
                frmInvoice.getPanelItem().setVisible(true);
                frmInvoice.getBtnCancel().setVisible(false);
                frmInvoice.getBtnSave().setVisible(true);
        }
    }

    private void fillDefaultValues() {
        frmInvoice.getTxtDate().setText(DATE_FORMAT.format(new Date()));
        frmInvoice.getLblUser().setText(String.valueOf(MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_USER)));
    }

}

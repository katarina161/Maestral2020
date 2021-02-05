/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.exception.NegativePriceException;
import rs.ac.bg.fon.ps.exception.ProductAlreadyExistException;
import rs.ac.bg.fon.ps.exception.RequiredFieldsEmptyException;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmProduct;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class ProductController {

    private final FrmProduct frmProduct;
    private final FrmMain parent;

    public ProductController(FrmProduct frmProduct) {
        this.frmProduct = frmProduct;
        this.parent = MainCordinator.getInstance().getMainController().getFrmMain();
        addActionListener();
    }

    private void addActionListener() {
        frmProduct.btnSaveAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    validateForm();
                    calculatePriceWithVAT();

                    Product product = makeProductFromForm();
                    Controller.getInstance().addProduct(product);

                    resetForm();
                    JOptionPane.showMessageDialog(frmProduct, "Product successfully saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (RequiredFieldsEmptyException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmProduct, "Please fill out all of the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ProductAlreadyExistException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmProduct, "Product already exist.", "Insert Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmProduct, "Error occured. Save product failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmProduct.btnCalculatePriceWithVATAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePriceWithVAT();
            }
        });

        frmProduct.btnRevertAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
                fillProductForm();
            }
        });

        frmProduct.btnUpdateAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }

            private void update() {
                try {
                    validateForm();
                    calculatePriceWithVAT();

                    Product product = makeProductFromForm();

                    Controller.getInstance().updateProduct(product);

                    JOptionPane.showMessageDialog(frmProduct, "Product successfully updates!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (RequiredFieldsEmptyException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmProduct, "Please fill out all of the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmProduct, "Error occured. Update product failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmProduct.btnDeleteAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                int answer = JOptionPane.showConfirmDialog(frmProduct, "Are you sure you want to delete this product?",
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    try {
                        Product product = makeProductFromForm();

                        Controller.getInstance().deleteProduct(product);

                        JOptionPane.showMessageDialog(frmProduct, "Product successfully deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frmProduct.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frmProduct, "Error occured. Delete product failed.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        frmProduct.btnResetAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        frmProduct.btnAddSizeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSize();
            }

            private void addSize() {
                DefaultListModel listModel = (DefaultListModel) frmProduct.getListSelectedSizes().getModel();
                List<Size> selectedSizes = getSelectedSizes();
                if (!selectedSizes.contains((Size) frmProduct.getCmbSize().getSelectedItem())) {
                    listModel.addElement(frmProduct.getCmbSize().getSelectedItem());
                }
            }
        });

        frmProduct.btnRemoveSizeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSize();
            }

            private void removeSize() {
                DefaultListModel listModel = (DefaultListModel) frmProduct.getListSelectedSizes().getModel();
                List<Object> selectedSizes = frmProduct.getListSelectedSizes().getSelectedValuesList();
                for (Object selectedSize : selectedSizes) {
                    listModel.removeElement(selectedSize);
                }
            }
        });
    }

    public void openForm(FormMode formMode) {
        frmProduct.setLocationRelativeTo(parent);
        frmProduct.setResizable(false);
        try {
            prepareView(formMode);
            frmProduct.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(parent, "View initialisation failed!", "Error", JOptionPane.ERROR_MESSAGE);
            frmProduct.dispose();
        }
    }

    private void prepareView(FormMode formMode) throws Exception {
        fillCmbCategory();
        fillCmbSize();
        setupComponents(formMode);
    }

    private void fillCmbCategory() throws Exception {
        frmProduct.getCmbCategory().removeAllItems();
        List<Category> categories = Controller.getInstance().getAllCategories();
        for (Category category : categories) {
            frmProduct.getCmbCategory().addItem(category);
        }
        frmProduct.getCmbCategory().setSelectedIndex(-1);
    }

    private void fillCmbSize() throws Exception {
        frmProduct.getCmbSize().removeAllItems();
        List<Size> sizes = Controller.getInstance().getAllSizes();
        for (Size size : sizes) {
            frmProduct.getCmbSize().addItem(size);
        }
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmProduct.setTitle("Add new Product");
                frmProduct.getBtnSave().setVisible(true);
                frmProduct.getBtnReset().setVisible(true);
                frmProduct.getBtnRevert().setVisible(false);
                frmProduct.getBtnUpdate().setVisible(false);
                frmProduct.getBtnDelete().setVisible(false);
                break;
            case FORM_DETAIL:
                frmProduct.setTitle("Update Product");
                frmProduct.getBtnSave().setVisible(false);
                frmProduct.getBtnReset().setVisible(false);
                frmProduct.getBtnRevert().setVisible(true);
                frmProduct.getBtnUpdate().setVisible(true);
                frmProduct.getBtnDelete().setVisible(true);
                frmProduct.getTxtArticle().setEnabled(false);

                fillProductForm();
                break;
        }
    }

    private void fillProductForm() {
        Product product = (Product) MainCordinator.getInstance().getParam(Constants.PARAM_PRODUCT);
        frmProduct.getTxtArticle().setText(String.valueOf(product.getArticle()));
        frmProduct.getTxtName().setText(String.valueOf(product.getName()));
        frmProduct.getTxtDescription().setText(String.valueOf(product.getDescription()));
        frmProduct.getCmbCategory().setSelectedItem(product.getCategory());
        DefaultListModel listModel = (DefaultListModel) frmProduct.getListSelectedSizes().getModel();
        for (Size size : product.getSizes()) {
            listModel.addElement(size);
        }
        frmProduct.getTxtPriceWithoutVAT().setText(String.valueOf(product.getPriceWithoutVAT()));
        frmProduct.getTxtPriceWithVAT().setText(String.valueOf(product.getPriceWithVAT()));
    }

    private Product makeProductFromForm() {
        Product product = new Product();
        product.setArticle(Long.parseLong(frmProduct.getTxtArticle().getText().trim()));
        product.setName(frmProduct.getTxtName().getText().trim());
        product.setDescription(frmProduct.getTxtDescription().getText().trim());
        product.setCategory((Category) frmProduct.getCmbCategory().getSelectedItem());
        product.setSizes(getSelectedSizes());
        product.setPriceWithoutVAT(new BigDecimal(frmProduct.getTxtPriceWithoutVAT().getText().trim()));
        product.setPriceWithVAT(new BigDecimal(frmProduct.getTxtPriceWithVAT().getText().trim()));

        return product;
    }

    private void resetErrors() {
        frmProduct.getLblArticleError().setText("");
        frmProduct.getLblNameError().setText("");
        frmProduct.getLblCategoryError().setText("");
        frmProduct.getLblSizeError().setText("");
        frmProduct.getLblPriceWithoutWatError().setText("");
    }

    private void resetForm() {
        resetErrors();

        frmProduct.getTxtArticle().setText("");
        frmProduct.getTxtName().setText("");
        frmProduct.getTxtDescription().setText("");
        frmProduct.getCmbCategory().setSelectedIndex(-1);
        frmProduct.getTxtPriceWithoutVAT().setText("");
        frmProduct.getTxtPriceWithVAT().setText("");

        DefaultListModel listModel = (DefaultListModel) frmProduct.getListSelectedSizes().getModel();
        listModel.removeAllElements();
    }

    private void validateForm() throws RequiredFieldsEmptyException {
        resetErrors();
        boolean errors = false;

        if (frmProduct.getTxtArticle().getText() == null || frmProduct.getTxtArticle().getText().isEmpty()) {
            frmProduct.getLblArticleError().setText("Required!");
            errors = true;
        }
        if (frmProduct.getTxtName().getText() == null || frmProduct.getTxtName().getText().isEmpty()) {
            frmProduct.getLblNameError().setText("Required!");
            errors = true;
        }
        if (frmProduct.getCmbCategory().getSelectedIndex() == -1) {
            frmProduct.getLblCategoryError().setText("Required!");
            errors = true;
        }
        if (getSelectedSizes() == null || getSelectedSizes().isEmpty()) {
            frmProduct.getLblSizeError().setText("Required!");
            errors = true;
        }
        if (frmProduct.getTxtPriceWithoutVAT().getText() == null || frmProduct.getTxtPriceWithoutVAT().getText().isEmpty()) {
            frmProduct.getLblPriceWithoutWatError().setText("Required!");
            errors = true;
        }

        if (errors) {
            throw new RequiredFieldsEmptyException("Required fields can not be empty.");
        }

    }

    private void validatePrice() throws Exception {
        double priceWithout = Double.parseDouble(frmProduct.getTxtPriceWithoutVAT().getText().trim());
        if (priceWithout <= 0) {
            throw new NegativePriceException("Price must be a natural number!");
        }

    }

    private void calculatePriceWithVAT() {
        try {
            validatePrice();
            double priceWithout = Double.parseDouble(frmProduct.getTxtPriceWithoutVAT().getText().trim());
            int VATpercentage = Integer.parseInt(frmProduct.getTxtVATPercentage().getText().trim());
            double priceWith
                    = new BigDecimal(priceWithout * (1 + VATpercentage / 100.00)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            frmProduct.getTxtPriceWithVAT().setText(String.valueOf(priceWith));
        } catch (NumberFormatException | NegativePriceException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frmProduct, "Price and VAT percentage must be a positive number!", "Price Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(FrmProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Size> getSelectedSizes() {
        List<Size> selectedSizes = new ArrayList<>();
        for (int i = 0; i < frmProduct.getListSelectedSizes().getModel().getSize(); i++) {
            selectedSizes.add((Size) frmProduct.getListSelectedSizes().getModel().getElementAt(i));
        }

        return selectedSizes;
    }
}

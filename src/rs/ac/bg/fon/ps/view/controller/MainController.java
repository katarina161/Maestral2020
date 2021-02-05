/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;

/**
 *
 * @author Katarina
 */
public class MainController {
    
    private final FrmMain frmMain;

    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }

    public void openForm() {
        User user = (User) MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_USER);
        frmMain.getMenuUser().setText(user.getUsername());
        prepareView();
        frmMain.setVisible(true);
    }

    private void addActionListener() {
        frmMain.jmiProductNewAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProductNewActionPerformed(evt);
            }

            private void jmiProductNewActionPerformed(ActionEvent evt) {
                MainCordinator.getInstance().openAddNewProductForm();
            }
        });
        
        frmMain.jmiProductSearchAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jmiProductSearchActionPerformed(e);
            }

            private void jmiProductSearchActionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openViewAllProductsForm();
            }
        });
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

    private void prepareView() {
        frmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmMain.setLocationRelativeTo(null);
        frmMain.getContentPane().setBackground(Color.WHITE);
    }
    
}

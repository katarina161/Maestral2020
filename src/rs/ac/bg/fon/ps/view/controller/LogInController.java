/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.exception.IncorrectPasswordException;
import rs.ac.bg.fon.ps.exception.RequiredFieldsEmptyException;
import rs.ac.bg.fon.ps.exception.UnknownUserException;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmLogIn;

/**
 *
 * @author Katarina
 */
public class LogInController {

    private final FrmLogIn frmLogIn;

    public LogInController(FrmLogIn frmLogIn) {
        this.frmLogIn = frmLogIn;
        addActionListener();
    }

    public void openForm() {
        frmLogIn.setVisible(true);
        prepareView();
    }

    private void addActionListener() {
        frmLogIn.logInAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInUser(evt);
            }

            private void logInUser(java.awt.event.ActionEvent evt) {
                try {
                    String username = frmLogIn.getTxtUsername().getText().trim();
                    String password = String.valueOf(frmLogIn.getTxtPassword().getPassword());

                    validateForm(username, password);

                    User user = Controller.getInstance().logIn(username, password);
                    MainCordinator.getInstance().addParam(Constants.PARAM_CURRENT_USER, user);
                    JOptionPane.showMessageDialog(frmLogIn, "Welcome " + user.getFirstName(),
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    frmLogIn.dispose();
                    
                    MainCordinator.getInstance().openMainForm();
                } catch (RequiredFieldsEmptyException ex) {
                    ex.printStackTrace();
                } catch (IncorrectPasswordException ex) {
                    ex.printStackTrace();
                    frmLogIn.getTxtPassword().setText("");
                    JOptionPane.showMessageDialog(frmLogIn, "Incorrect password.", "Log in Error", JOptionPane.ERROR_MESSAGE);
                } catch (UnknownUserException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmLogIn, "Such user does not exist in the system.", "Log in Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void validateForm(String username, String password) throws RequiredFieldsEmptyException {
                resetForm();
                boolean errors = false;

                if (username == null || username.isEmpty()) {
                    frmLogIn.getLblUsernameError().setText("Username can not be empty.");
                    errors = true;
                }
                if (password == null || password.isEmpty()) {
                    frmLogIn.getLblPasswordError().setText("Passord can not be empty.");
                    errors = true;
                }

                if (errors) {
                    throw new RequiredFieldsEmptyException("Username and/or password are empty.");
                }
            }

            private void resetForm() {
                frmLogIn.getLblUsernameError().setText("");
                frmLogIn.getLblPasswordError().setText("");
            }

        });
    }

    private void prepareView() {
        frmLogIn.setLocationRelativeTo(null);
        frmLogIn.setResizable(false);
        frmLogIn.getContentPane().setBackground(Color.WHITE);
        frmLogIn.getRootPane().setDefaultButton(frmLogIn.getBtnLogIn());
    }

}

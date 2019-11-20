/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xabituka3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */


public class FXMLMenuProfessorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Pane pane1;
    @FXML
    Label lb;
    
    @FXML
    private void actionLearning(ActionEvent evt1){
        pane1.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLCriarLearning.fxml"));
            pane1.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar lista de assuntos");
        }
    }
    @FXML 
    private void actionStats(ActionEvent evt1){
        pane1.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLProfessorStats.fxml"));
            pane1.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar lista de assuntos");
        }
    }
    @FXML 
    private void actionSair(ActionEvent evt1){
        pane1.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("TelaLogin.fxml"));
            pane1.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar lista de assuntos");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lb.setText("Bem vindo Sr.(a)  "+FXMLTelaLoginController.theuser);

    }    
    
}

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
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLCriarLearningController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Pane pane1;
    @FXML 
    private void criarQuestao(ActionEvent evt1){
        pane1.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLCriarQuestao.fxml"));
            pane1.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar a tela de criar questões");
        }
    }
    @FXML 
    private void criarTopico(ActionEvent evt1){
        pane1.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLCriarTopico.fxml"));
            pane1.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar a tela de criar tópico");
        }
    }
    @FXML 
    private void criarMateria(ActionEvent evt1){
        pane1.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLCriarMateria.fxml"));
            pane1.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar a tela de criar matéria");
        }
    }
    @FXML 
    private void actionMenu(ActionEvent evt1){
        pane1.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLMenuProfessor.fxml"));
            pane1.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar o menu do professor");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

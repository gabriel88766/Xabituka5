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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLCriarMateriaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TextField tx;
    
    @FXML
    Pane pane1;
    
    @FXML
    public void onAct(ActionEvent e){
    JSONObject jsonMateria = new JSONObject();
        String materia = tx.getText();
        jsonMateria.put("name",materia);
        String url = Xabituka3.url_xabituca+"subjects";
        try{
            Post.posting(url, jsonMateria.toString());
        } catch (IOException exc){
            System.out.println(exc);
        }
         try {
                pane1.getChildren().clear();
                Pane tela = (Pane) FXMLLoader.load(getClass()
                    .getResource("FXMLCriarLearning.fxml"));
                pane1.getChildren().add(tela);
                JOptionPane.showMessageDialog((null), "Matéria criada com sucesso!");
            } catch (IOException exc2) {
                JOptionPane.showMessageDialog((null), "Falha ao carregar tela de criar learning");
            }
    }
    @FXML
    public void cancelar(ActionEvent e){
        try {
                pane1.getChildren().clear();
                Pane tela = (Pane) FXMLLoader.load(getClass()
                    .getResource("FXMLCriarLearning.fxml"));
                pane1.getChildren().add(tela);
            } catch (IOException exc2) {
                JOptionPane.showMessageDialog((null), "Falha ao carregar tela de criar learning");
            }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xabituka3;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLStatsController implements Initializable {

    private static SubjectsListAuxiliar[] sub;
    private static String[] subjects_list;
    private long[] ids;
    private static int length;
    private String combo_set;
    public static int id_set;
    /**
     * Initializes the controller class.
     */
    @FXML
    VBox vb;
    @FXML
    ComboBox cb;
    @FXML
    Pane pane;
    @FXML
     public void comboChanged(ActionEvent e){
        combo_set = (String) cb.getValue();
        for(int i=0 ; i<length ;i++ ){
            if(combo_set == sub[i].name)
                FXMLEstatisticasController.id_set=sub[i].id;
        }
    }
     @FXML
     public void voltar(ActionEvent evt){
         pane.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLMenuAluno.fxml"));
            pane.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar menu do aluno");
        }
     }
     @FXML
     public void prosseguir(ActionEvent evt){
         FXMLEstatisticasController.user_id = FXMLTelaLoginController.user_id;
         pane.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLEstatisticas.fxml"));
            pane.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar menu do aluno");
        }
     }
    ObservableList<String> list = FXCollections.observableArrayList(getSubjectsList());
    public String[] getSubjectsList(){
        String url = Xabituka3.url_xabituca+"/subjects";
        try{
            String temp = Request.requesting(url);
            JSONArray contest_json = new JSONArray(temp);
            length = contest_json.length();
            sub = new SubjectsListAuxiliar[length];
            subjects_list = new String[length];
            for(int i=0; i < length; i++){
                String aux1 = contest_json.getJSONObject(i).getString("name");
                int aux2 = contest_json.getJSONObject(i).getInt("id");
                sub[i] = new SubjectsListAuxiliar(aux2,aux1);
            }
            Arrays.sort(sub);
            for(int i=0; i< length ; i++){
                subjects_list[i]= sub[i].name;
            }
            return subjects_list;
        }catch(IOException  e){
            String[] k = new String[1];
            return k;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb.setItems(list);
        
    }    
    
}

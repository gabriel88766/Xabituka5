/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xabituka3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import static xabituka3.FXMLCriarTopicoController.subject;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLProfessorStatsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static String[] user_list;
    private static long[] user_id_list;
    int length1;
    int length2;
    private static String[] subject_list;
    private static long[] subject_id_list;
    
      public String[] getUsersList(){
        String url = Xabituka3.url_xabituca+"users";
        try{
            String temp = Request.requesting(url);
            JSONArray users_json = new JSONArray(temp);
            length1 = users_json.length();
            String[] user_list_aux = new String[length1];
            for(int i=0; i < length1; i++){
                user_list_aux[i] = users_json.getJSONObject(i).getString("nickname");
            }
            return user_list_aux;
            
        }catch(IOException  e){
            String[] k = new String[1];
            return k;
        }
    }
    public long[] getUsersIdList(){
        String url = Xabituka3.url_xabituca+"users";
        try{
            String temp = Request.requesting(url);
            JSONArray users_json = new JSONArray(temp);
            length1 = users_json.length();
//            subject_list = new String[length];
            long[] user_id_list_aux = new long[length1];
            for(int i=0; i < length1; i++){
                user_id_list_aux[i] = users_json.getJSONObject(i).getInt("id");
//                System.out.println(subject_id_list_aux[i]);
            }
            return user_id_list_aux;
        }catch(IOException  e){
            long[] k = new long[1];
            return k;
        }
    }
     public String[] getSubjectsList(){
        String url = Xabituka3.url_xabituca+"/subjects";
        try{
            String temp = Request.requesting(url);
            JSONArray subjects_json = new JSONArray(temp);
            length2 = subjects_json.length();
            String[] subject_list_aux = new String[length2];
            for(int i=0; i < length2; i++){
                subject_list_aux[i] = subjects_json.getJSONObject(i).getString("name");
            }
            return subject_list_aux;
            
        }catch(IOException  e){
            String[] k = new String[1];
            return k;
        }
    }
    public long[] getSubjectsIdList(){
        String url = Xabituka3.url_xabituca+"/subjects";
        try{
            String temp = Request.requesting(url);
            JSONArray subjects_json = new JSONArray(temp);
            length2 = subjects_json.length();
//            subject_list = new String[length];
            long[] subject_id_list_aux = new long[length2];
            for(int i=0; i < length2; i++){
                subject_id_list_aux[i] = subjects_json.getJSONObject(i).getInt("id");
//                System.out.println(subject_id_list_aux[i]);
            }
            return subject_id_list_aux;
        }catch(IOException  e){
            long[] k = new long[1];
            return k;
        }
    }
    @FXML
    Pane pane;
    @FXML
    ComboBox cb1;
    @FXML
    ComboBox cb2;
    @FXML
    public void onComboChanged(ActionEvent e){
        String user = cb1.getValue().toString();
        System.out.println(user);
        for(int i=0; i < length1; i++){
            if(user_list[i].equals(user)){
                FXMLEstatisticasController.user_id = (int)user_id_list[i];
            } 
        }
    }
    @FXML 
    public void onComboChanged2(ActionEvent evt){
        String subject = cb2.getValue().toString();
        for(int i=0; i < length2; i++){
                if(subject_list[i].equals(subject)){
                    FXMLEstatisticasController.id_set = (int)subject_id_list[i];
                } else {
                }
            }
    }
    @FXML
    public void voltar(ActionEvent e){
         try {
                pane.getChildren().clear();
                Pane tela = (Pane) FXMLLoader.load(getClass()
                    .getResource("FXMLMenuProfessor.fxml"));
                pane.getChildren().add(tela);
            } catch (IOException exc) {
                JOptionPane.showMessageDialog((null), "Falha ao voltar");
            }
    }
    @FXML
    public void prosseguir(ActionEvent e){
         try {
                pane.getChildren().clear();
                Pane tela = (Pane) FXMLLoader.load(getClass()
                    .getResource("FXMLEstatisticas.fxml"));
                pane.getChildren().add(tela);
            } catch (IOException exc) {
                JOptionPane.showMessageDialog((null), "Falha ao voltar");
            }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        user_id_list = getUsersIdList(); 
        user_list = getUsersList();
        ObservableList<String> list = FXCollections.observableArrayList(user_list);
        cb1.setItems(list);
        subject_id_list = getSubjectsIdList(); 
        subject_list = getSubjectsList();
        ObservableList<String> list2 = FXCollections.observableArrayList(subject_list);
        cb2.setItems(list2);
    }    
    
}

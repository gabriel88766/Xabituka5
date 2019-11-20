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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLCriarTopicoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static String topico;
    static String subject;
    static int subject_id;
    private static int length;
    private static String[] subject_list;
    private static long[] subject_id_list;
    
    
    @FXML
    TextField tx;
    @FXML
    ComboBox cb;
    @FXML
    Pane pane1;
    
    @FXML
    public void onAction(ActionEvent e){
        JSONObject jsonTopico = new JSONObject();
        for(int i=0; i < length; i++){
                if(subject_list[i].equals(subject)){
                    subject_id = (int)subject_id_list[i];
                } else {
                }
            }
        
        topico = tx.getText();
        
        jsonTopico.put("name",topico);
        jsonTopico.put("subjectId",subject_id);
        String url = Xabituka3.url_xabituca+"/topics";
        System.out.println(jsonTopico.toString());
        try{
            Post.posting(url, jsonTopico.toString());
            JOptionPane.showMessageDialog((null), "Tópico cadastrado com sucesso");
        } catch (IOException exc){
            System.out.println(exc);
        }
    }
    @FXML
    public void back(ActionEvent e){
        try {
                pane1.getChildren().clear();
                Pane tela = (Pane) FXMLLoader.load(getClass()
                    .getResource("FXMLCriarLearning.fxml"));
                pane1.getChildren().add(tela);
            } catch (IOException exc) {
                JOptionPane.showMessageDialog((null), "Falha ao voltar");
            }
    }
    
    @FXML 
    public void comboChanged(ActionEvent e){
        subject = cb.getValue().toString();
    }
    
     public String[] getSubjectsList(){
        String url = Xabituka3.url_xabituca+"/subjects";
        try{
            String temp = Request.requesting(url);
            JSONArray subjects_json = new JSONArray(temp);
            length = subjects_json.length();
            String[] subject_list_aux = new String[length];
            for(int i=0; i < length; i++){
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
            length = subjects_json.length();
//            subject_list = new String[length];
            long[] subject_id_list_aux = new long[length];
            for(int i=0; i < length; i++){
                subject_id_list_aux[i] = subjects_json.getJSONObject(i).getInt("id");
//                System.out.println(subject_id_list_aux[i]);
            }
            return subject_id_list_aux;
        }catch(IOException  e){
            long[] k = new long[1];
            return k;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        subject_id_list = getSubjectsIdList(); 
        subject_list = getSubjectsList();
        ObservableList<String> list = FXCollections.observableArrayList(subject_list);
        cb.setItems(list);
    }    
    
}

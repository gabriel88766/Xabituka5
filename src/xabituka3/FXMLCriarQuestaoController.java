/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xabituka3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLCriarQuestaoController implements Initializable {

    
     static String topico;
    static int topic_id;
    
    static String questao;
    static String dificuldade;
    /**
     * Initializes the controller class.
     */
    static boolean resposta=true;
    static int level=1;
    private static String[] topic_list;
    private static long[] topic_id_list;
    int length;
    static int id;
    static String materia;
public void getid()
{
    String url = Xabituka3.url_xabituca+"subjects";
        try{
            String temp = Request.requesting(url);
            JSONArray subjects_json = new JSONArray(temp);
            length = subjects_json.length();
            for(int i=0; i < length; i++){
                if(subjects_json.getJSONObject(i).getString("name").equals(materia))
                id = subjects_json.getJSONObject(i).getInt("id");
            }
          System.out.print(id);
            
        }catch(IOException  e){
            
        }
        
}

 public String[] getTopicsList(){
        String url = Xabituka3.url_xabituca+"/topics";
      
        int j=0;
        try{
            String temp = Request.requesting(url);
            JSONArray topics_json = new JSONArray(temp);
            length = topics_json.length();
//            String[] topic_list_aux = new String[length];
            List<String> topic_list_aux2 = new ArrayList();
            for(int i=0; i < length; i++){
                if(topics_json.getJSONObject(i).getInt("subjectId")==id)
                {
                topic_list_aux2.add(topics_json.getJSONObject(i).getString("name"));
                }
            }
         
            return topic_list_aux2.toArray(new String[0]);
            
        }catch(IOException  e){
            String[] k = new String[1];
            return k;
        }
    }
  public String[] getTopicsList1(){
        String url = Xabituka3.url_xabituca+"/topics";
      
        int j=0;
        try{
            String temp = Request.requesting(url);
            JSONArray topics_json = new JSONArray(temp);
            length = topics_json.length();
//            String[] topic_list_aux = new String[length];
            List<String> topic_list_aux2 = new ArrayList();
            for(int i=0; i < length; i++){
                topic_list_aux2.add(topics_json.getJSONObject(i).getString("name"));
            }
         
            return topic_list_aux2.toArray(new String[0]);
            
        }catch(IOException  e){
            String[] k = new String[1];
            return k;
        }
    }
    public long[] getTopicsIdList(){
      
        String url = Xabituka3.url_xabituca+"/topics";
        try{
            String temp = Request.requesting(url);
            JSONArray topics_json = new JSONArray(temp);
            length = topics_json.length();
//            subject_list = new String[length];
            long[] topic_id_list_aux = new long[length];
            for(int i=0; i < length; i++){
                topic_id_list_aux[i] = topics_json.getJSONObject(i).getInt("id");
            }
            return topic_id_list_aux;
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
            length = subjects_json.length();
            String[] subject_list_aux = new String[length];
           
            for(int i=0; i < length; i++){
                subject_list_aux[i] = subjects_json.getJSONObject(i).getString("name");
            }
            Arrays.sort(subject_list_aux);
            return subject_list_aux;
            
        }catch(IOException  e){
            String[] k = new String[1];
            return k;
        }
    }
    
    @FXML
    ComboBox cb1;
    @FXML
    ComboBox cb2;
    @FXML
    ComboBox cb3;
    @FXML
    ComboBox cb4;
    @FXML
    Pane pane1;
    @FXML
    TextArea txt;
    @FXML 
    public void comboChanged(ActionEvent e){
         if(cb4.getValue().toString().equals("Verdadeiro")){
             resposta=true;
         }else
             resposta=false;
    }
    @FXML
    public void comboChanged1(ActionEvent e){
        materia=cb1.getValue().toString();
        getid();
        String[] combo2 = getTopicsList();
        ObservableList<String> list3 = FXCollections.observableArrayList(combo2);
        cb2.setItems(list3);
    }
    @FXML
    public void comboChanged2(ActionEvent e){
        topico=cb2.getValue().toString();
    }
    @FXML
    public void comboChanged3(ActionEvent e){
       level = Integer.parseInt(cb3.getValue().toString());
       System.out.println(level);
    }
    @FXML
    public void voltar(ActionEvent e){
        try {
                pane1.getChildren().clear();
                Pane tela = (Pane) FXMLLoader.load(getClass()
                    .getResource("FXMLMenuProfessor.fxml"));
                pane1.getChildren().add(tela);
            } catch (IOException exc) {
                JOptionPane.showMessageDialog((null), "Falha ao voltar");
            }
    }
    @FXML
    public void criarQuestao(ActionEvent evt){
        JSONObject jsonQuestao = new JSONObject();
        for(int i=0; i < length; i++){
                if(topic_list[i].equals(topico)){
                    topic_id = (int)topic_id_list[i];
                } else {
                }
            }
        
        questao = txt.getText();
        
        
        jsonQuestao.put("question",questao);
        jsonQuestao.put("difficulty",level);
        jsonQuestao.put("answer",resposta);
        jsonQuestao.put("topicId", topic_id);
        String url = Xabituka3.url_xabituca+"/learn/questions";
                System.out.println(jsonQuestao.toString());

        try{
            Post.posting(url, jsonQuestao.toString());
        } catch (IOException e){
            System.out.println(e);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FXMLCriarQuestaoController.topic_id_list = getTopicsIdList(); 
        FXMLCriarQuestaoController.topic_list = getTopicsList1();
        String[] value = {"Verdadeiro","Falso"};
        ObservableList<String> list = FXCollections.observableArrayList(value);
        cb4.setItems(list);
        String[] levels = {"1","2","3","4","5"};
        ObservableList<String> list2 = FXCollections.observableArrayList(levels);
        cb3.setItems(list2);
        String[] materias = getSubjectsList();
        ObservableList<String> list4 = FXCollections.observableArrayList(materias);
        cb1.setItems(list4);

    }    
    
}

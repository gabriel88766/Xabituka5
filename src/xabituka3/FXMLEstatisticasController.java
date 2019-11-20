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
import javafx.geometry.Pos;
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
public class FXMLEstatisticasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static int id_set;
    static int user_id;
    static JSONArray topics;
    int total;
    int correct;
    
    @FXML
    Pane pane;
    @FXML
    Label lb1;
    @FXML
    Label lb2;
    @FXML
    VBox vb1;
    @FXML
    VBox vb2;
    @FXML
    VBox vb3;
    
    @FXML 
    public void mainMenu(ActionEvent evt){
        if(user_id == FXMLTelaLoginController.user_id){
            //aluno
         pane.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLMenuAluno.fxml"));
            pane.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar menu do aluno");
        }
     
        }else{
            //professor
            pane.getChildren().clear();
            try {
                Pane tela = (Pane) FXMLLoader.load(getClass()
                    .getResource("FXMLMenuProfessor.fxml"));
                pane.getChildren().add(tela);
            } catch (IOException e) {
                JOptionPane.showMessageDialog((null), "Falha ao carregar menu do aluno");
            }
        }
    }
    
    public void showStats(){
       String url = Xabituka3.url_xabituca+"/stats/"+id_set+"/"+user_id;
       try{
            String temp = Request.requesting(url);
            JSONObject stats_json = new JSONObject(temp);
            topics = stats_json.getJSONArray("topics");
            this.total = stats_json.getInt("numberOfAnswers");
            this.correct = stats_json.getInt("numberOfCorrectAnswers");
            System.out.print(topics);
        }catch(IOException  e){
            
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showStats();
        lb1.setText("Total de respostas: "+total);
        lb2.setText("Respostas corretas: "+correct);
        int k = topics.length();
        Label[] lb = new Label[3*k];
        for(int i = 0;i<3*k;i++){
            lb[i]=new Label();
            lb[i].setPrefWidth(165);
            lb[i].setPrefHeight(27);
            lb[i].setAlignment(Pos.CENTER);
            lb[i].setStyle("-fx-background-color: rgb(240,240,240)");
        }
        for(int i = 0;i<k;i++){
            lb[3*i].setText(topics.getJSONObject(i).getString("topicName"));
            lb[3*i+1].setText(Integer.toString(topics.getJSONObject(i).getInt("numberOfTopicAnswers")));
            lb[3*i+2].setText(Integer.toString(topics.getJSONObject(i).getInt("numberOfCorrectTopicAnswers")));
            vb1.getChildren().add(lb[3*i]);
            vb2.getChildren().add(lb[3*i+1]);
            vb3.getChildren().add(lb[3*i+2]);
        }
    }    
    
}

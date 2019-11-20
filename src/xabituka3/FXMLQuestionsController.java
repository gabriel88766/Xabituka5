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

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLQuestionsController implements Initializable {

    boolean question_answer;
    int question_id;
    /**
     * Initializes the controller class.
     */

            
    @FXML        
    TextArea label1;
    @FXML
    Button but1;
    @FXML
    Button but2;
    @FXML
    Pane pane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String st;
        st = getQuestion();
        label1.setText(st);
    } 
    @FXML
    public void OnPressedbut1(MouseEvent evt){
        but1.setStyle("-fx-background-color: rgb(255,70,70)");
    }
    @FXML
    public void OnReleasedbut1(MouseEvent evt){
        but1.setStyle("-fx-background-color: red");
        boolean acertou = (question_answer==false);
        post(acertou);
    }
    @FXML
     public void OnPressedbut2(MouseEvent evt){
        but2.setStyle("-fx-background-color: rgb(70,180,70)");
    }
     @FXML
    public void OnReleasedbut2(MouseEvent evt){
        but2.setStyle("-fx-background-color: green");
        boolean acertou = (question_answer==true);
        post(acertou);
    }
    @FXML
    public void onActionBut1(ActionEvent evt){
        boolean acertou = (question_answer==false);
        post(acertou);
    }
    @FXML
     public void onActionBut2(ActionEvent evt){
        boolean acertou = (question_answer==true);
        post(acertou);
    }
    @FXML
    public void voltar(ActionEvent evt){
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLMenuAluno.fxml"));
            pane.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar lista de assuntos");
        }
    }
     public void post(boolean acertou){
        JSONObject answer = new JSONObject();
        answer.put("userId",FXMLTelaLoginController.user_id);
        answer.put("learnQuestionsId", question_id);
        answer.put("gotItRight", acertou);
        String urlParameters = answer.toString();
        System.out.println(urlParameters);
        String url = Xabituka3.url_xabituca + "learn/answers";
        try {
            Post.posting(url,urlParameters);
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLQuestions.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
     }
    
    public String getQuestion(){
        String url = Xabituka3.url_xabituca+"learn/questions/"+FXMLLearnSubjectsController.id_set+"/"+FXMLTelaLoginController.user_id;
        try{
            String temp = Request.requesting(url);
            JSONObject question_json = new JSONObject(temp);
            String question;
            question = question_json.getString("question");
            this.question_answer = question_json.getBoolean("answer");
            this.question_id = question_json.getInt("id");
            return question;
        }catch(IOException  e){
            String k = " ";
            return k;
        }
    }
    
}

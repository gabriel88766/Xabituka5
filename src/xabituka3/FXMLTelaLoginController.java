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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class FXMLTelaLoginController implements Initializable {

    protected static String theuser;
    protected static int user_id;
    protected static boolean is_aluno=true;
    @FXML
    private TextField txtLogin;

    @FXML
    private Pane pane1;
    
    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button Login;
    
    @FXML
    private void actionHandledLogin(ActionEvent event) throws Exception{
        action();
    }
    @FXML
    private void onKeyPressed(KeyEvent event){
        String s = this.getClass().getName();
        if(event.getCode()==KeyCode.ENTER && "xabituka3.FXMLTelaLoginController"==s){
            action();
        }
    }
    public void action(){
        try{
            String temp;
            String url = Xabituka3.url_xabituca + "/users/login?nickname="+txtLogin.getText().trim()+"&password="+txtSenha.getText();
            temp = Request.requesting(url);
            JSONObject authentication = new JSONObject(temp);
            boolean auth = authentication.getBoolean("auth");
            if(auth){
                
                String user = authentication.getString("userType");
                user_id = authentication.getInt("userId");
                theuser = txtLogin.getText();
                if(user.equals("student")){
                    JOptionPane.showMessageDialog((null), "Bem Vindo, Aluno!");
                    pane1.getChildren().clear();
                    try {
                        Pane tela = (Pane) FXMLLoader.load(getClass()
                            .getResource("FXMLMenuAluno.fxml"));
                        pane1.getChildren().add(tela);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog((null), "Falha ao carregar menu do aluno");
                    }
                }else{
                    JOptionPane.showMessageDialog((null), "Bem Vindo, Professor!");
                    is_aluno=false;
                    try {
                        Pane tela = (Pane) FXMLLoader.load(getClass()
                            .getResource("FXMLMenuProfessor.fxml"));
                        pane1.getChildren().add(tela);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog((null), "Falha ao carregar menu do professor");
                    }
                }  
            }
            else{
                JOptionPane.showMessageDialog((null), "Usuário ou senha incorretos");
            }
        }catch(IOException e){
            JSONObject authentication = null;
            JOptionPane.showMessageDialog((null), "Caractere Inválido ou erro no servidor");
        }
    }
    @FXML 
    public void actionHandledCadastrar(ActionEvent evt2){
        pane1.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLTelaCadastro.fxml"));
            pane1.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar tela de cadastro");
        }
    }
     @FXML
     private void jajaApago(ActionEvent evt){
         JOptionPane.showMessageDialog((null), "Fale com o ADMIN");
         
     }
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 
    



}
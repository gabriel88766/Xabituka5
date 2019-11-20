/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xabituka3;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

public class FXMLTelaCadastroController implements Initializable{

    private boolean aluno;
    private boolean professor;
    @FXML
    private TextField txtLogin;

    @FXML
    private Pane pane1;
    
    @FXML
    private PasswordField txtSenha1;
    @FXML
    private PasswordField txtSenha2;

    @FXML
    private Button Cadastrar;
    @FXML
    private RadioButton rad1;
    @FXML
    private RadioButton rad2;
    
    
    public void set1(ActionEvent evt1){
        if(rad2.isSelected())
            rad2.setSelected(false);
        else{
            rad1.setSelected(true);
        }
    }
    public void set2(ActionEvent evt2){
        if(rad1.isSelected())
            rad1.setSelected(false);
        else{
            rad2.setSelected(true);
        }
    }
    
    @FXML
    private void actionCadastrar(ActionEvent event){
        cadastro();
    }
    
    @FXML
    private void onKeyPressed(KeyEvent event){
        if(event.getCode()==KeyCode.ENTER){
            cadastro();
        }
    }
    
    public void cadastro(){
        String user_type;
        if(rad1.isSelected()){
            aluno=true;
        }else{
            aluno=false;
        }
        if(txtSenha1.getText() == null ? txtSenha2.getText() != null : !txtSenha1.getText().equals(txtSenha2.getText())){
            JOptionPane.showMessageDialog((null), "As senhas são diferentes");
            return;
        }
        if(txtSenha1.getText().length()<4){
            JOptionPane.showMessageDialog((null), "Senha muito curta");
            return;
        }
        if(txtLogin.getText().length()<4){
            JOptionPane.showMessageDialog((null), "Nome de usuário muito curto");
            return;
        }
        if(aluno){
            user_type="student";
        }else{
            user_type="professor";
        }
        String senha = txtSenha1.getText();
        String user = txtLogin.getText();
        boolean auxiliar = cadastrar(user,senha,user_type);
            if(auxiliar){
                pane1.getChildren().clear();
            try {
                JOptionPane.showMessageDialog((null), "Usuário cadastrado com sucesso");
                Pane tela = (Pane) FXMLLoader.load(getClass()
                    .getResource("TelaLogin.fxml"));
                pane1.getChildren().add(tela);
            } catch (IOException e) {
                JOptionPane.showMessageDialog((null), "Falha ao carregar tela de login");
            }
        }
    }
    @FXML
    private void actionLogin(ActionEvent evt1){
        pane1.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("TelaLogin.fxml"));
            pane1.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar tela de login");
        }
    }

@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rad1.setSelected(true);
    } 
    
public static boolean cadastrar(String login,String senha,String user_type){
        // TODO code application logic here
        JSONObject user = new JSONObject();
        user.put("nickname",login);
        user.put("userType", user_type);
        user.put("pw", senha);
        String urlParameters = user.toString();
        System.out.println(urlParameters);
        String url = Xabituka3.url_xabituca + "/users";
        try {
            Post.posting(url,urlParameters);
            return true;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }


}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xabituka3;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.json.JSONArray;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLLearnSubjectsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static SubjectsListAuxiliar[] sub;
    private static String[] subjects_list;
    private long[] ids;
    private static int length;
    private String combo_set;
    public static int id_set=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combobox.setItems(list);
    }
    @FXML
    private Label label1;
    private Label label2;
    public ComboBox<String> combobox;
    public Pane pane;
    public  void actionHandle(ActionEvent evt) throws Throwable{
        if(id_set!=0){
            pane.getChildren().clear();
            try {
                Pane tela = (Pane) FXMLLoader.load(getClass()
                    .getResource("FXMLQuestions.fxml"));
                pane.getChildren().add(tela);
            } catch (IOException e) {
            }
        }else
           JOptionPane.showMessageDialog((null), "Selecione ao mesmo um"); 
    }
    @FXML
    private void actionMenu(ActionEvent evt1){
        pane.getChildren().clear();
        try {
            Pane tela = (Pane) FXMLLoader.load(getClass()
                .getResource("FXMLMenuAluno.fxml"));
            pane.getChildren().add(tela);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((null), "Falha ao carregar menu do aluno");
        }
    }
    
    public void comboChanged(ActionEvent e){
        combo_set = combobox.getValue();
        for(int i=0 ; i<length ;i++ ){
            if(combo_set == sub[i].name)
                id_set=sub[i].id;
        }
        System.out.println(combo_set+" e o numero " + id_set);
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
    
    
   
    
}

class SubjectsListAuxiliar implements Comparable<SubjectsListAuxiliar>{
    public int id;
    public String name;
    public SubjectsListAuxiliar(int id,String name){
        this.id = id;
        this.name = name;
    }
    	public static Comparator<SubjectsListAuxiliar> comparacao
                          = new Comparator<SubjectsListAuxiliar>() {

	    public  int compare(SubjectsListAuxiliar sub1, SubjectsListAuxiliar sub2) {
	    	
	      String subName1 = sub1.name.toUpperCase();
	      String subName2 = sub2.name.toUpperCase();
              
	      return subName1.compareTo(subName2);
	    }

	};

    @Override
    public int compareTo(SubjectsListAuxiliar o) {
        return this.name.compareTo(o.name);
    }
}
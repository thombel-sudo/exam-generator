/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examgenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class MainMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void btnDeleteQuestionClicked(ActionEvent event) {
              try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("deleteQuestion.fxml"));
            stage.setTitle("Видалити питання");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }
    
    
    @FXML
    private void btnShowAllClicked(ActionEvent event) {
              try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("showAllQuestions.fxml"));
            stage.setTitle("Перегляд всіх запитань");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void btnAddQuestionClicked(ActionEvent event) {
              try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addQuestion.fxml"));
            stage.setTitle("Додати питання");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void btnExitClicked(ActionEvent event){
        System.exit(0);
    }
    @FXML
    private void btnCreateClicked(ActionEvent event){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            stage.setTitle("Генерація білетів");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

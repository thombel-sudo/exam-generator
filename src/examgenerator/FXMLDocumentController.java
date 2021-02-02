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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextField txtVarNum;
    
    @FXML
    private Button btnSave, btnAddQuestion, btnDeleteQuestion, btnShowAllQuestions;
    
    @FXML
    private ComboBox cbTheme, cbQuestionNum;
    
    private String savePath;
    Connection co;
    ArrayList<String> questions ;
    File file;
    PrintWriter out;
    FileWriter nFile;
    private Random randomgenerator = new Random();
    
    
    
    
    
    
    
    @FXML
    private void btnDirectoryClick(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        try{
            
            File file = dc.showDialog(null);;
        
                if (file.getAbsolutePath() != null){
                savePath = file.getAbsolutePath();
                System.out.println(file.getAbsolutePath());
                btnSave.setDisable(false);
                }
        }catch (Exception e){
        
        }
    }
    
    @FXML
    private void btnSaveClick() throws IOException{
    questions = new ArrayList<String>();    
                try
                {
                Statement statement = co.createStatement();
                String query = 
                        "SELECT questions "
                        + "FROM " + cbTheme.getValue().toString();
                System.out.println(query);
                ResultSet rs = statement.executeQuery (query);
                while (rs.next())
                        {
                         questions.add(rs.getString("questions"));
                        }
                rs.close();
                statement.close();
                }
                catch (Exception e)
		{
		System.out.println( e.getMessage());
		}    
//        for(int i = 0; i < questions.size(); i++){
//            int lol = randomgenerator.nextInt(questions.size());
//            System.out.println(lol);
//        }
        for(int i = 1; i <= Integer.parseInt(txtVarNum.getText()); i++){
            String text = "";
            file = new File(savePath + "\\" + i + ".txt");
            if(!file.exists()){
            file.createNewFile();
            out = new PrintWriter(new FileWriter(file));
            
            for(int j = 1; j <= Integer.parseInt(cbQuestionNum.getValue().toString()); j++){
                int index = randomgenerator.nextInt(questions.size());
                out.write(j+". " + questions.get(index) + "\r\n");
//                text=text + "\n" + j+". " + questions.get(index) ;
//                text = text + "\n";
            }
           out.close();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Результат дії");
                alert.setHeaderText(null);
                alert.setContentText("Запит виконано!");
                alert.showAndWait();
           }else{
                System.out.println(file.getPath() + "Вже існує");
            }
//            System.out.println(file);
//            
//            out.write(text);
            
//            nFile = new FileWriter(file);
//            nFile.write("trtrtrtrt");
        }
        System.out.println("Done!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try
		{
			Class.forName("org.sqlite.JDBC");
			co = DriverManager.getConnection ( "jdbc:sqlite:src\\database\\questions.db" );
			System.out.println("Connected");
                       
		}
		catch (Exception e)
		{
			System.out.println( e.getMessage());
                       
		}
        cbTheme.getItems().addAll("Біологія","Хімія","Програмування","БД","Фізика","Історія");
        cbQuestionNum.getItems().addAll(1,2,3,4);
    }    
    
}

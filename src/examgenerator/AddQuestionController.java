/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examgenerator;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddQuestionController implements Initializable {

    @FXML
    private ComboBox cbChoice;
    @FXML
    private TextArea txtQuestion;
    Connection co;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void btnAddQuestionClick(ActionEvent event){
        String theme = cbChoice.getValue().toString();
        try
		{
                String text = txtQuestion.getText();
                
		String query = 
		"INSERT INTO "+theme +"(questions) " +
		"VALUES ('" + text + "')";
		Statement statement = co.createStatement();
		statement.executeUpdate(query);
                statement.close();
                System.out.println("Success");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Результат дії");
                alert.setHeaderText(null);
                alert.setContentText("Запит виконано!");
                alert.showAndWait();
		}
		catch (Exception e)
		{
		System.out.println( e.getMessage());
		}
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
        cbChoice.getItems().addAll("Біологія","Хімія","Програмування","БД","Фізика","Історія");
    }    
    
}

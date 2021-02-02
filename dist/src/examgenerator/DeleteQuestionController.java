/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examgenerator;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DeleteQuestionController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox cbTheme,cbQuestion;
    
    Connection co;
    ArrayList<String> questions ;
    @FXML
    private void btnDeleteClick(ActionEvent event){
        try
		{
		String question = cbQuestion.getValue().toString();
                String theme = cbTheme.getValue().toString();
		String query = 
		"DELETE FROM "+ theme + 
		" WHERE questions= " + "'"+ question + "'" +  ";";
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
    @FXML
    private void cbThemeChange(ActionEvent event){
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
                cbQuestion.getItems().addAll(questions);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        // TODO
    }    
    
}

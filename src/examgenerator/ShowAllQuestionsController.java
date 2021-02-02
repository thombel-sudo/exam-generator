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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ShowAllQuestionsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox cbThemes;
    @FXML
    private TextArea txtQuestions;
    Connection co;
    ArrayList<String> questionsShow ;
    @FXML
    private void cbThemesClick(ActionEvent event){
    txtQuestions.setText("");
        questionsShow = new ArrayList<String>();    
                try
                {
                Statement statement = co.createStatement();
                String query = 
                        "SELECT questions "
                        + "FROM " + cbThemes.getValue().toString();
                System.out.println(query);
                ResultSet rs = statement.executeQuery (query);
                while (rs.next())
                        {
                         questionsShow.add(rs.getString("questions"));
                        }
                rs.close();
                statement.close();
                }
                catch (Exception e)
		{
		System.out.println( e.getMessage());
		} 
                for(int i = 1; i <= questionsShow.size(); i++){
                txtQuestions.setText(txtQuestions.getText() + i + ". " +  questionsShow.get(i)+"\n");
                }
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
                 cbThemes.getItems().addAll("Біологія","Хімія","Програмування","БД","Фізика","Історія");
        // TODO
    }    
    
}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author namanmadan
 */
public class NewClass {
    
    public static void main(String[] args) {
        try
        {
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Get a connection
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/QuizData", "naman", "naman"); 
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from MCQ where Question = 'What is true about private constructor?'");
            while(results.next()){
                System.out.println(results.getString(6));
            }
            int count = 1;
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
}

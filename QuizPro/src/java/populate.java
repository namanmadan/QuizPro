/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author namanmadan
 */
public class populate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String UserName = request.getParameter("user_name");
        try (PrintWriter out = response.getWriter()) {
            
            /* TODO output your page here. You may use following sample code. */
            try
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Quiz</title>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='main.css'></link>");
            out.println("</head>");
            out.println("<body>");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //Get a connection
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/QuizData", "naman", "naman"); 
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from MCQ");
            int count = 1;
            
            out.println("<div class='container-fluid'>");
            out.println("<div class='jumbotron'>");
            out.println("<h1 class='quiz_heading'>Welcome "+UserName+" to Quiz App</h1>"); 
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='container'>");
            out.println("<div class='row'>");
            out.println("<div class='col-md-12'>");
            out.println("<form method='post' action='logic'>");
            while(results.next()){
                //Question
                out.println("<div class='card'>");
                out.println("<h3>"+String.valueOf(count)+"). "+results.getString(1)+" </h3>");
                
                //OptionA
                out.println("<div>");
                out.println("<input type=\"radio\" name="+results.getString(1).replace(" ", "__")+" id=\"question-"+count+"-answers-A\" value="+results.getString(2).replace(" ", "__")+" />");
                out.println("<label for=\"question-"+count+"-answers-A\">A) "+results.getString(2)+" </label>");
                out.println("</div>");
                
                //OptionB
                out.println("<div>");
                out.println("<input type=\"radio\" name="+results.getString(1).replace(" ", "__")+" id=\"question-"+count+"-answers-B\" value="+results.getString(3).replace(" ", "__")+" />");
                out.println("<label for=\"question-"+count+"-answers-B\">B) "+results.getString(3)+" </label>");
                out.println("</div>");
                
                //OptionC
                out.println("<div>");
                out.println("<input type=\"radio\" name="+results.getString(1).replace(" ", "__")+" id=\"question-"+count+"-answers-C\" value="+results.getString(4).replace(" ", "__")+" />");
                out.println("<label for=\"question-"+count+"-answers-C\">C) "+results.getString(4)+" </label>");
                out.println("</div>");
                
                //OptionD
                out.println("<div>");
                out.println("<input type=\"radio\" name="+results.getString(1).replace(" ", "__")+" id=\"question-"+count+"-answers-D\" value="+results.getString(5).replace(" ", "__")+" />");
                out.println("<label for=\"question-"+count+"-answers-D\">D) "+results.getString(5)+" </label>");
                out.println("</div>");
                count += 1;
                out.println("</div>");
            }
            out.println("<div class='btnDiv'>");
            out.println("<button class='btn btn-success'>SUBMIT</button>");
            out.println("</form>");
            
            out.println("</div>");
            out.println("</div>");
            out.println("</div");
            out.println("</div"); 
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
            
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

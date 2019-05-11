/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author namanmadan
 */
public class logic extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try
            {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet logic</title>");       
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='main.css'></link>");
            out.println("</head>");
            out.println("<body>");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //Get a connection
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/QuizData", "naman", "naman"); 
            Statement stmt = conn.createStatement();
            ResultSet results;
            Enumeration parameterNames = request.getParameterNames();
            int total = 0;
            int score = 0;
            while (parameterNames.hasMoreElements()) {

            String paramName = (String) parameterNames.nextElement();
            results = stmt.executeQuery("select count(*) from MCQ");
            results.next();
            total = results.getInt(1);
            results = stmt.executeQuery("select * from MCQ where Question = '"+paramName.replace("__", " ")+"'");
            results.next();
            if(results.getString(6).equals(request.getParameter(paramName).replace("__", " "))){
                score += 1;
            }
            
           
            }   
            out.println("<div class='container-fluid'>");
            out.println("<div class='jumbotron'>");
            out.println("<h1 class='quiz_heading'>Quiz App</h1>"); 
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='container'>");
            out.println("<h5 class='final_Score'> Your Score is: "+score+"/"+total+"</h5>");
            //out.println("<button class='btn btn-success' onclick=''>Restart</button>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            }catch (Exception except)
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

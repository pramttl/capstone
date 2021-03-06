/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leon
 */
@WebServlet(name = "login", urlPatterns = {"/userLogin"})
public class login extends HttpServlet {

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
        
        String userName = request.getParameter("id");
        String pw = request.getParameter("pw");
        //validate user and password
        if(validateUser(userName, pw)){
            //Set session for the valid user
            request.getSession().setAttribute("validUser", userName);
            request.getSession().setAttribute("folders", validateFolders("id"));
            response.sendRedirect("index.jsp");
        }
        else {
            //Send the user back to login page with message
            request.setAttribute("validate", "false");
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }
    }

    /**
     * Get the contents from related folders
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    /**
     * 
     * @param id User id
     * @param pw password
     * @return 
     */
    public boolean validateUser(String id, String pw) {
        if(id.equals("id") && pw.equals("password")) {
            return true;
        }
        return false;
    }
    
    public String validateFolders(String id) {
        String folder = null;
        if(id.equals("id")) {
            System.out.println(id);
            folder = "userFolder1";
        }
        return folder;
    }

}

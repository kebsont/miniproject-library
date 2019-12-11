/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guignardnicolas
 */
public class addUserServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {

            Connection con = null;
            try {
                con = DatabaseConnection.initializeDatabase();

                if (request.getParameterMap().containsKey("nom")) {
                    Statement st = null;

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet addBookServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Servlet addBookServlet at " + request.getContextPath() + "</h1>");

                    String nomRecu = request.getParameter("nom");
                    String prenomRecu = request.getParameter("prenom");
                    String dateNaissRecu = request.getParameter("dateNaiss");
                    String emailRecu = request.getParameter("email");
                    String passwordRecu = request.getParameter("password");
                    String typeUserRecu = request.getParameter("typeUser");


                    st = con.createStatement();
                    String reqaddBook = "INSERT INTO USERS (Nom, Prenom, DateNaissance,Profil, Password, Email ) VALUES "
                            + "('" +nomRecu + "','" + prenomRecu + "','" + dateNaissRecu + "','" + typeUserRecu + "','" + passwordRecu + "','" + emailRecu + "')";

                    int r = st.executeUpdate(reqaddBook);
                    out.println("Bienvenue " + nomRecu + " !!!!");
                    out.println("<br>");
                    out.println("Ton mot de passe est : " + prenomRecu);
                    out.println("<br>");
                    out.println("Ton mot de passe est : " + dateNaissRecu);
                    out.println("<br>");
                    out.println("Ton mot de passe est : " + emailRecu);
                    out.println("<br>");
                    out.println("Ton mot de passe est : " + passwordRecu);
                    out.println("<br>");
                    out.println("Ton mot de passe est : " + typeUserRecu);
                    
                    System.out.println("nomRecu : " + nomRecu);
                    response.sendRedirect(request.getContextPath() + "/listUserServlet");                    
                    //r.close();
                    st.close();
                    con.close();

                } else {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/addUser.jsp").forward(request, response);
                }

            } catch (SQLException ex) {
                Logger.getLogger(addBookServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(addBookServlet.class.getName()).log(Level.SEVERE, null, ex);
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

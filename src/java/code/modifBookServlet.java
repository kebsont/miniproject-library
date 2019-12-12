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
 * @author quent
 */
public class modifBookServlet extends HttpServlet {

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

                if (request.getParameterMap().containsKey("titre")) {
                    Statement st = null;

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet modifBookServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Servlet ModifBookServlet at " + request.getContextPath() + "</h1>");

                    String titreRecu = request.getParameter("titre");
                    String auteurRecu = request.getParameter("auteur");
                    String editeurRecu = request.getParameter("editeur");
                    String parutionRecu = request.getParameter("parution");
                    int monId = 21;

                    st = con.createStatement();
                    String reqUpBook = "UPDATE BOOKS SET Titre ='" + titreRecu + "', Auteur ='" + auteurRecu + "', Edition='" + editeurRecu + "',DateParution='" + parutionRecu + "' WHERE IDBooks = " + monId;

                    int r = st.executeUpdate(reqUpBook);
                    out.println("Bienvenue " + titreRecu + " !!!!");
                    out.println("<br>");
                    out.println("Ton mot de passe est : " + auteurRecu);
                    out.println("<br>");
                    out.println("Ton mot de passe est : " + editeurRecu);
                    out.println("<br>");
                    out.println("Ton mot de passe est : " + parutionRecu);
                    System.out.println("titreRecu : " + titreRecu);
                    response.sendRedirect(request.getContextPath() + "/BooksServlet");
                    //r.close();
                    st.close();
                    con.close();

                } else {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/modifBook.jsp").forward(request, response);
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
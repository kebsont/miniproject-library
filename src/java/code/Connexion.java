/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import code.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author kebson
 */
public class Connexion extends HttpServlet {

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
                if (request.getParameter("connexion") != null) {

                    if (request.getParameterMap().containsKey("email") && request.getParameterMap().containsKey("password")) {

                        String emailRecu = request.getParameter("email");
                        String passwordRecu = request.getParameter("password");
                        // A Basic encryptor for password
                        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
                        PreparedStatement st = con
                                .prepareStatement("select * from USERS WHERE email = '" + emailRecu + "'");
                        ResultSet rs = st.executeQuery();
                        String password = null;
                        String profil = null;
                        String prenom = null, nom = null;
                        Long IDUser = null;
                        while (rs.next()) {
                            IDUser = rs.getLong("IDUser");
                            password = rs.getString("password");
                            profil = rs.getString("Profil");
                            prenom = rs.getString("Prenom");
                            nom = rs.getString("Nom");
                            System.out.println("password from the DB WHILE: " + password);
                        }
                        User user = new User(password, nom, prenom, profil);
                        // Compare the given password with the one stored in DB
                        if (passwordEncryptor.checkPassword(passwordRecu, password)) {
                            System.out.println("Identifiants Corrects");
                            HttpSession session = request.getSession();
                            //Add user data session
                            session.setAttribute("IDUser", IDUser);
                            session.setAttribute("prenomFromSession", user.getPrenom());
                            session.setAttribute("nomFromSession", user.getNom());
                            session.setAttribute("profilFromSession", user.getProfil());
                            session.setMaxInactiveInterval(30 * 60);
                            // Redirect to home page 
                            response.sendRedirect(request.getContextPath() + "/BooksServlet");

                        } else {
                            // Redirect with error message
                            System.out.println("Identifiants incorrects");
                            request.setAttribute("error", "Identifiants incorrect");
                            response.sendRedirect(request.getContextPath() + "/Connexion");

                        }

                    }
                } else {

                    this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(addBookServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-1);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(addBookServlet.class.getName()).log(Level.SEVERE, null, ex);
                 System.exit(-2);
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

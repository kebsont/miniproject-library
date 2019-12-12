/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import code.entities.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

/**
 *
 * @author kebson
 */
public class Inscription extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        //this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
        String nomRecu, prenomRecu, loginRecu, emailRecu, passwordRecu, typeUserRecu;
        Date dateNaissRecu;
        Connection con = null;
        try {
            con = DatabaseConnection.initializeDatabase();
            if (request.getParameterMap().containsKey("name")) {
                Statement st = null;
                // try {
                nomRecu = request.getParameter("name");
                prenomRecu = request.getParameter("prenom");
                loginRecu = request.getParameter("login");
                // On parse la data
                String dateNaissStr = request.getParameter("DateNaiss");
                typeUserRecu = "User";

                //dateNaissRecu = new SimpleDateFormat("DD-MMM-YYYY").parse(dateNaissStr);
                emailRecu = request.getParameter("email");
                passwordRecu = request.getParameter("password");
                /* } catch (Exception e) {
                request.setAttribute("erreur", "Informations manquantes, Veuillez vérifier votre formulaire.");
                processRequest(request, response);
                return;
            }*/

                BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
                String encryptedPassword = passwordEncryptor.encryptPassword(passwordRecu);

                User user = new User();
                user.setNom(nomRecu);
                user.setPrenom(prenomRecu);
                user.setLogin(loginRecu);
                user.setEmail(emailRecu);
                user.setDateNaissance(dateNaissStr);
                user.setPassword(encryptedPassword);
                user.setProfil(typeUserRecu);

                System.out.println("Utilisateur inscrit: " + user);

                st = con.createStatement();
                String reqaddUser = "INSERT INTO USERS (Nom, Prenom,Login,  DateNaissance,Profil, Password, Email ) VALUES "
                        + "('" + nomRecu + "','" + prenomRecu + "', '" + loginRecu + "', '" + dateNaissStr + "','" + typeUserRecu + "','" + encryptedPassword + "','" + emailRecu + "')";

                int r = st.executeUpdate(reqaddUser);

                response.sendRedirect(request.getContextPath() + "/Inscription");
                st.close();
                con.close();
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(addBookServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addBookServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }

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

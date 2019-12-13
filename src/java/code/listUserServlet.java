/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import code.entities.Book;
import code.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
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
public class listUserServlet extends HttpServlet {

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
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("select * from USERS");
            ResultSet rs = st.executeQuery();
            List listeUsers = new LinkedList();

            while (rs.next()) {
                Long id = rs.getLong(1);
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String dateNaissance = rs.getString("DateNaissance");
                String profil = rs.getString("Profil");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                String login = rs.getString("login");
                User user = new User(id, nom, prenom, login, dateNaissance, profil, password, email);
                // Add user created in the users list
                listeUsers.add(user);

            }
            request.setAttribute("users", listeUsers);
            this.getServletContext().getRequestDispatcher("/WEB-INF/listUser.jsp").forward(request, response);
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BooksServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BooksServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-2);
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
        // If the Modify button is clicked
        if (request.getParameter("Modifier") != null) {
            try {
                int monId = Integer.parseInt(request.getParameter("Modifier"));
                User user = null;
                String query = "select * from USERS WHERE IDUser ='" + monId + "' ";
                Connection con = DatabaseConnection.initializeDatabase();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Long id = rs.getLong(1);
                    String nom = rs.getString("Nom");
                    String prenom = rs.getString("Prenom");
                    String dateNaissance = rs.getString("DateNaissance");
                    String profil = rs.getString("Profil");
                    String password = rs.getString("Password");
                    String email = rs.getString("Email");
                    String login = rs.getString("login");
                    user = new User(id, nom, prenom, login, dateNaissance, profil, password, email);
                }
                rs.close();
                con.close();

                request.setAttribute("user", user);
                this.getServletContext().getRequestDispatcher("/WEB-INF/modifUser.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(listUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-3);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(listUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-4);
            }

        } else if (request.getParameter("Supprimer") != null) {
            deleteElement(Integer.parseInt(request.getParameter("Supprimer")), "IDUser", "USERS");
            response.sendRedirect(request.getContextPath() + "/listUserServlet");
        } else if (request.getParameter("AddUser") != null) {
            response.sendRedirect(request.getContextPath() + "/addUserServlet");
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

    // Get an element and Delete it
    int deleteElement(int id, String NomID, String table) {
        Connection con;
        int rs = 0;
        try {
            con = DatabaseConnection.initializeDatabase();
            PreparedStatement st = con
                    .prepareStatement("DELETE FROM " + table + " WHERE " + NomID + " = " + id);
            System.out.println("Ma requete est " + st);
            rs = st.executeUpdate();
            System.out.println("le rs est " + rs);
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(listUserServlet.class.getName()).log(Level.SEVERE, null, ex);
             System.exit(-5);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(listUserServlet.class.getName()).log(Level.SEVERE, null, ex);
             System.exit(-6);
        }
        return rs;
    }

}

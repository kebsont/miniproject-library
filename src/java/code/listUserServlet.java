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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            System.out.println("con: " + con);
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
                User user = new User(id, nom, prenom,login, dateNaissance, profil, password, email);

                listeUsers.add(user);

            }
            System.out.println("Users: " + listeUsers);
            request.setAttribute("users", listeUsers);
            this.getServletContext().getRequestDispatcher("/WEB-INF/listUser.jsp").forward(request, response);
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BooksServlet.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BooksServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        if (request.getParameter("Modifier") != null) {
            request.setAttribute("idUser", request.getParameter("Modifier")); 
            response.sendRedirect(request.getContextPath()+ "/modifUserServlet");

        } else if (request.getParameter("Supprimer") != null) {
            deleteElement(Integer.parseInt(request.getParameter("Supprimer")), "IDUser", "USERS");
            response.sendRedirect(request.getContextPath()+ "/listUserServlet");
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

int deleteElement(int id, String NomID, String table) {
        Connection con;
        int rs = 0;
        try {
            con = DatabaseConnection.initializeDatabase();
            System.out.println("con: " + con);
            PreparedStatement st = con
                    .prepareStatement("DELETE FROM " + table + " WHERE " + NomID + " = " + id);
            System.out.println("Ma requete est " + st);
            rs = st.executeUpdate();
            System.out.println("le rs est " + rs);
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(listUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(listUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

}

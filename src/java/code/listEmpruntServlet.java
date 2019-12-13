/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import code.entities.Emprunt;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
public class listEmpruntServlet extends HttpServlet {

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
            // Get all loaned books
            PreparedStatement st = con
                    .prepareStatement("SELECT EMPRUNTS.*, USERS.*, BOOKS.* FROM EMPRUNTS JOIN USERS ON EMPRUNTS.UserID = USERS.IDUser JOIN BOOKS ON EMPRUNTS.BookID = BOOKS.IDBooks");
            ResultSet rs = st.executeQuery();
            List listeEmprunt = new LinkedList();

            while (rs.next()) {
                Long id = rs.getLong("ID");
                Long idBook = rs.getLong("BookID");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String titre = rs.getString("Titre");
                String auteur = rs.getString("Auteur");
                Date dateEmprunt = rs.getDate("DateEmprunt");
                Emprunt emprunt = new Emprunt(id, idBook, titre, auteur, nom, prenom, dateEmprunt);

                listeEmprunt.add(emprunt); // Fill them in the book list

            }
            // pass the list to the emprunts variable
            request.setAttribute("emprunts", listeEmprunt);
            this.getServletContext().getRequestDispatcher("/WEB-INF/listEmprunt.jsp").forward(request, response);
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(listEmpruntServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(listEmpruntServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        if (request.getParameter("Retour") != null) {
            retourLivre(Integer.parseInt(request.getParameter("Retour")));
            deleteEmprunt(Integer.parseInt(request.getParameter("Retour")));
            response.sendRedirect(request.getContextPath() + "/listEmpruntServlet");
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
    // Function to  get back a book

    int retourLivre(int idBook) {
        Connection con;
        int rs = 0;
        try {
            con = DatabaseConnection.initializeDatabase();
            // Give it back with the given ID
            PreparedStatement st = con
                    .prepareStatement("UPDATE BOOKS SET Disponibilite = 1 WHERE IDBooks = " + idBook);
            rs = st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(listEmpruntServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-3);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(listEmpruntServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-4);
        }
        return rs;
    }

    // Function to remove the loaned book
    int deleteEmprunt(int id) {
        Connection con;
        int rs = 0;
        try {
            con = DatabaseConnection.initializeDatabase();
            // Delete it with the given ID
            PreparedStatement st = con
                    .prepareStatement("DELETE FROM EMPRUNTS WHERE BookID = " + id);
            System.out.println("Ma requete est " + st);
            rs = st.executeUpdate();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(listEmpruntServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-5);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(listEmpruntServlet.class.getName()).log(Level.SEVERE, null, ex);
             System.exit(-6);
        }
        return rs;
    }
}

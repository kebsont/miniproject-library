/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import code.entities.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
                // If the form is filled with the title
                if (request.getParameterMap().containsKey("titre")) {
                    Statement st = null;

                    String titreRecu = request.getParameter("titre");
                    String auteurRecu = request.getParameter("auteur");
                    String editeurRecu = request.getParameter("editeur");
                    String parutionRecu = request.getParameter("parution");
                    Integer disponibilite = 1;
                    Book livre = new Book(titreRecu, auteurRecu, editeurRecu, parutionRecu, disponibilite.byteValue());
                    // Update the filled book
                    st = con.createStatement();
                    
                    editBook(livre);

                    st = con.createStatement();
                    //String reqUpBook = "UPDATE BOOKS SET Titre ='" + titreRecu + "', Auteur ='" + auteurRecu + "', Edition='" + editeurRecu + "',DateParution='" + parutionRecu + "' WHERE IDBooks = " + monId;

                    //int r = st.executeUpdate(reqUpBook);
                   
                    response.sendRedirect(request.getContextPath() + "/BooksServlet");
                    st.close();
                    con.close();

                } else {
                    // redirect if any actions
                    this.getServletContext().getRequestDispatcher("/WEB-INF/modifBook.jsp").forward(request, response);
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

    int editBook(Book book) {
        Connection con;
        int rs = 0;
        try {
            con = DatabaseConnection.initializeDatabase();
            System.out.println("con: " + con);
            // Update the book with the given ID
            PreparedStatement st = con
                    .prepareStatement("UPDATE BOOKS SET Titre ='" + book.getTitre() + "', Auteur ='" + book.getAuteur() + "', Edition='" + book.getEdition() + "',DateParution='" + book.getDateParution() + "' WHERE IDBooks = " + book.getId());

            System.out.println("Ma requete est " + st);
            rs = st.executeUpdate();
            System.out.println("le rs est " + rs);
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
}

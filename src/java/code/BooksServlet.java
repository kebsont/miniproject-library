package code;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import code.DatabaseConnection;
import java.sql.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import code.entities.Book;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kebson
 */
@WebServlet(name = "BooksServlet", urlPatterns = {"/BooksServlet"})
public class BooksServlet extends HttpServlet {

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

           /* if (request.getParameter("fctSearch") != null) {
             
            } else {*/

                System.out.println("Tu n'as rien recherché");

                PreparedStatement st = con
                        .prepareStatement("select * from BOOKS");
                ResultSet rs = st.executeQuery();
                List listeLivres = new LinkedList();

                while (rs.next()) {
                    Long id = rs.getLong(1);
                    String titre = rs.getString(2);
                    String auteur = rs.getString(3);
                    String edition = rs.getString(4);
                    Date dateParution = rs.getDate("DateParution");
                    Book livre = new Book(id, titre, auteur, edition, dateParution);

                    listeLivres.add(livre);

                }
                System.out.println("Livres: " + listeLivres);
                request.setAttribute("livres", listeLivres);

                this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

                rs.close();
                con.close();

            //}
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
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            String titreSearch = request.getParameter("fctSearch");
            System.out.println("Tu recherches le livre: " + titreSearch);
            ArrayList al = null;
            Book book = new Book();
            ArrayList bookSearchAL = new ArrayList();
            String query = "select * from BOOKS WHERE Titre LIKE '%" + titreSearch + "%' ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            List listeLivres = new LinkedList();
            
            while (rs.next()) {
                Long id = rs.getLong(1);
                String titre = rs.getString(2);
                String auteur = rs.getString(3);
                String edition = rs.getString(4);
                Date dateParution = rs.getDate("DateParution");
                Book livre = new Book(id, titre, auteur, edition, dateParution);
                
                listeLivres.add(livre);
                
            }
            System.out.println("Livressssssssssss: " + listeLivres);
            request.setAttribute("livres", listeLivres);
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/bookSearch.jsp").forward(request, response);
            
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BooksServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BooksServlet.class.getName()).log(Level.SEVERE, null, ex);
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

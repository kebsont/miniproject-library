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
import javax.servlet.http.HttpSession;

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
                String dateParution = rs.getString("DateParution");
                Byte disponibilite = rs.getByte("Disponibilite");

                Book livre = new Book(id, titre, auteur, edition, dateParution, disponibilite);

                listeLivres.add(livre);

            }
            System.out.println("Livres: " + listeLivres);
            request.setAttribute("livres", listeLivres);

            this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

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
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            String titreSearch = request.getParameter("fctSearch");
            Book livre = null;
            if (titreSearch != null) {

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
                    String dateParution = rs.getString("DateParution");
                    Byte disponibilite = rs.getByte("Disponibilite");

                    livre = new Book(id, titre, auteur, edition, dateParution, disponibilite);

                    listeLivres.add(livre);

                }
                System.out.println("Booksssssssssss: " + listeLivres);
                request.setAttribute("livres", listeLivres);

                this.getServletContext().getRequestDispatcher("/WEB-INF/bookSearch.jsp").forward(request, response);

                rs.close();
                con.close();
            } else if (request.getParameter("Modifier") != null) {
                int monId = Integer.parseInt(request.getParameter("Modifier"));

                String query = "select * from BOOKS WHERE IDBooks ='" + monId + "' ";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                Book BookToEdit = null;
                while (rs.next()) {
                    Long id = rs.getLong(1);
                    String titre = rs.getString(2);
                    String auteur = rs.getString(3);
                    String edition = rs.getString(4);
                    String dateParution = rs.getString("DateParution");
                    Byte disponibilite = rs.getByte("Disponibilite");

                    BookToEdit = new Book(id, titre, auteur, edition, dateParution, disponibilite);
                }
                System.out.println("Le Livre a édité est: " + BookToEdit.getAuteur());
                rs.close();
                con.close();

                request.setAttribute("auteur", BookToEdit.getAuteur());
                request.setAttribute("edition", BookToEdit.getEdition());
                request.setAttribute("titre", BookToEdit.getTitre());
                //response.sendRedirect(request.getContextPath() + "/modifBookServlet");
                this.getServletContext().getRequestDispatcher("/WEB-INF/modifBook.jsp").forward(request, response);

            } else if (request.getParameter("Supprimer") != null) {
                deleteElement(Integer.parseInt(request.getParameter("Supprimer")), "IDBooks", "BOOKS");
                response.sendRedirect(request.getContextPath() + "/BooksServlet");
            } else if (request.getParameter("Emprunter") != null) {
                emprunterLivre(Integer.parseInt(request.getParameter("Emprunter")), request);
                response.sendRedirect(request.getContextPath() + "/BooksServlet");
            }else if (request.getParameter("Ajouter") != null) { 
                response.sendRedirect(request.getContextPath() + "/addBookServlet");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BooksServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-3);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BooksServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-4);
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
            System.exit(-6);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(listUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-7);
        }
        return rs;
    }
    // Lon a book with the given ID
    int emprunterLivre(int id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long IdUser = (Long) session.getAttribute("IDUser");

        Connection con;
        int rs = 0;
        try {
            con = DatabaseConnection.initializeDatabase();
            System.out.println("con: " + con);
            Statement st = con.createStatement();
            // Execute 2 request with a transaction
            rs = st.executeUpdate("UPDATE BOOKS SET Disponibilite = 0 WHERE IDBooks = " + id);
            st.executeUpdate("INSERT INTO EMPRUNTS (UserID, BookID, DateEmprunt) VALUES ("+ IdUser+", " + id + " ,NOW());");
            System.out.println("le rs est " + rs);
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(listUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-8);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(listUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-9);
        }
        return rs;
    }

}

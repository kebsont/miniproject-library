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
public class addBookServlet extends HttpServlet {

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
                // if form is filled
                if (request.getParameterMap().containsKey("titre")) {
                    Statement st = null;

                    String titreRecu = request.getParameter("titre");
                    String auteurRecu = request.getParameter("auteur");
                    String editeurRecu = request.getParameter("editeur");
                    String parutionRecu = request.getParameter("parution");
                    // Insert into DB
                    st = con.createStatement();
                    String reqaddBook = "INSERT INTO BOOKS (Titre, Auteur, Edition, DateParution) VALUES ('" + titreRecu + "','" + auteurRecu + "','" + editeurRecu + "','" + parutionRecu + "')";

                    int r = st.executeUpdate(reqaddBook);

                    response.sendRedirect(request.getContextPath() + "/BooksServlet");
                    st.close();
                    con.close();

                } else {
                    this.getServletContext().getRequestDispatcher("/WEB-INF/addBook.jsp").forward(request, response);
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

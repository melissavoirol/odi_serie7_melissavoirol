package ch.hearc.ig.odi.serie7.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Melissa Voirol <melissa.voirol@he-arc.ch>
 */
public class DisplayInformations extends HttpServlet {

    protected String param = null;
    protected String value = null;

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        for (Enumeration e = config.getInitParameterNames(); e.hasMoreElements();) {
            this.param = (String) e.nextElement();
            this.value = config.getInitParameter("prenom");
        }
    }

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
        request.setCharacterEncoding("UTF-8");

        String prenom = request.getParameter("prenom");
        HttpSession session = request.getSession();
        if (session == null) {
            System.out.println("Pas de session");
        } else {
            if (prenom != null) {
                if (!session.isNew()) {
                    session.invalidate();
                    session = request.getSession();
                }
                session.setAttribute("prenom", prenom);
            }
            prenom = (String) session.getAttribute("prenom");
        }

        String id = session.getId();
        Date date = new Date(session.getCreationTime());

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DisplayInformations</title>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet DisplayInformations at " + request.getContextPath() + "</h1>");
            //out.print("Prénom passé en paramètre : ");
            out.print("Prénom stocké dans un attribut de session : ");
            out.println(prenom);
            out.print("<br>");
            out.print("Valeur du paramètre \"" + this.param + "\" mis dans le web.xml : ");
            out.println(this.value);
            out.print("<br>");
            out.println("<h1>Informations liées à la session : </h1>");
            out.print("- id : ");
            out.println(id);
            out.print("<br>");
            out.print("- date de création : ");
            out.println(date);
            out.println("</body>");
            out.println("</html>");
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

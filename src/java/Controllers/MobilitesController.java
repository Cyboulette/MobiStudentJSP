package Controllers;

import Models.DemandeMobilite;
import Models.Etudiant;
import Models.Universite;
import Utils.ControllerUtilsInterface;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Quentin DESBIN, Arnaud HERTEL
 */
public class MobilitesController extends HttpServlet {

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
        // La session
        HttpSession session = request.getSession();
        
        // Le dispatcher de requetes
        RequestDispatcher rd = null;
        
        String action = request.getParameter("action"); // On récupère l'action passée en GET ou POST
        if(action != null && !action.isEmpty()) { // Si on est sûr que l'action a bien été passée
            switch(action.toUpperCase()) { // On passe en majuscule pour ignorer la casse
                case "SEARCH":
                    request.setAttribute("etudiants", Etudiant.getAll());
                    request.setAttribute("universites", Universite.getAll());
                    ControllerUtilsInterface.redirectTo("/search_mobilites.jsp", request, response);
                    break;
                case "DEMANDES_MOBILITES_ETUDIANTS":
                    int num_etudiant = Integer.parseInt(request.getParameter("num_etudiant"));
                    request.setAttribute("demandes", DemandeMobilite.getMobisByEtud(num_etudiant));
                    ControllerUtilsInterface.redirectTo("/demandes_etudiants.jsp", request, response);
                case "DEMANDES_MOBILITES_UNIVERSITES":
                    String nom = request.getParameter("nom_univ");
                    request.setAttribute("demandes", DemandeMobilite.getMobisByUniv(nom));
                    ControllerUtilsInterface.redirectTo("/demandes_universites.jsp", request, response);
                case "DEMANDES_MOBILITES_DIPLOMES":
                    String intitule = request.getParameter("intitule");
                    request.setAttribute("demandes", DemandeMobilite.getMobisByDiplome(intitule));
                    ControllerUtilsInterface.redirectTo("/demandes_diplomes.jsp", request, response);
                default:
                    ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    break;
            }
        } else {
            request.setAttribute("error", "L'action demandée n'existe pas");
            ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
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

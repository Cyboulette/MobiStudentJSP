package Controllers;

import Models.DemandeMobilite;
import Models.Diplome;
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
        if (action != null && !action.isEmpty()) { // Si on est sûr que l'action a bien été passée
            switch (action.toUpperCase()) { // On passe en majuscule pour ignorer la casse
                case "SEARCH":
                    request.setAttribute("etudiants", Etudiant.getAll());
                    request.setAttribute("universites", Universite.getAll());
                    request.setAttribute("diplomes", Diplome.getAll());
                    if(request.getParameter("id_etudiant") != null){
                        int idE = Integer.parseInt(request.getParameter("id_etudiant"));
                        request.setAttribute("demandes", DemandeMobilite.getMobisByEtud(idE));                       
                    }
                    else if(request.getParameter("num_univ") != null){
                        int id = Integer.parseInt(request.getParameter("num_univ"));
                        request.setAttribute("demandes", DemandeMobilite.getMobisByUniv(id));                       
                    }
                    else if(request.getParameter("id_diplome") != null){
                        int idD = Integer.parseInt(request.getParameter("id_diplome"));
                        request.setAttribute("demandes", DemandeMobilite.getMobisByDiplome(idD));                        
                    }
                    ControllerUtilsInterface.redirectTo("/search_mobilites.jsp", request, response);
                    break;
                case "ADD":
                    request.setAttribute("etudiants", Etudiant.getAll());
                    request.setAttribute("diplomes", Diplome.getAll());
                    ControllerUtilsInterface.redirectTo("/addMobi.jsp", request, response);
                    break;
                case "ADD_MOBILITES":
                    DemandeMobilite mobilite = new DemandeMobilite();
                    mobilite.setIdDiplome(Integer.parseInt(request.getParameter("id_Diplome")));
                    mobilite.setIdEtudiant(Integer.parseInt(request.getParameter("id_Etudiant")));
                    mobilite.setDate_depot(request.getParameter("date_depot"));
                    mobilite.setEtat(request.getParameter("etat"));
                    boolean ajout = mobilite.add();
                    if (ajout) {
                        session.setAttribute("message", "ok");
                        session.setAttribute("mobilite", mobilite);
                        request.setAttribute("diplomes", Diplome.getAll());

                        ControllerUtilsInterface.redirectTo("/demandes_diplomes.jsp", request, response);
                    } else {
                        session.setAttribute("error", "Erreur dans l'ajout");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    }
                    break;
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

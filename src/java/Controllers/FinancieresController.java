package Controllers;

import Models.Contrat;
import Models.DemandeFinanciere;
import Models.DemandeMobilite;
import Models.Diplome;
import Models.Etudiant;
import Models.Programme;
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
 * @author ahertel
 */
public class FinancieresController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
// La session
        HttpSession session = request.getSession();

        // Le dispatcher de requetes
        RequestDispatcher rd = null;

        String action = request.getParameter("action"); // On récupère l'action passée en GET ou POST
        if (action != null && !action.isEmpty()) { // Si on est sûr que l'action a bien été passée
            switch (action.toUpperCase()) { // On passe en majuscule pour ignorer la casse
                case "SEARCH":
                    request.setAttribute("contrats", Contrat.getAll());
                    if (request.getParameter("contrat_id") != null) {
                        int contrat_id = Integer.parseInt(request.getParameter("contrat_id"));
                        request.setAttribute("financieres", DemandeFinanciere.getDemandeFiByContrat(contrat_id));
                    }
                    ControllerUtilsInterface.redirectTo("/search_financieres.jsp", request, response);
                    break;
                case "ADD":
                    request.setAttribute("contrats", Contrat.getAll());
                    ControllerUtilsInterface.redirectTo("/addFin.jsp", request, response);
                    break;
                case "ADD_FINANCIERES":
                    DemandeFinanciere fin = new DemandeFinanciere();
                    fin.setIdContrat(Integer.parseInt(request.getParameter("contrat_id")));
                    fin.setDate_depot(request.getParameter("date_depot"));
                    fin.setEtat(request.getParameter("etat"));
                    fin.setMontant_accorde(Double.parseDouble(request.getParameter("montant_accorde")));
                    boolean ajout = fin.add();
                    if (ajout) {
                        request.setAttribute("success", "La demande financière a bien été ajoutée");
                        request.setAttribute("contrats", Contrat.getAll());
                        ControllerUtilsInterface.redirectTo("/search_financieres.jsp", request, response);
                    } else {
                        request.setAttribute("error", "Erreur dans l'ajout");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    }
                    break;
                case "EDIT":
                    int idF = Integer.parseInt(request.getParameter("id"));
                    DemandeFinanciere demandeFinanciere = DemandeFinanciere.getDemandeFiById(idF);
                    if(demandeFinanciere == null) {
                        request.setAttribute("error", "Cette demande financière n'existe pas");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    } else {
                        request.setAttribute("financiere", demandeFinanciere);
                        request.setAttribute("contrats", Contrat.getAll());
                        ControllerUtilsInterface.redirectTo("/editFin.jsp", request, response);
                    }
                    break;
                case "EDIT_FINANCIERE":
                    if(request.getParameter("idF") != null) {
                        idF = Integer.parseInt(request.getParameter("idF"));
                        demandeFinanciere = DemandeFinanciere.getDemandeFiById(idF);
                        if(demandeFinanciere == null) {
                            request.setAttribute("error", "Cette demande financière n'existe pas");
                            ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                        } else {
                            demandeFinanciere.setIdContrat(Integer.parseInt(request.getParameter("contrat_id")));
                            demandeFinanciere.setEtat(request.getParameter("etat"));
                            demandeFinanciere.setMontant_accorde(Double.parseDouble(request.getParameter("montant_accorde")));
                            boolean edit = demandeFinanciere.edit();
                            if(edit) {
                                request.setAttribute("success", "Cette demande financière a bien été modifiée");
                            } else {
                                request.setAttribute("error", "Impossible de modifier cette demande financière");
                            }
                            
                            request.setAttribute("financiere", demandeFinanciere);
                            request.setAttribute("contrats", Contrat.getAll());
                            ControllerUtilsInterface.redirectTo("/editFin.jsp", request, response);
                        }
                    } else {
                        request.setAttribute("error", "Vous devez préciser un id de demande financière");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    }
                    break;
                case "DELETE":
                    idF = Integer.parseInt(request.getParameter("id"));
                    demandeFinanciere = DemandeFinanciere.getDemandeFiById(idF);
                    if(demandeFinanciere == null) {
                        request.setAttribute("error", "Cette demande financière n'existe pas");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    } else {
                        request.setAttribute("financiere", demandeFinanciere);
                        ControllerUtilsInterface.redirectTo("/deleteFin.jsp", request, response);
                    }
                    break;
                case "DELETE_FINANCIERE":
                    idF = Integer.parseInt(request.getParameter("idF"));
                    demandeFinanciere = DemandeFinanciere.getDemandeFiById(idF);
                    if(demandeFinanciere == null) {
                        request.setAttribute("error", "Cette demande financière n'existe pas");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    } else {
                        boolean delete = demandeFinanciere.delete();
                        if(delete) {
                            request.setAttribute("success", "Demande financière supprimée avec succès");
                        } else {
                            request.setAttribute("error", "Impossible de supprimer cette demande financière");
                        }
                        request.setAttribute("contrats", Contrat.getAll());
                        ControllerUtilsInterface.redirectTo("/search_financieres.jsp", request, response);
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

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
        request.setCharacterEncoding("UTF-8");
        // La session
        HttpSession session = request.getSession();

        // Le dispatcher de requetes
        RequestDispatcher rd = null;

        String action = request.getParameter("action"); // On récupère l'action passée en GET ou POST
        if (action != null && !action.isEmpty()) { // Si on est sûr que l'action a bien été passée
            switch (action.toUpperCase()) { // On passe en majuscule pour ignorer la casse
                case "SEARCH":
                    // On récupérer tous les étudiants, toutes les universités et tous les diplômes
                    request.setAttribute("etudiants", Etudiant.getAll());
                    request.setAttribute("universites", Universite.getAll());
                    request.setAttribute("diplomes", Diplome.getAll());
                    
                    request.setAttribute("tabActive", "v-pills-diplomes"); // Par défaut la tab active c'est diplômes
                    
                    // Si on passe un id_etudiant, on recherche les demandes par etudiant
                    if(request.getParameter("id_etudiant") != null && !request.getParameter("id_etudiant").equalsIgnoreCase("null")){
                        int idE = Integer.parseInt(request.getParameter("id_etudiant"));
                        request.setAttribute("demandes", DemandeMobilite.getMobisByEtud(idE));
                        request.setAttribute("tabActive", "v-pills-etudiants");
                    } // Si on passe un num_univ, on recherche les demandes par université
                    else if(request.getParameter("num_univ") != null && !request.getParameter("num_univ").equalsIgnoreCase("null")){
                        int id = Integer.parseInt(request.getParameter("num_univ"));
                        request.setAttribute("demandes", DemandeMobilite.getMobisByUniv(id));
                        request.setAttribute("tabActive", "v-pills-universites");
                    } // Si on passe un id_diplome, on recherche les demandes par diplôme
                    else if(request.getParameter("id_diplome") != null && !request.getParameter("id_diplome").equalsIgnoreCase("null")){
                        int idD = Integer.parseInt(request.getParameter("id_diplome"));
                        request.setAttribute("demandes", DemandeMobilite.getMobisByDiplome(idD));
                        request.setAttribute("tabActive", "v-pills-diplomes");
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
                        request.setAttribute("success", "Votre demande de mobilité a bien été ajoutée");
                        request.setAttribute("mobilite", mobilite);
                        request.setAttribute("etudiants", Etudiant.getAll());
                        request.setAttribute("universites", Universite.getAll());
                        request.setAttribute("diplomes", Diplome.getAll());
                        request.setAttribute("tabActive", "v-pills-diplomes"); // Par défaut la tab active c'est diplômes

                        ControllerUtilsInterface.redirectTo("/search_mobilites.jsp", request, response);
                    } else {
                        request.setAttribute("error", "Erreur dans l'ajout de votre demande de mobilité, contactez un administrateur");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    }
                    break;
                case "EDIT":
                    int idM = Integer.parseInt(request.getParameter("id"));
                    DemandeMobilite demandeMobilite = DemandeMobilite.getMobiById(idM);
                    if(demandeMobilite == null) {
                        request.setAttribute("error", "Cette demande de mobilité n'existe pas");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    } else {
                        request.setAttribute("mobilite", demandeMobilite);
                        request.setAttribute("etudiants", Etudiant.getAll());
                        request.setAttribute("diplomes", Diplome.getAll());
                        ControllerUtilsInterface.redirectTo("/editMobi.jsp", request, response);
                    }
                    break;
                case "EDIT_MOBILITE":
                    if(request.getParameter("idM") != null) {
                        idM = Integer.parseInt(request.getParameter("idM"));
                        demandeMobilite = DemandeMobilite.getMobiById(idM);
                        if(demandeMobilite == null) {
                            request.setAttribute("error", "Cette demande de mobilité n'existe pas");
                            ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                        } else {
                            demandeMobilite.setIdDiplome(Integer.parseInt(request.getParameter("id_Diplome")));
                            demandeMobilite.setIdEtudiant(Integer.parseInt(request.getParameter("id_Etudiant")));
                            demandeMobilite.setEtat(request.getParameter("etat"));
                            boolean edit = demandeMobilite.edit();
                            if(edit) {
                                request.setAttribute("success", "Cette demande de mobilité a bien été modifiée");
                            } else {
                                request.setAttribute("error", "Impossible de modifier cette demande de mobilité");
                            }
                            
                            request.setAttribute("mobilite", demandeMobilite);
                            request.setAttribute("etudiants", Etudiant.getAll());
                            request.setAttribute("diplomes", Diplome.getAll());
                            ControllerUtilsInterface.redirectTo("/editMobi.jsp", request, response);
                        }
                    } else {
                        request.setAttribute("error", "Vous devez préciser un id de demande de mobilité");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    }
                    break;
                case "DELETE":
                    idM = Integer.parseInt(request.getParameter("id"));
                    demandeMobilite = DemandeMobilite.getMobiById(idM);
                    if(demandeMobilite == null) {
                        request.setAttribute("error", "Cette demande de mobilité n'existe pas");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    } else {
                        request.setAttribute("mobilite", demandeMobilite);
                        ControllerUtilsInterface.redirectTo("/deleteMobi.jsp", request, response);
                    }
                    break;
                case "DELETE_MOBILITE":
                    idM = Integer.parseInt(request.getParameter("idM"));
                    demandeMobilite = DemandeMobilite.getMobiById(idM);
                    if(demandeMobilite == null) {
                        request.setAttribute("error", "Cette demande de mobilité n'existe pas");
                        ControllerUtilsInterface.redirectTo("/index.jsp", request, response);
                    } else {
                        boolean delete = demandeMobilite.delete();
                        if(delete) {
                            request.setAttribute("success", "Demande de mobilité supprimée avec succès");
                        } else {
                            request.setAttribute("error", "Impossible de supprimer cette demande de mobilité");
                        }
                        request.setAttribute("etudiants", Etudiant.getAll());
                        request.setAttribute("universites", Universite.getAll());
                        request.setAttribute("diplomes", Diplome.getAll());
                        request.setAttribute("tabActive", "v-pills-diplomes"); // Par défaut la tab active c'est diplômes
                        ControllerUtilsInterface.redirectTo("/search_mobilites.jsp", request, response);
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

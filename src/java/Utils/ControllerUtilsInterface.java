package Utils;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quentin DESBIN, Arnaud HERTEL
 */
public class ControllerUtilsInterface {
    public static void redirectTo(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
    
    public static String active(HttpServletRequest request, String urlToMatch, String actionToMatch) {
        boolean isActive = true;
        String url = request.getRequestURI();
        String queryString = request.getQueryString();
        if(url != null) {
            url = url.replaceAll("/MobiStudentJSP/", "");
            url = url.replaceAll(".jsp", "");
            if(!url.equalsIgnoreCase(urlToMatch)) isActive = false;
            if(isActive && queryString != null) {
                queryString = queryString.replaceAll("action=", "");
                if(!queryString.equalsIgnoreCase(actionToMatch)) isActive = false;
            }
        }
        return (isActive?"active":"");
    }
}

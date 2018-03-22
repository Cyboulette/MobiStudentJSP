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
public interface ControllerUtilsInterface {
    public static void redirectTo(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}

package utility;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class Redirector {

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        // Instantiate a request dispatcher for the JSP
        RequestDispatcher view =
                request.getRequestDispatcher(url);

        // Use the request dispatcher to ask the Container to crank up the JSP,
        // sending it the request and response
        view.forward(request, response);
    }
}

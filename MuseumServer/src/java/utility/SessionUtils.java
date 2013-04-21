/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javax.servlet.http.HttpSession;

/**
 *
 * @author neil
 */
public class SessionUtils {

    public static void clearSignUpSessionData(HttpSession session) {

        session.removeAttribute("multicastGroup");
        session.removeAttribute("multicastGroupId");
        session.removeAttribute("visitors");
        session.removeAttribute("tourId");
        session.removeAttribute("questionSet");
        session.removeAttribute("currentVisitor");
    }
}

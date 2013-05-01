/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businessDomainObjects.AudioManager;
import businessDomainObjects.ExhibitManager;
import businessDomainObjects.HandsetAccessManager;
import businessDomainObjects.TourManager;
import handsetInteraction.AudioPortManager;
import handsetInteraction.HandsetRequestManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import visitorsAndGroups.GroupManager;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 */
@WebServlet(name = "HandsetRequestInterface", urlPatterns = {"/HandsetRequestInterface"})
public class HandsetRequestInterface extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();
        
        String serverAddress = request.getServerName();

        // Get the managers we need and bundle them into the HandsetRequestManager
        HandsetAccessManager ham = (HandsetAccessManager) ctx.getAttribute("handsetAccessManager");
        GroupManager gm = (GroupManager) ctx.getAttribute("groupManager");
        TourManager tm = (TourManager) ctx.getAttribute("tourManager");
        ExhibitManager em = (ExhibitManager) ctx.getAttribute("exhibitManager");
        AudioManager am = (AudioManager) ctx.getAttribute("audioManager");
        AudioPortManager apm = (AudioPortManager) ctx.getAttribute("audioPortManager");
        HandsetRequestManager hrm = new HandsetRequestManager(ham, gm, tm, em, am, apm, serverAddress);
        // Get the authentication params
        String macAddress = (String) request.getParameter("mac");
        int pin = Integer.parseInt((String) request.getParameter("pin"));

        // validate our handset and pin
        if (ham.deviceHasAccess(macAddress) && gm.pinIsValid(pin)) {

            // If there's no exhibit in the request then the handset is signing in
            // So we sign the handset  and return the pin and the list of exhibits
            String exhibit = (String) request.getParameter("exhibit");
            if (exhibit == null) {
                String exhibitsForPin = hrm.signHandsetIn(gm, tm, pin, request.getRemoteAddr());
                
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                try{
                    out.println(pin);
                    out.println(macAddress);
                    out.println(exhibitsForPin);
                }
                finally{
                    out.close();
                }
                // fire off the response
                return;
            }
            // otherwise we can assume that they want some audio
            hrm.serveExhibitAudioFile(pin, Integer.parseInt(exhibit));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

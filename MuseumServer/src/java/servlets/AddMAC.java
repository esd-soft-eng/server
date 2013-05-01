package servlets;

import businessDomainObjects.HandsetAccessManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logging.Logger;
import logging.Logger;
import utility.Redirector;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Servlet file which corresponds to the Add New Handset JSP
 */
@WebServlet(name = "AddMAC", urlPatterns = {"/addNewDevice.do"})
public class AddMAC extends HttpServlet {

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
        //Get the mac address parameter that the jsp has hopefully passed across
        String MAC1 = (String) request.getParameter("MAC1");
        String MAC2 = (String) request.getParameter("MAC2");
        String MAC3 = (String) request.getParameter("MAC3");
        String MAC4 = (String) request.getParameter("MAC4");
        String MAC5 = (String) request.getParameter("MAC5");
        String MAC6 = (String) request.getParameter("MAC6");


        //If the MAC wasn't passed across then redirect back to the add MAC address page
        if (!validMAC(MAC1, MAC2, MAC3, MAC4, MAC5, MAC6)) {
            request.setAttribute("message", "<h2 style='color:red'>Please enter a valid value for the MAC address</h2>");
            Redirector.redirect(request, response, "/admin/addMacAddress.jsp");
            return;
        }
        String MAC = MAC1+":"+MAC2+":"+MAC3+":"+MAC4+":"+MAC5+":"+MAC6;
        //get the handsetAccessManager from the servlet context
        HandsetAccessManager ham = (HandsetAccessManager) getServletContext().getAttribute("handsetAccessManager");

        //attempt to add the device and redirect with a success message,
        //if it fails then redirect back to the 'add' page.
        if (ham.addDevice(MAC)) {
            Logger.Log(Logger.LogType.HANDSETADD, new String[] {MAC, (String)request.getSession().getAttribute("username")});
            request.setAttribute("message", "<h2>Successfully added MAC address <i>\"" + MAC + "\"</i> to the database.</h2>"
                    + "<p> Click <a href=\"/MuseumServer/admin/viewRegisteredHandsets.jsp\">here</a> to view registered devices.");
            Redirector.redirect(request, response, "/admin/addMacAddress.jsp");
            return;
        } else {
            request.setAttribute("message", "<h2 style='color:red'>Failed to add MAC address <i>\"" + MAC + "\"</i> to the database.</h2><p>Reason: <i>\"" + MAC + "\"</i> likely already exists.");
            Redirector.redirect(request, response, "/admin/addMacAddress.jsp");
            return;
        }
    }

    //Simple function which checks if a MAC address is actually valid
    private boolean validMAC(String MAC1, String MAC2, String MAC3, String MAC4, String MAC5, String MAC6) {
        String[] macArray = {MAC1, MAC2, MAC3, MAC4, MAC5, MAC6};

        for (String mac : macArray) {
            if (mac == null || mac.isEmpty()) {
                return false;
            }
            //regex to see if the mac address is a valid hex value
            if (!mac.matches("^[\\da-fA-F]+$")) {
                return false;
            }
        }

        return true;
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

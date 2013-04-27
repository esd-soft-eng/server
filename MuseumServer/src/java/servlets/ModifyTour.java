package servlets;

import businessDomainObjects.Exhibit;
import businessDomainObjects.ExhibitManager;
import businessDomainObjects.Tour;
import businessDomainObjects.TourManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logging.Logger;
import utility.Redirector;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Servlet file which corresponds to the Modify Tour JSP
 */
@WebServlet(name = "ModifyTour", urlPatterns = {"/modifyTour.do"})
public class ModifyTour extends HttpServlet {

    TourManager tm;

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
        String tourID = request.getParameter("tourID");
        String stage = request.getParameter("stage");

        if (tourID == null || tourID == "" || stage == null || stage == "") {
            request.setAttribute("message", "<h2 style='color:red;'> You must select a tour. </h2>");
            Redirector.redirect(request, response, "/admin/modifyTour.jsp");
            return;
        }

        int convertedID = Integer.parseInt(tourID);
        tm = (TourManager) getServletContext().getAttribute("tourManager");
        Tour tourToModify = tm.getTourByID(convertedID);

        if (tourToModify == null) {
            request.setAttribute("message", "<h2 style='color:red;'> Invalid tour ID. </h2>");
            Redirector.redirect(request, response, "/admin/modifyTour.jsp");
            return;
        }

        if (stage.equals("1")) {
            modifyTourStage1(request, response, tourToModify);
        } else if (stage.equals("2")) {
            modifyTourStage2(request, response, tourToModify);
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

    private void modifyTourStage1(HttpServletRequest request, HttpServletResponse response, Tour tourToModify) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String tourName = tourToModify.getName();
        String tourDescription = tourToModify.getDescription();
        int tourID = tourToModify.getTourID();
        ExhibitManager em = (ExhibitManager) getServletContext().getAttribute("exhibitManager");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Modify an existing.</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='/MuseumServer/modifyTour.do'>");
            out.println("<h2>Select a tour to modify.</h2>");
            out.println("<input type='hidden' name='stage' value='2'/>");
            out.println("<input type='hidden' name='tourID' value='" + tourToModify.getTourID() + "'/>");
            out.println("Name of tour: <input type='text' name='tourName' size='50' maxlength='50' value='" + tourName + "'/><br/>");
            out.println("Description of tour: <input type='text' name='tourDescription' size='150' maxlength='200' value='" + tourDescription + "'/><br/>");
            for (Exhibit e : em.getListOfExhibits()) {

                String description = e.getDescription().length() > 100 ? e.getDescription().substring(0, 100) + "....." : e.getDescription();
                String name = e.getName();
                int ID = e.getExhibitID();
                String s = String.valueOf(tourID);
                if (tourToModify.getExhibitIDs().contains(String.valueOf(ID))) {
                    out.println("<input type=\"checkbox\" name=\"exhibitID\" value=\"" + ID + "\" checked>" + name + "(<i>" + description + "</i>)<br>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"exhibitID\" value=\"" + ID + "\">" + name + "(<i>" + description + "</i>)<br>");
                }
            }
            out.println("<input type='submit' value='modify'/>");
            out.println("</form>");
            String messageFromServlet = (String) request.getAttribute("message");
            if (messageFromServlet != null) {
                out.println(messageFromServlet);
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }

    }

    private void modifyTourStage2(HttpServletRequest request, HttpServletResponse response, Tour tourToModify) throws ServletException, IOException {

        String tourID = request.getParameter("tourID");
        int convertedID = Integer.parseInt(tourID);
        String tourName = request.getParameter("tourName");
        String tourDescription = request.getParameter("tourDescription");
        String[] exhibitIDs = request.getParameterValues("exhibitID");

        if (tourName == null || tourName == "") {
            request.setAttribute("message", "<h2 style='color:red;'> You must enter a tour name. </h2>");
            Redirector.redirect(request, response, "modifyTour.do?tourID=" + tourID + "&stage=1");
            return;
        }
        if (tourDescription == null || tourDescription == "") {
            request.setAttribute("message", "<h2 style='color:red;'> You must enter a tour description. </h2>");
            Redirector.redirect(request, response, "modifyTour.do?tourID=" + tourID + "&stage=1");
            return;
        }
        if (exhibitIDs == null || exhibitIDs.length == 0) {
            request.setAttribute("message", "<h2 style='color:red;'> You must select at least one exhibit. </h2>");
            Redirector.redirect(request, response, "modifyTour.do?tourID=" + tourID + "&stage=1");
            return;
        }
        ArrayList<String> exhibits = new ArrayList<String>();
        for (String s : exhibitIDs) {
            exhibits.add(s);
        }

        if (!tm.modifyTour(convertedID, tourName, tourDescription, exhibits)) {
            request.setAttribute("message", "<h2 style='color:red;'> Failed to modify exhibit. </h2>");
            Redirector.redirect(request, response, "modifyTour.do?tourID=" + tourID + "&stage=1");
            return;
        } else {
            Logger.Log(Logger.LogType.TOURMODIFY, new String[]{tourID, tourName, (String) request.getSession().getAttribute("username")});

            request.setAttribute("message", "<h2> Successfully modified tour. </h2>");
            Redirector.redirect(request, response, "/admin/modifyTour.jsp");
            return;
        }
    }
}

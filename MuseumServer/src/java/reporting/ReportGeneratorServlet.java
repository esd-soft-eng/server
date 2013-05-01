package reporting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistance.DatabaseQueryExecutor;
import utility.Redirector;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: The servlet file which ensures that the report request is valid and
 * obtains the report HTML ready for display
 */
@WebServlet(name = "ReportGeneratorServlet", urlPatterns = {"/generateReport.do"})
public class ReportGeneratorServlet extends HttpServlet {

    public enum ReportType {

        REGISTER, LOGIN, AUDIO, WIFI, EXHIBIT, HANDSET, ROUTER, TOUR, USER;
    };
    String[] validTypes = {"audioLog", "exhibitLog", "handsetLog", "loginLog", "registerLog", "routerLog", "tourLog", "userLog", "wifiAudioLog"};
    String[] validPeriods = {"week", "fortnight", "month", "twoMonths", "fourMonths", "sixMonths", "year", "forever"};

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reportType = request.getParameter("reportType");
        String reportPeriod = request.getParameter("reportPeriod");
        ReportFactory fact = new ReportFactory();
        Report report = null;
        DatabaseQueryExecutor db = (DatabaseQueryExecutor) getServletContext().getAttribute("databaseExecutor");

        if (!isValidType(reportType) || !isValidPeriod(reportPeriod)) {
            request.setAttribute("message", "<h2 style='color:red;'>Either the report type or the report period was invalid.</h2>");
            Redirector.redirect(request, response, "/manager/reportGenerator.jsp");
            return;
        }
        if ("audioLog".equals(reportType)) {
            report = fact.getFactory(ReportType.AUDIO);
        } else if ("exhibitLog".equals(reportType)) {
            report = fact.getFactory(ReportType.EXHIBIT);
        } else if ("handsetLog".equals(reportType)) {
            report = fact.getFactory(ReportType.HANDSET);
        } else if ("loginLog".equals(reportType)) {
            report = fact.getFactory(ReportType.LOGIN);
        } else if ("registerLog".equals(reportType)) {
            report = fact.getFactory(ReportType.REGISTER);
        } else if ("routerLog".equals(reportType)) {
            report = fact.getFactory(ReportType.ROUTER);
        } else if ("tourLog".equals(reportType)) {
            report = fact.getFactory(ReportType.TOUR);
        } else if ("userLog".equals(reportType)) {
            report = fact.getFactory(ReportType.USER);
        } else if ("wifiAudioLog".equals(reportType)) {
            report = fact.getFactory(ReportType.WIFI);
        }

        //convert report periods to week numbers        
        if (reportPeriod.equals("week")) {
            reportPeriod = "1";
        } else if (reportPeriod.equals("fortnight")) {
            reportPeriod = "2";
        } else if (reportPeriod.equals("month")) {
            reportPeriod = "4";
        } else if (reportPeriod.equals("twoMonths")) {
            reportPeriod = "8";
        } else if (reportPeriod.equals("fourMonths")) {
            reportPeriod = "16";
        } else if (reportPeriod.equals("sixMonths")) {
            reportPeriod = "26";
        } else if (reportPeriod.equals("year")) {
            reportPeriod = "52";
        } else if (reportPeriod.equals("forever")) {
            reportPeriod = "999";
        }




        String reportHTML = report.generateReport(reportPeriod, db);
        if (report == null || report.equals("")) {
            request.setAttribute("message", "<h2 style='color:red;'>Either the report type or the report period was invalid.</h2>");
            Redirector.redirect(request, response, "/manager/reportGenerator.jsp");
            return;
        } else {
            request.setAttribute("report", reportHTML);
            Redirector.redirect(request, response, "/manager/reportGenerator.jsp");
            return;
        }
    }

    private boolean isValidType(String reportType) {
        if (reportType == null) {
            return false;
        }
        for (String s : validTypes) {
            if (reportType.equals(s)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPeriod(String reportPeriod) {
        if (reportPeriod == null) {
            return false;
        }
        for (String s : validPeriods) {
            if (reportPeriod.equals(s)) {
                return true;
            }
        }
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."){}
    /**
     * Handles the HTTP <code){}GET</code){} method.
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
     * Handles the HTTP <code){}POST</code){} method.
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
    }// </editor-fold){}
}

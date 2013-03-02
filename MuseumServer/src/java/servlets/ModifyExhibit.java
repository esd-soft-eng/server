package servlets;

import businessDomainObjects.Audio;
import businessDomainObjects.AudioManager;
import businessDomainObjects.Exhibit;
import businessDomainObjects.ExhibitManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Redirector;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
@WebServlet(name = "ModifyExhibit", urlPatterns = {"/modifyExhibit.do"})
public class ModifyExhibit extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tempID = request.getParameter("exhibitID");
        String stage = request.getParameter("stage");
        ExhibitManager manager = (ExhibitManager) getServletContext().getAttribute("exhibitManager");
        AudioManager audioManager = (AudioManager) getServletContext().getAttribute("audioManager");

        if (stage == null || stage == "") {
            request.setAttribute("message", "<h2 style='color:red'>Stage parameter missing.</h2>");
            Redirector.redirect(request, response, "/admin/modifyExhibit.jsp");
            return;
        }

        if (tempID == null || tempID == "") {
            request.setAttribute("message", "<h2 style='color:red'>Please select an exhibit to modify.</h2>");
            Redirector.redirect(request, response, "/admin/modifyExhibit.jsp");
            return;
        }

        int exhibitID = -1;
        Exhibit exhibitToModify;
        try {
            exhibitID = Integer.parseInt(tempID);
            exhibitToModify = manager.getExhibitByID(exhibitID);
            if (exhibitToModify == null) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "<h2 style='color:red'>Exhibit ID was not a valid integer.</h2>");
            Redirector.redirect(request, response, "/admin/modifyExhibit.jsp");
            return;
        }

        if (audioManager.getListOfAudio().isEmpty()) {
            request.setAttribute("message", "<h2 style='color:red'>No audio is currently held in the database, please add some before modification</h2>");
            Redirector.redirect(request, response, "/admin/modifyExhibit.jsp");
            return;
        }

        if (stage.equals("1")) {
            response.setContentType("text/html;charset=UTF-8");
            String exhibitName = exhibitToModify.getName();
            String exhibitDescription = exhibitToModify.getDescription();
            int audioLevel1ID = exhibitToModify.getAudioLevel1ID();
            int audioLevel2ID = exhibitToModify.getAudioLevel2ID();
            int audioLevel3ID = exhibitToModify.getAudioLevel3ID();
            int audioLevel4ID = exhibitToModify.getAudioLevel4ID();

            PrintWriter out = response.getWriter();
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Modify an exhibit stored in the database</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Modify an exhibit stored in the database</h1>");
                if (request.getAttribute("message") != null) {
                    out.println(request.getAttribute("message"));
                }
                out.println("<form action='/MuseumServer/modifyExhibit.do'>");
                out.println("<input type='hidden' name='exhibitID' value='" + exhibitID + "'/>");
                out.println("<input type='hidden' name='stage' value='2'/>");
                out.println("Name of exhibit: <input type='text' name='name' size='50' maxlength='50' value='" + exhibitName + "'/><br/>");
                out.println("Description of exhibit: <input type='text' name='description' size='100' maxlength='100' value='" + exhibitDescription + "'/><br/>");
                out.println("<h3>Audio file for level 1:</h3>");

                for (Audio audio : audioManager.getListOfAudio()) {
                    if (audio.getAudioID() == audioLevel1ID) {
                        out.println("<input type='radio' name='audioIDLevel1' value='" + audio.getAudioID() + "' checked><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                    } else {
                        out.println("<input type='radio' name='audioIDLevel1' value='" + audio.getAudioID() + "'><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                    }
                }

                out.println("<h3>Audio file for level 2:</h3>");

                for (Audio audio : audioManager.getListOfAudio()) {
                    if (audio.getAudioID() == audioLevel2ID) {
                        out.println("<input type='radio' name='audioIDLevel2' value='" + audio.getAudioID() + "' checked><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                    } else {
                        out.println("<input type='radio' name='audioIDLevel2' value='" + audio.getAudioID() + "'><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                    }
                }

                out.println("<h3>Audio file for level 3:</h3>");

                for (Audio audio : audioManager.getListOfAudio()) {
                    if (audio.getAudioID() == audioLevel3ID) {
                        out.println("<input type='radio' name='audioIDLevel3' value='" + audio.getAudioID() + "' checked><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                    } else {
                        out.println("<input type='radio' name='audioIDLevel3' value='" + audio.getAudioID() + "'><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                    }
                }

                out.println("<h3>Audio file for level 4:</h3>");

                for (Audio audio : audioManager.getListOfAudio()) {
                    if (audio.getAudioID() == audioLevel4ID) {
                        out.println("<input type='radio' name='audioIDLevel4' value='" + audio.getAudioID() + "' checked><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                    } else {
                        out.println("<input type='radio' name='audioIDLevel4' value='" + audio.getAudioID() + "'><b>" + audio.getAudioName() + "</b> (held at <i>\"" + audio.getAudioLocation() + "\"</i>) <br/>");
                    }
                }

                out.println("<input type='submit' value='Modify'/>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");

            } finally {
                out.close();
            }
        } else if (stage.equals("2")) {
            String exhibitName = request.getParameter("name");
            String exhibitDescription = request.getParameter("description");
            int audioLevel1ID = Integer.parseInt(request.getParameter("audioIDLevel1"));
            int audioLevel2ID = Integer.parseInt(request.getParameter("audioIDLevel2"));
            int audioLevel3ID = Integer.parseInt(request.getParameter("audioIDLevel3"));
            int audioLevel4ID = Integer.parseInt(request.getParameter("audioIDLevel4"));

            if (exhibitName == null || exhibitName.equals("")) {
                request.setAttribute("message", "<h2 style='color:red'>Name cannot be empty</h2>");
                Redirector.redirect(request, response, "/modifyExhibit.do?exhibitID=" + exhibitID + "&stage=1");
                return;
            } else if (exhibitDescription == null || exhibitDescription.equals("")) {
                request.setAttribute("message", "<h2 style='color:red'>Description cannot be null</h2>");
                Redirector.redirect(request, response, "/modifyExhibit.do?exhibitID=" + exhibitID + "&stage=1");
                return;
            } else if (!manager.modifyExhibit(exhibitID, exhibitName, exhibitDescription, audioLevel1ID, audioLevel2ID, audioLevel3ID, audioLevel4ID)) {
                request.setAttribute("message", "<h2 style='color:red'>Modification failed</h2>");
                Redirector.redirect(request, response, "/admin/modifyExhibit.jsp");
                return;
            }

            request.setAttribute("message", "<h2>Successfully modified exhibit</h2>");
            Redirector.redirect(request, response, "/admin/modifyExhibit.jsp");

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

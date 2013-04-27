package servlets;

import businessDomainObjects.Audio;
import businessDomainObjects.AudioManager;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Redirector;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Servlet file which corresponds to the Remove Audio JSP
 */
@WebServlet(name = "RemoveAudio", urlPatterns = {"/removeAudio.do"})
public class RemoveAudio extends HttpServlet {

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
        String ID = request.getParameter("audioID");

        if (ID == null || ID.equals("")) {
            request.setAttribute("message", "<h2 style='color:red;'>No audio was selected</h2>");
            Redirector.redirect(request, response, "/admin/removeAudio.jsp");
            return;
        }

        AudioManager manager = (AudioManager) getServletContext().getAttribute("audioManager");
        Audio audioToDelete = null;
        //Loop over all audio until we find the correct audio to remove
        for (Audio a : manager.getListOfAudio()) {
            if (a.getAudioID() == Integer.parseInt(ID)) {
                audioToDelete = a;
                break;
            }
        }

        //if no audio was found then error out
        if (audioToDelete == null) {
            request.setAttribute("message", "<h2 style='color:red;'>Invalid audio ID was selected</h2>");
            Redirector.redirect(request, response, "/admin/removeAudio.jsp");
            return;
        }

        File audioFile = new File(audioToDelete.getAudioLocation());
        audioFile.delete();

        //if the deletion fails then error out
        if (!manager.removeAudio(Integer.parseInt(ID))) {
            request.setAttribute("message", "<h2 style='color:red;'>Failed to delete audio file.</h2>");
            Redirector.redirect(request, response, "/admin/removeAudio.jsp");
            return;
        }

        request.setAttribute("message", "<h2>Successfully deleted audio file.</h2>");
        Redirector.redirect(request, response, "/admin/removeAudio.jsp");
        return;
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

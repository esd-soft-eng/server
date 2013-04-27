package servlets;

import businessDomainObjects.AudioManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import utility.FileUtil;
import utility.Redirector;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Servlet file which corresponds to the Add Audio JSP
 */
@WebServlet(name = "AddAudio", urlPatterns = {"/addAudio.do"})
public class AddAudio extends HttpServlet {

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

        AudioManager manager = (AudioManager) getServletContext().getAttribute("audioManager");
        if (manager == null) {
            request.setAttribute("message", "<h2 style='color:red;'>Failed to upload file.</h2>");
            Redirector.redirect(request, response, "/admin/addAudio.jsp");
            return;
        }

        String audioLocation = "";
        InputStream content = null;
        String audioName = "";
        String filename = "";
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            
            for (FileItem item : items) {
                //because form is multipart we deal with the file object itself here
                if (!item.isFormField()) {
                    filename = item.getName();

                    audioLocation = FileUtil.getFileSystemPath(getServletContext(), "audio") + "/" + filename;
                    content = item.getInputStream();
                } else { //And grab the other parameter in this block

                    if (item.getFieldName().contains("audioName")) {
                        audioName = item.getString();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //various checks to ensure that the uploaded file is correct/the name is valid
        if (!audioLocation.toUpperCase().endsWith(".MP3")) {
            request.setAttribute("message", "<h2 style='color:red;'>Only MP3s may be uploaded through this form.</h2>");
            Redirector.redirect(request, response, "/admin/addAudio.jsp");
            return;
        } else if (audioName == null || audioName == "") {
            request.setAttribute("message", "<h2 style='color:red;'>No name specified.</h2>");
            Redirector.redirect(request, response, "/admin/addAudio.jsp");
            return;
        } else if (content == null) {
            request.setAttribute("message", "<h2 style='color:red;'>Error uploading file.</h2>");
            Redirector.redirect(request, response, "/admin/addAudio.jsp");
            return;
        } else if (!manager.addAudio(audioName, filename)) {
            request.setAttribute("message", "<h2 style='color:red;'>Error uploading file.</h2>");
            Redirector.redirect(request, response, "/admin/addAudio.jsp");
            return;
        }
        
        uploadAudio(content, audioLocation);


        request.setAttribute("message", "<h2>Successfully uploaded file with name <i>\"" + audioName + "\"</i></h2>");
        Redirector.redirect(request, response, "/admin/addAudio.jsp");
        return;
    }

    //Simple function to store the file on the server
    public void uploadAudio(InputStream content, String audioLocation) throws FileNotFoundException, IOException {
        // write the inputStream to a FileOutputStream
        OutputStream out = new FileOutputStream(new File(audioLocation));

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = content.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        content.close();
        out.flush();
        out.close();
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

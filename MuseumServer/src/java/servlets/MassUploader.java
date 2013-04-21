package servlets;

import businessDomainObjects.AudioManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
@WebServlet(name = "MassUploader", urlPatterns = {"/MassUploader"})
public class MassUploader extends HttpServlet {

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
        //Audio files prefixes which correspond with different knowledge levels
        String[] GCSELevels = new String[]{"002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012"};
        String[] ALevelLevels = new String[]{"013", "014", "015", "016", "017", "018", "019", "020", "021", "022", "023"};
        String[] UniLevels = new String[]{"024", "025", "026", "027", "028", "029", "030", "031", "032", "033", "034"};
        String[] PHDLevels = new String[]{"051", "052", "061", "071", "100", "220", "260", "300", "301", "311"};

        //Full filenames of each song to add to the database
        String[] songNames = new String[]{
            "002 Giant Squid.mp3", "003 Archelon.mp3", "004 Jurassic Sea Lily.mp3",
            "005 Apatosaurus.mp3", "006 Zallinger's Age of Reptiles Mural.mp3", "007 Horned Dinosaurs.mp3",
            "008 Deinonychus.mp3", "009 Petrified Wood - Rainbows in Stone.mp3", "010 Mastodon.mp3",
            "011 Zallinger's Age of Mammals Mural.mp3", "012 Irish Elk.mp3", "013 Brontothere.mp3",
            "014 Hall of Human Origins.mp3", "015 'Lucy' and 'Turkana Boy'.mp3",
            "016 Neanderthal Reconstruction.mp3", "017 Hall of Native American Cultures.mp3",
            "018 Arctic Region.mp3", "019 Subarctic Region.mp3", "020 Southwest Region.mp3",
            "021 Plains Region.mp3", "022 Hall of Minerals, Earth and Space.mp3", "023 Polar Bear.mp3",
            "024 Hall of North American Dioramas.mp3", "025 Western (Shortgrass) Plains.mp3",
            "026 Alaskan Tundra.mp3", "027 Rainforest.mp3", "028 Connecticut Hall of Birds- Owls.mp3",
            "029 Eagles.mp3", "030 Dodo.mp3", "031 Southern New England Habitats- Shoreline Diroama.mp3",
            "032 Forest Edge.mp3", "033 Bog.mp3", "034 Egyptian Hall.mp3",
            "051 More info on Apatosaurus Skull.mp3", "052 More info on naming Apatosaurus.mp3",
            "061 More info on Zallinger's Age of Reptiles Mural.mp3",
            "071 More info on Horned Dinosaurs.mp3", "100 More info on the Mastodon.mp3",
            "220 More info on the Hall of Minerals, Earth and Space.mp3",
            "260 More info on the Alaskan Tundra.mp3", "300 More info on Archelon.mp3", "301 More info on the Dodo.mp3", "311 More info on the Shoreline Diorama.mp3"
        };
        AudioManager am = (AudioManager) getServletContext().getAttribute("audioManager");
        for (String song : songNames) {
            String tracknumber = song.substring(0, 3);
            String level = "";
            
            for (String s : GCSELevels) {
                if (s.equals(tracknumber)) {
                    level = "GCSE";
                }
            }
            for (String s : ALevelLevels) {
                if (s.equals(tracknumber)) {
                    level = "A-Level";
                }
            }
            for (String s : UniLevels) {
                if (s.equals(tracknumber)) {
                    level = "Uni";
                }
            }
            for (String s : PHDLevels) {
                if (s.equals(tracknumber)) {
                    level = "PHD";
                }
            }
            
            //strip number from filename
            String correctedName = song.substring(4);
            //strip filetype from filename
            correctedName = correctedName.replace(".mp3", "");
            //ensure that name field is in the format of "filename (level)
            correctedName = correctedName + " (" + level + ")";
            //add song to database
            am.addAudio(correctedName, song);
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

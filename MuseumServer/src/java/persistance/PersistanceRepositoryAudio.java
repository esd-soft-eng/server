package persistance;

import businessDomainObjects.Audio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.InputValidator;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class PersistanceRepositoryAudio {

    private DatabaseQueryExecutor db;

    public PersistanceRepositoryAudio(DatabaseQueryExecutor db) {
        this.db = db;
    }

    public ArrayList<Audio> getAllAudio() {
        String sql = "SELECT * FROM audio;";

        ResultSet rs = db.executeStatement(sql);
        return mapResultSetToArrayList(rs);
    }

    private ArrayList<Audio> mapResultSetToArrayList(ResultSet rs) {
        ArrayList<Audio> listOfAudio = new ArrayList<Audio>();

        try {
            while (rs.next()) {
                String audioName = rs.getString("AudioName");
                String audioLocation = rs.getString("AudioLocation");
                int audioID = rs.getInt("AudioID");
                Audio temp = new Audio(audioID, audioName, audioLocation);
                listOfAudio.add(temp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersistanceRepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfAudio;
    }

    public synchronized boolean addAudio(String name, String location) throws SQLException {
        name = InputValidator.clean(name);

        String insertionSQL = "INSERT INTO audio (AudioName, AudioLocation) VALUES ('" + name + "','" + location + "');";
        return db.executeUpdate(insertionSQL);
    }

    public synchronized boolean removeAudio(int ID) throws SQLException {
        String deletionSQL = "DELETE FROM audio WHERE AudioID=" + ID + ";";
        return db.executeUpdate(deletionSQL);
    }
}
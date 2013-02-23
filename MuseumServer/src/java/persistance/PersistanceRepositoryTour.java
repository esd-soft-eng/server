package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utility.InputValidator;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class PersistanceRepositoryTour {

    private DatabaseQueryExecutor db;

    public PersistanceRepositoryTour(DatabaseQueryExecutor db) {
        this.db = db;
    }

    public synchronized boolean addTour(String name, String description, ArrayList<String> exhibitIDs) throws SQLException {
        name = InputValidator.clean(name);
        description = InputValidator.clean(description);
        String insertSQL = "INSERT INTO `tours` (TourName, TourDescription) VALUES ('" + name + "','" + description + "');";
        boolean ret = db.executeUpdate(insertSQL);
        if (ret == false) {
            return false;
        }

        String tourIDSQL = "SELECT MAX(TourID) FROM `tours`;";
        ResultSet rs = db.executeStatement(tourIDSQL);
        int latestTourID = Integer.parseInt(rs.getString("TourID"));
        for (String exhibitID : exhibitIDs) {
            String exhibitInsertSQL = "INSERT INTO `toursExhibitsLink` (TourID, ExhibitID) VALUES ('" + latestTourID + "','" + exhibitID + "');";
            ret = db.executeUpdate(exhibitInsertSQL);
            if (ret == false) {
                return false;
            }
        }

        return true;
    }

    public synchronized boolean removeTour(int tourID) {
        throw new NotImplementedException();
    }

    public synchronized boolean modifyTour(String name, String description, ArrayList<String> exhibitIDs) {
        throw new NotImplementedException();
    }
}

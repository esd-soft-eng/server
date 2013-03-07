package persistance;

import businessDomainObjects.Tour;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        String tourIDSQL = "SELECT MAX(TourID) as TourID FROM `tours`;";
        ResultSet rs = db.executeStatement(tourIDSQL);
        rs.next();
        String latestTourID = rs.getString("TourID");
        for (String exhibitID : exhibitIDs) {
            String exhibitInsertSQL = "INSERT INTO `toursExhibitsLink` (TourID, ExhibitID) VALUES ('" + latestTourID + "','" + exhibitID + "');";
            ret = db.executeUpdate(exhibitInsertSQL);
            if (ret == false) {
                return false;
            }
        }

        return true;
    }

    public synchronized boolean removeTour(int tourID) throws SQLException {
        String tourIDString = InputValidator.clean(String.valueOf(tourID));
        String SQL = "DELETE FROM tours WHERE TourID = '" + tourIDString + "';";
        boolean ret = db.executeUpdate(SQL);
        if (!ret) {
            return false;
        }
        SQL = "DELETE FROM toursExhibitsLink WHERE TourID = '" + tourIDString + "';";
        ret = db.executeUpdate(SQL);
        if (!ret) {
            return false;
        }

        return true;
    }

    public synchronized boolean modifyTour(String name, String description, ArrayList<String> exhibitIDs) {
        throw new NotImplementedException();
    }

    public ArrayList<Tour> getAllTours() {
        String sql = "SELECT * FROM "
                + "`tours` `t` , `exhibits` `e` , `toursExhibitsLink` `tel` "
                + "WHERE `t`.`TourID` = `tel`.`TourID` "
                + "AND `e`.`ExhibitID` = `tel`.`ExhibitID` ORDER BY `tel`.`TourID`;";

        ResultSet rs = db.executeStatement(sql);
        return mapResultSetToArrayList(rs);
    }

    private ArrayList<Tour> mapResultSetToArrayList(ResultSet rs) {
        ArrayList<Tour> listOfTours = new ArrayList<Tour>();
        String tourName = "", tourDescription = "", tourID = "", exhibitID = "";
        Tour tempTour = null;
        try {
            while (rs.next()) {
                if (rs.getString("TourID").equals(tourID)) {
                    tempTour.addExhibit(Integer.parseInt(exhibitID));
                } else {
                    tourName = rs.getString("TourName");
                    tourDescription = rs.getString("TourDescription");
                    tourID = rs.getString("TourID");
                    exhibitID = rs.getString("ExhibitID");
                    tempTour = new Tour(Integer.parseInt(tourID), tourName, tourDescription);
                    tempTour.addExhibit(Integer.parseInt(exhibitID));
                    listOfTours.add(tempTour);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersistanceRepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfTours;
    }
}

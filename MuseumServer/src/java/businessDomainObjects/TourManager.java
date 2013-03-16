package businessDomainObjects;

import java.sql.SQLException;
import java.util.ArrayList;
import persistance.PersistanceRepositoryTour;

/**
 *
 * @author Oliver Brooks
 */

public class TourManager {

    private ArrayList<Tour> listOfTours;
    private PersistanceRepositoryTour persistance;

    public TourManager(PersistanceRepositoryTour pr) {
        persistance = pr;
        this.listOfTours = persistance.getAllTours();
    }

    public synchronized boolean addTour(String name, String description, ArrayList<String> exhibitIDs, String questionSetID) {
        try {
            boolean ret = persistance.addTour(name, description, exhibitIDs, questionSetID);
            if (ret == false) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        
        this.listOfTours = persistance.getAllTours();
        return true;
    }

    public synchronized boolean removeTour(int tourID) {
        try {
            boolean ret = persistance.removeTour(tourID);
            if (ret == false) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        this.listOfTours = persistance.getAllTours();
        return true;
    }

    public synchronized boolean modifyTour(int tourID, String name, String description, ArrayList<String> exhibitIDs) {
        try {
            boolean ret = persistance.modifyTour(tourID, name, description, exhibitIDs);
            if (ret == false) {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }

        this.listOfTours = persistance.getAllTours();
        return true;
    }

    public ArrayList<Tour> getListOfTours() {
        return listOfTours;
    }

    public Tour getTourByID(int ID) {
        for (Tour t : listOfTours) {
            if (t.getTourID() == ID) {
                return t;
            }
        }
        return null;
    }

}

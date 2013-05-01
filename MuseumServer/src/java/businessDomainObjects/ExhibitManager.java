package businessDomainObjects;

import java.sql.SQLException;
import java.util.ArrayList;
import persistance.PersistanceRepositoryExhibit;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Manager class which contains information about all Exhibit models
 *       and holds a list of all active exhibits in the database
 */
public class ExhibitManager {

    private ArrayList<Exhibit> listOfExhibits;
    private PersistanceRepositoryExhibit persistance;

    public ExhibitManager(PersistanceRepositoryExhibit pr) {
        persistance = pr;
        this.listOfExhibits = persistance.getAllExhibits();
    }

    public synchronized boolean addExhibit(String name, String description, int audioLevel1ID, int audioLevel2ID, int audioLevel3ID, int audioLevel4ID) {

        try {
            boolean ret = persistance.addExhibit(name, description, audioLevel1ID, audioLevel2ID, audioLevel3ID, audioLevel4ID);
            if (ret == false) {
                return false;
            } else {
                this.listOfExhibits = persistance.getAllExhibits();
                return true;
            }
        } catch (SQLException se) {
            return false;
        }
    }

    public ArrayList<Exhibit> getListOfExhibits() {
        return listOfExhibits;
    }

    public Exhibit getExhibitByID(int ID) {
        for (Exhibit e : listOfExhibits) {
            if (e.getExhibitID() == ID) {
                return e;
            }
        }
        return null;
    }

    public synchronized boolean removeExhibit(int ID) {
        try {
            boolean ret = persistance.removeExhibit(ID);
            if (ret == false) {
                return false;
            } else {
                this.listOfExhibits = persistance.getAllExhibits();
                return true;
            }
        } catch (SQLException se) {
            return false;
        }
    }

    public synchronized boolean modifyExhibit(int ID, String name, String description, int audioLevel1ID, int audioLevel2ID, int audioLevel3ID, int audioLevel4ID) {
        try {
            boolean ret = persistance.modifyExhibit(ID, name, description, audioLevel1ID, audioLevel2ID, audioLevel3ID, audioLevel4ID);
            if (ret == false) {
                return false;
            } else {
                this.listOfExhibits = persistance.getAllExhibits();
                return true;
            }
        } catch (SQLException se) {
            return false;
        }
    }
}

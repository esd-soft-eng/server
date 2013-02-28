package businessDomainObjects;

import java.sql.SQLException;
import java.util.ArrayList;
import persistance.PersistanceRepositoryExhibit;
import utility.InputValidator;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class ExhibitManager {

    private ArrayList<Exhibit> listOfExhibits;
    private PersistanceRepositoryExhibit persistance;

    public ExhibitManager(PersistanceRepositoryExhibit pr) {
        persistance = pr;
        this.listOfExhibits = persistance.getAllExhibits();
    }

    public synchronized boolean addExhibit(String name, String description, int audioLevel1ID, int audioLevel2ID, int audioLevel3ID, int audioLevel4ID) {
        name = InputValidator.clean(name);
        description = InputValidator.clean(description);

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

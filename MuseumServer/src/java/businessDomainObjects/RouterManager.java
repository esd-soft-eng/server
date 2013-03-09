package businessDomainObjects;

import java.util.ArrayList;
import persistance.PersistanceRepositoryRouter;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class RouterManager {

    private PersistanceRepositoryRouter persistance;
    private ArrayList<Router> listOfRouters;

    public RouterManager(PersistanceRepositoryRouter pr) {
        this.persistance = pr;
        listOfRouters = persistance.getAllRouters();
    }

    public synchronized boolean removeRouter(String MAC) {
        if (MAC == null || MAC == "") {
            return false;
        }

        if (getRouterByMAC(MAC) == null) {
            return false;
        }

        if (!persistance.removeRouter(MAC)) {
            return false;
        } else {
            return true;
        }
    }

    private Router getRouterByMAC(String MAC) {
        for (Router r : listOfRouters) {
            if (r.getMACAddress() == MAC) {
                return r;
            }
        }
        return null;
    }
}

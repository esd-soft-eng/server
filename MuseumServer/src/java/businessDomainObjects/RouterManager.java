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
    
    public synchronized boolean addRouter(Router r){
        String audioLocation = r.getAudioLocation();
        String description = r.getDescription();
        String macAddress = r.getMACAddress();
        
        if(macAddress == null || macAddress.equals("") || audioLocation.equals("") || audioLocation == null
                || description == null || description.equals("")){
            return false;
        }
        
        if(!persistance.addRouter(macAddress, audioLocation, description)){
            return false;
        }
        
        return true;
    }
    
    
    public synchronized boolean modifyRouter(Router r){
        String audioLocation = r.getAudioLocation();
        String description = r.getDescription();
        String macAddress = r.getMACAddress();
        
        if(macAddress == null || macAddress.equals("") || audioLocation.equals("") || audioLocation == null
                || description == null || description.equals("")){
            return false;
        }
        
        if(!persistance.modifyRouter(macAddress, audioLocation, description)){
            return false;
        }
        
        return true;
    }

    public synchronized boolean removeRouter(String MAC) {
        if (MAC == null || MAC.equals("")) {
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

    public Router getRouterByMAC(String MAC) {
        for (Router r : listOfRouters) {
            if (r.getMACAddress().equals(MAC)) {
                return r;
            }
        }
        return null;
    }
    
    public ArrayList<Router> getRouterList(){
        return listOfRouters;
    }
}

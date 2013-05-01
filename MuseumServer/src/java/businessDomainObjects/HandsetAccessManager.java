package businessDomainObjects;

import java.util.HashSet;
import persistance.PersistanceRepositoryHandset;

/**
 *
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 */
public class HandsetAccessManager {

    private PersistanceRepositoryHandset persistance;
    private HashSet<String> allowedDevices;

    public HandsetAccessManager(PersistanceRepositoryHandset persistance) {
        this.persistance = persistance;
        this.initialise();
    }

    public synchronized boolean addDevice(String macAddress) {
        if (this.allowedDevices.add(macAddress)) {
            persistance.addDevice(macAddress);
            return true;
        }
        return false;
    }

    public synchronized boolean removeDevice(String macAddress) {
        if (this.allowedDevices.remove(macAddress)) {
            persistance.removeDevice(macAddress);
            return true;
        }
        return false;
    }

    public boolean deviceHasAccess(String macAddress) {
        boolean allowed = allowedDevices.contains(macAddress);
        return allowed;
    }

    public String[] getDeviceList() {
        return this.allowedDevices.toArray(new String[0]);
    }

    private void initialise() {
        this.allowedDevices = this.persistance.getAllowedDevices();
    }
}

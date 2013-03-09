package businessDomainObjects;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class Router {

    String MAC;
    String audioLocation;
    String description;

    public Router() {
        MAC = "";
        audioLocation = "";
        description = "";
    }

    public String getAudioLocation() {
        return audioLocation;
    }

    public void setAudioLocation(String audioLocation) {
        this.audioLocation = audioLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMACAddress() {
        return MAC;
    }

    public void setMACAddress(String MAC) {
        this.MAC = MAC;
    }
}

package businessDomainObjects;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Audio model class, containing all information about an audio file.
 */
public class Audio {
    private int audioID;
    private String audioName;
    private String audioLocation;

    public Audio(int audioID, String audioName, String audioLocation) {
        this.audioID = audioID;
        this.audioName = audioName;
        this.audioLocation = audioLocation;
    }
    
    public int getAudioID() {
        return audioID;
    }

    public void setAudioID(int audioID) {
        this.audioID = audioID;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getAudioLocation() {
        return audioLocation;
    }

    public void setAudioLocation(String audioLocation) {
        this.audioLocation = audioLocation;
    }    
}

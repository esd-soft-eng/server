/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businessDomainObjects;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class Exhibit {

    private int exhibitID;
    private String name;
    private String description;
    private int audioLevel1ID, audioLevel2ID, audioLevel3ID, audioLevel4ID;

    public Exhibit(int exhibitID, String name, String description, int audioLevel1ID, int audioLevel2ID, int audioLevel3ID, int audioLevel4ID) {
        this.exhibitID = exhibitID;
        this.name = name;
        this.description = description;
        this.audioLevel1ID = audioLevel1ID;
        this.audioLevel2ID = audioLevel2ID;
        this.audioLevel3ID = audioLevel3ID;
        this.audioLevel4ID = audioLevel4ID;
    }

    public int getExhibitID() {
        return exhibitID;
    }

    public void setExhibitID(int exhibitID) {
        this.exhibitID = exhibitID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAudioLevel1ID() {
        return audioLevel1ID;
    }

    public void setAudioLevel1ID(int audioLevel1ID) {
        this.audioLevel1ID = audioLevel1ID;
    }

    public int getAudioLevel2ID() {
        return audioLevel2ID;
    }

    public void setAudioLevel2ID(int audioLevel2ID) {
        this.audioLevel2ID = audioLevel2ID;
    }

    public int getAudioLevel3ID() {
        return audioLevel3ID;
    }

    public void setAudioLevel3ID(int audioLevel3ID) {
        this.audioLevel3ID = audioLevel3ID;
    }

    public int getAudioLevel4ID() {
        return audioLevel4ID;
    }

    public void setAudioLevel4ID(int audioLevel4ID) {
        this.audioLevel4ID = audioLevel4ID;
    }
}

package businessDomainObjects;

import java.util.HashSet;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class Tour {

    private int tourID;
    private String name;
    private String description;
    private HashSet<String> exhibitIDs;
    private int questionSetID;

    public Tour(int ID, String name, String description, int questionSetID) {
        this.tourID = ID;
        this.name = name;
        this.description = description;
        exhibitIDs = new HashSet<String>();
        this.questionSetID = questionSetID;
    }

    public void addExhibit(int exhibitID) {
        exhibitIDs.add(String.valueOf(exhibitID));
    }

    public void removeExhibit(int exhibitID) {
        String convertedID = String.valueOf(exhibitID);
        exhibitIDs.remove(convertedID);
    }

    public int getTourID() {
        return tourID;
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

    public HashSet<String> getExhibitIDs() {
        return exhibitIDs;
    }
    
    public int getQuestionSetID(){
        return questionSetID;
    }   
}

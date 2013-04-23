/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handsetInteraction;

import QuestionsAndAnswers.Levels;
import businessDomainObjects.AudioManager;
import businessDomainObjects.Exhibit;
import businessDomainObjects.ExhibitManager;
import businessDomainObjects.HandsetAccessManager;
import businessDomainObjects.TourManager;
import java.util.HashMap;
import utility.AudioUrlDispatcher;
import visitorsAndGroups.Group;
import visitorsAndGroups.GroupManager;
import visitorsAndGroups.Visitor;

/**
 *
 * @author neil
 */
public class HandsetRequestManager {

    private HandsetAccessManager ham;
    private GroupManager gm;
    private TourManager tm;
    private ExhibitManager em;
    private AudioManager am;
    private AudioPortManager apm;
    private String serverAddress;

    public HandsetRequestManager(HandsetAccessManager ham, GroupManager gm,
            TourManager tm, ExhibitManager em, AudioManager am, AudioPortManager apm, String serverAddress) {
        this.ham = ham;
        this.gm = gm;
        this.tm = tm;
        this.em = em;
        this.am = am;
        this.apm = apm;
        this.serverAddress = serverAddress;
    }

    public String signHandsetIn(GroupManager gm, TourManager tm, int pin, String hostname) {

        // Set the retun url for the pins audio requests
        Group group = gm.getGroupContainingPin(pin);
        group.setPinUrl(pin, hostname);

        // Get the list of exhibits which will be returned to the handset
        return tm.getTourExhibitsAsString(group.getTourId());
    }

    public boolean serveExhibitAudioFile(int pin, int exhibit) {

        Group group = this.gm.getGroupContainingPin(pin);

        if (group.pinIsGroupLeader(pin)) {
            Visitor[] visitors = group.getVisitorsAsArray();
            Exhibit ex = this.em.getExhibitByID(exhibit);
            this.serveTracks(visitors, ex);
        }
        return true;
    }

    private void serveTracks(Visitor[] visitors, Exhibit exhibit) {

        HashMap<Levels, String> levelsServed = new HashMap();

        for (Visitor visitor : visitors) {
            String audioUrl = this.startAudioAndGetUrl(visitor, exhibit, levelsServed);
            AudioUrlDispatcher.DispatchUrlToHandset(visitor.pin, audioUrl, visitor.getHostName());
        }
    }

    private String startAudioAndGetUrl(Visitor visitor, Exhibit exhibit, HashMap levelsServed) {

        Levels level = visitor.level;

        // If we're already running the audio for another person in the group
        // then we don't need to run it again on another port do we (multicast feature)
        if (levelsServed.containsKey(level)) {
            return (String) levelsServed.get(level);
        }

        int audioFileId = this.getAudioFileForLevel(level, exhibit);
        String audioFile = am.getLocationForAudioId(audioFileId);
        String port = apm.getNewPort();

        // now we compose the url for the audio and add it to the 
        // list of already playing audio files
        String audioUrl = serverAddress + ":" + port + "/" + audioFile;
        levelsServed.put(level, audioUrl);

        return audioUrl;
    }

    private int getAudioFileForLevel(Levels level, Exhibit exhibit) {

        if (level == Levels.PHD) {
            return exhibit.getAudioLevel4ID();
        }
        if (level == Levels.UNI) {
            return exhibit.getAudioLevel3ID();
        }
        if (level == Levels.ALEVEL) {
            return exhibit.getAudioLevel2ID();
        }
        return exhibit.getAudioLevel1ID();
    }
}
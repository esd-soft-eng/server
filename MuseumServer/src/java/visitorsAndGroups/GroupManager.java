/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visitorsAndGroups;

import java.util.ArrayList;
import persistance.PersistanceRepositoryGroups;

/**
 *
 * @author neil
 */
public class GroupManager {

    private PersistanceRepositoryGroups persistance;
    private ArrayList<Group> groups;

    public GroupManager(PersistanceRepositoryGroups persistance) {
        this.persistance = persistance;
        this.init();
    }

    public synchronized int createNewGroup(int tourId) {

        if (persistance.addNewGroupToDatabase(tourId)) {
            Group newGroup = persistance.getLastCreatedGroup();
            this.groups.add(newGroup);
            return newGroup.getId();
        }

        return -1;
    }

    public synchronized boolean addNewVisitorToGroup(int groupId, Visitor visitor) {

        visitor.pin = this.generateNewPin();
        if (persistance.addNewVisitorToDatabase(visitor, groupId)) {
            return this.addVisitorToGroup(groupId, visitor);
        }
        return false;
    }

    public synchronized boolean addVisitorToGroup(int groupId, Visitor visitor) {

        Group group = this.getGroupById(groupId);
        if (group != null) {
            return group.addVisitorToGroup(visitor);
        }
        return false;
    }

    public synchronized Integer generateNewPin() {

        int newPin;

        do {
            newPin = this.getRandomFourDigitInt();
        } while (this.pinIsAlreadyTaken(newPin));

        return newPin;
    }

    public boolean setGroupLeader(int groupId, int pin) {
        
        Group group = this.getGroupById(groupId);
        if (group != null){
            return (group.setGroupLeader(pin) && persistance.updateGroupLeader(group)) ? true : false;
        }
        return false;
    }

    private synchronized Group getGroupById(int groupId) {

        for (Group g : this.groups) {
            if (g.getId() == groupId) {
                return g;
            }
        }
        return null;
    }

    private int getRandomFourDigitInt() {
        return (int) (1000 + (Math.random() * 8999));
    }

    private synchronized boolean pinIsAlreadyTaken(int newPin) {

        for (Group g : this.groups) {
            if (g.containsPin(newPin)) {
                return true;
            }
        }
        return false;
    }

    private void init() {
        this.groups = persistance.getAllActiveGroups();
    }
}

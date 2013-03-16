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

        if (persistance.addNewVisitorToDatabase(visitor, groupId)) {
            return this.addVisitorToGroup(groupId, visitor);
        }
        return false;
    }

    public synchronized boolean addVisitorToGroup(int groupId, Visitor visitor) {

        Group group = this.getGroupById(groupId);
        if (group != null) {
            return this.groups.add(group);
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

    private void init() {
        this.groups = persistance.getAllGroups();
    }
}

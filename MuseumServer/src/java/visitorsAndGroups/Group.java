/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visitorsAndGroups;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author neil
 */
public class Group {

    private int id;
    private ArrayList<Visitor> groupMembers;
    private Visitor groupLeader;
    private int tourId;
    private Date start;
    private Date expires;
    private boolean active;
    
    public Group(int id, int tourId, Date start, Date expires, boolean active, ArrayList<Visitor> groupMembers){
        
        this.id = id;
        this.tourId = tourId;
        this.start = start;
        this.expires = expires;
        this.groupMembers = groupMembers;
        this.active = active;
    }
    
    public int getId(){
        return this.id;
    }
    
    public boolean setGroupLeader(int pin){
        
        Visitor newLeader = this.findVisitorByPin(pin);
        if (newLeader == null){
            return false;
        }
        this.groupLeader = newLeader;
        return true;
    }
    
    public boolean pinIsGroupLeader(int pin){
        return (this.groupLeader.getPin() == pin) ? true : false;
    }
    
    public boolean addVisitorToGroup(Visitor visitor){
        return this.groupMembers.add(visitor);
    }
    
    public boolean removeVisitorFromGroup(int pin){
        
        Visitor remove = this.findVisitorByPin(pin);
        if (remove == null){
            return false;
        }
        return this.groupMembers.remove(remove);        
    }
    
    public Visitor[] getVisitorsAsArray(){
              
        return this.groupMembers.toArray(new Visitor[0]);
    }
            
    private Visitor findVisitorByPin(int pin){
        
        for (Visitor visitor : this.groupMembers){
            if (visitor.getPin() == pin){
                return visitor;
            }
        }
        return null;
    }
}

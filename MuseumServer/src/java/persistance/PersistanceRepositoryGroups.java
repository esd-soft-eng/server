/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import QuestionsAndAnswers.Levels;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import visitorsAndGroups.Group;
import visitorsAndGroups.Visitor;

/**
 *
 * @author neil
 */
public class PersistanceRepositoryGroups {
    
    private DatabaseQueryExecutor executor;
        
    public void PeristanceRepositoryGroups(DatabaseQueryExecutor executor){
        this.executor = executor;
    }

    public synchronized boolean addNewGroupToDatabase(int tourId) {
        
        String sql = "INSERT INTO `tourGroup` "
                   + "(tourId, startDate, expiryDate, active) "
                   + "VALUES (" + tourId + ", NOW(), NOW() + INTERVAL 8 HOUR, 1)";
        
        return executor.executeUpdate(sql);
    }
    
    public boolean updateGroup(int id, int leader, Date start, Date expires, boolean active){
        
        String sql = "UPDATE `tourGroup` `tg` "
                   + "SET `tg`.`leader`=" + leader
                   + " , `tg`.`start`=" + start
                   + " , `tg`.`expires`=" + expires
                   + " , `tg`.`active`=" + active
                   + " WHERE `tg`.`id`=" + id;
        return executor.executeUpdate(sql);
    }

    public synchronized Group getLastCreatedGroup() {
        
        String sql = "SELECT MAX(`id`) FROM `tourGroup`";
        
        ResultSet rs = executor.executeStatement(sql);
        return this.getTourGroupsFromResultSet(rs).get(0);        
    }

    public synchronized boolean addNewVisitorToDatabase(Visitor visitor, int groupId) {
        
        String sql = "INSERT INTO `visitor`"
                   + " (foreName, surName, title, age, level, pin, tourGroup)"
                   + " VALUES ( '" + visitor.forename + "','" + visitor.surname + "',"
                   + "'" + visitor.title + "'," + visitor.age + ",'" + visitor.level.toString() + "',"
                   + "" + visitor.pin + "," + groupId + ")";        
        return executor.executeUpdate(sql);
    }

    public synchronized ArrayList<Group> getAllGroups() {
        
        String sql = "SELECT * FROM `tourGroup`";
        ResultSet rs = executor.executeStatement(sql);
        return this.getTourGroupsFromResultSet(rs);
    }
    
    private synchronized ArrayList<Group> getTourGroupsFromResultSet(ResultSet rs){
        ArrayList<Group> groups = new ArrayList();
        
        try {
            while (rs.next()) {
                groups.add(getGroupFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistanceRepositoryQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return groups;
    }

    private Group getGroupFromResultSet(ResultSet rs) throws SQLException {
        
        int id = rs.getInt("id");
        int tour = rs.getInt("tourId");
        int leader = rs.getInt("leader");
        Date start = rs.getDate("startDate");
        Date expires = rs.getDate("expiryDate");
        boolean active = rs.getBoolean("active");
        ArrayList<Visitor> visitors = this.getAllVisitorsForGroup(id);
        Group group = new Group(id, tour, start, expires, active, visitors);
        group.setGroupLeader(leader);
        return group;
    }

    private ArrayList<Visitor> getAllVisitorsForGroup(int groupId) {
        
        String sql = "SELECT * FROM `visitor` `v`"
                   + " WHERE `v`.`tourGroup`=" + groupId;
        
        ResultSet rs = executor.executeStatement(sql);
        return this.getVisitorsFromResultSet(rs);
    }

    private ArrayList<Visitor> getVisitorsFromResultSet(ResultSet rs) {
        ArrayList<Visitor> visitors = new ArrayList();
        
        try {
            while (rs.next()) {
                visitors.add(getVisitorFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistanceRepositoryQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return visitors;
    }

    private Visitor getVisitorFromResultSet(ResultSet rs) throws SQLException {
        
        String title = rs.getString("title"); 
        String forename = rs.getString("foreName");
        String surname = rs.getString("surName");
        int age = rs.getInt("age");
        Levels level = Levels.valueOf(rs.getString("level"));             
        return new Visitor(title, forename, surname, age, level);
    }
}

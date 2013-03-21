/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

import persistance.DatabaseQueryExecutor;
import utility.DateUtil;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class ExhibitLog implements Log {

    DatabaseQueryExecutor db;
    Logger.LogAction action;

    public ExhibitLog(DatabaseQueryExecutor db, Logger.LogAction action) {
        this.db = db;
        this.action = action;
    }

    @Override
    public void log(String[] params) {
        String exhibitID = params[0];
        String exhibitName = params[1];
        String maintainerUsername = params[2];
        
        String SQL = "INSERT INTO ExhibitLog (action, exhibitID, exhibitName,maintainerUsername, logDate,logTime) VALUES ('" + action.name() + "','" + exhibitID + "','" + exhibitName + "','"+maintainerUsername+"','"+ DateUtil.getCurrentDate()+ "','" + DateUtil.getCurrentTime() + "');";
        db.executeUpdate(SQL);
    }
}

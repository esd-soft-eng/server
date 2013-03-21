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
public class TourLog implements Log {

    DatabaseQueryExecutor db;
    Logger.LogAction action;

    public TourLog(DatabaseQueryExecutor db, Logger.LogAction action) {
        this.db = db;
        this.action = action;
    }

    @Override
    public void log(String[] params) {
        String tourID = params[0];
        String tourName = params[1];
        String maintainerUsername = params[2];
        
        String SQL = "INSERT INTO TourLog (action, tourID, tourName,maintainerUsername, logDate,logTime) VALUES ('" + action.name() + "','" + tourID + "','" + tourName + "','"+maintainerUsername+"','"+ DateUtil.getCurrentDate() + "','" + DateUtil.getCurrentTime() + "');";
        db.executeUpdate(SQL);
    }
}

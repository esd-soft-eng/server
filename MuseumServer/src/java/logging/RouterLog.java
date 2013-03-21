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
public class RouterLog implements Log {
    DatabaseQueryExecutor db;
    Logger.LogAction action;

    public RouterLog(DatabaseQueryExecutor db, Logger.LogAction action) {
        this.db = db;
        this.action = action;
    }

    @Override
    public void log(String[] params) {
        String MAC = params[0];
        String maintainerUsername = params[1];
        String SQL = "INSERT INTO RouterLog (MAC, maintainerUsername, logDate,logTime) VALUES ('" + MAC + "','" + maintainerUsername + "','" + DateUtil.getCurrentDate() + "','" + DateUtil.getCurrentTime() + "');";
        db.executeUpdate(SQL);;
    }
}

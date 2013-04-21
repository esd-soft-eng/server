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
public class UserLog implements Log {

    DatabaseQueryExecutor db;
    Logger.LogAction action;

    public UserLog(DatabaseQueryExecutor db, Logger.LogAction action) {
        this.db = db;
        this.action = action;
    }

    @Override
    public void log(String[] params) {
        String usernameOfUser = params[0];
        String usernameOfMaintainer = params[1];

        String SQL = "INSERT INTO UserLog (action, usernameOfUser, maintainerUsername, logDate,logTime) VALUES ('" + action.name() + "','" + usernameOfUser + "','" + usernameOfMaintainer + "','" + DateUtil.getCurrentDate() + "','" + DateUtil.getCurrentTime() + "');";
        db.executeUpdate(SQL);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

import persistance.DatabaseQueryExecutor;

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
        String loginCode = params[0];
        String MAC = params[1];
        
        String SQL = "INSERT INTO RouterLog (action, loginCode ,MAC, logDate,logTime) VALUES ('" + action.name() + "','" + loginCode + "','" +MAC+"','"+ Logger.getDate() + "','" + Logger.getTime() + "');";
        db.executeUpdate(SQL);
    }
}

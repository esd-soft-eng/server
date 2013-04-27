package logging;

import persistance.DatabaseQueryExecutor;
import utility.DateUtil;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Log implementation which writes a log entry to the database whenever a
 * handset is added/removed/modified
 */
public class HandsetLog implements Log {

    DatabaseQueryExecutor db;
    Logger.LogAction action;

    public HandsetLog(DatabaseQueryExecutor db, Logger.LogAction action) {
        this.db = db;
        this.action = action;
    }

    @Override
    public void log(String[] params) {
        String MAC = params[0];
        String maintainerUsername = params[1];

        String SQL = "INSERT INTO HandsetLog (action, MAC ,maintainerUsername, logDate,logTime) VALUES ('" + action.name() + "','" + MAC + "','" + maintainerUsername + "','" + DateUtil.getCurrentDate() + "','" + DateUtil.getCurrentTime() + "');";
        db.executeUpdate(SQL);
    }
}

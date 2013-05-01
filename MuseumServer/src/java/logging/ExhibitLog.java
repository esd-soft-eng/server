package logging;

import persistance.DatabaseQueryExecutor;
import utility.DateUtil;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Log implementation which writes a log entry to the database whenever an
 * exhibit is added/removed/modified
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

        String SQL = "INSERT INTO ExhibitLog (action, exhibitID, exhibitName,maintainerUsername, logDate,logTime) VALUES ('" + action.name() + "','" + exhibitID + "','" + exhibitName + "','" + maintainerUsername + "','" + DateUtil.getCurrentDate() + "','" + DateUtil.getCurrentTime() + "');";
        db.executeUpdate(SQL);
    }
}

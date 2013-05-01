package logging;

import persistance.DatabaseQueryExecutor;
import utility.DateUtil;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Log implementation which writes a log entry to the database whenever a
 * handset logs into the system
 */
public class LoginLog implements Log {

    DatabaseQueryExecutor db;

    public LoginLog(DatabaseQueryExecutor db) {
        this.db = db;
    }

    @Override
    public void log(String[] params) {
        String loginCode = params[0];
        String SQL = "INSERT INTO LoginLog (loginCode, logDate,logTime) VALUES ('" + loginCode + "','" + DateUtil.getCurrentDate() + "','" + DateUtil.getCurrentTime() + "');";
        db.executeUpdate(SQL);
    }
}

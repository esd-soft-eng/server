package logging;

import persistance.DatabaseQueryExecutor;
import utility.DateUtil;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Log implementation which writes a log entry to the database whenever an
 * audio file is requested
 */
public class AudioLog implements Log {

    DatabaseQueryExecutor db;

    public AudioLog(DatabaseQueryExecutor db) {
        this.db = db;
    }

    @Override
    public void log(String[] params) {
        String loginCode = params[0];
        String audioID = params[1];
        String SQL = "INSERT INTO AudioLog (loginCode, audioID, logDate,logTime) VALUES ('" + loginCode + "','" + audioID + "','" + DateUtil.getCurrentDate() + "','" + DateUtil.getCurrentTime() + "');";
        db.executeUpdate(SQL);
    }
}

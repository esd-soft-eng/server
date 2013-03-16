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
public class WifiAudioLog implements Log {

    DatabaseQueryExecutor db;

    public WifiAudioLog(DatabaseQueryExecutor db) {
        this.db = db;
    }

    @Override
    public void log(String[] params) {
        String loginCode = params[0];
        String MAC = params[1];
        String SQL = "INSERT INTO WifiLog (loginCode, MAC, logDate,logTime) VALUES ('" + loginCode + "','" + MAC + "','" + Logger.getDate() + "','" + Logger.getTime() + "');";
        db.executeUpdate(SQL);
    }
}

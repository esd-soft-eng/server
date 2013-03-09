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
public class TourLog implements Log {

    DatabaseQueryExecutor db;
    LogFactory.LogAction action;

    public TourLog(DatabaseQueryExecutor db, LogFactory.LogAction action) {
        this.db = db;
        this.action = action;
    }

    @Override
    public void log(String[] params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

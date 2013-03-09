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
public class AudioLog implements Log {

    DatabaseQueryExecutor db;

    public AudioLog(DatabaseQueryExecutor db) {
        this.db = db;
    }

    @Override
    public void log(String[] params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

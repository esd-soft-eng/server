package reporting;

import java.sql.ResultSet;
import persistance.DatabaseQueryExecutor;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Simple static class which pulls all logs which fit in a certain
 * timeframe according to the table name provided
 */
public class ReportSQLUtility {

    public static ResultSet getAllReportsInPeriod(DatabaseQueryExecutor db, String tableName, String filterDate) {
        String SQL = "SELECT * FROM " + tableName + " WHERE STR_TO_DATE(logDate, '%d/%m/%Y') >= STR_TO_DATE('" + filterDate + "','%d/%m/%Y');";
        return db.executeStatement(SQL);
    }
}
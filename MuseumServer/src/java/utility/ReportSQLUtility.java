package utility;

import java.sql.ResultSet;
import persistance.DatabaseQueryExecutor;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class ReportSQLUtility {

    //Utility to grab all records from a table based on a period
    //"logDate" field must be present
    public static ResultSet getAllReportsInPeriod(DatabaseQueryExecutor db, String tableName, String filterDate) {
        String SQL = "SELECT * FROM "+tableName+" WHERE STR_TO_DATE(logDate, '%d/%m/%Y') >= STR_TO_DATE('" + filterDate + "','%d/%m/%Y');";
        return db.executeStatement(SQL);
    }
}
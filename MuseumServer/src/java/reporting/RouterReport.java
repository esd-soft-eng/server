/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporting;

import java.sql.ResultSet;
import java.sql.SQLException;
import persistance.DatabaseQueryExecutor;
import utility.DateUtil;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class RouterReport implements Report {

    @Override
    public String generateReport(String timePeriod, DatabaseQueryExecutor db) {
        int numWeeks = Integer.parseInt(timePeriod);

        String cutoffDate = DateUtil.getDateString(DateUtil.subtractWeeksFromCurrentDate(numWeeks));

        ResultSet reportResults = ReportSQLUtility.getAllReportsInPeriod(db, "RouterLog", cutoffDate);
        String overallHtml = "";
        try {
            if (!reportResults.isBeforeFirst()) {
                return "<h2> No logs exist for that period. </h2>";
            }

            overallHtml = "<table border='1'><tr><th>Log Number</th><th>Action</th><th>MAC</th>"
                    + "<th>Maintainer's Username</th><th>Date</th></tr>";
            while (reportResults.next()) {
                String logNumber = reportResults.getString("LogID");
                String MAC = reportResults.getString("MAC");
                String action = reportResults.getString("action");
                String maintainerUsername = reportResults.getString("maintainerUsername");
                String logDate = reportResults.getString("logDate");
                overallHtml += "<tr><td>" + logNumber + "</td><td>"+action+"</td><td>" + MAC
                        + "</td><td>" + maintainerUsername + "</td><td>" + logDate + "</td></tr>";
            }
            overallHtml += "</table>";
            return overallHtml;

        } catch (SQLException ex) {
            return "<h2> No logs exist for that period.</h2>";
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporting;

import utility.ReportSQLUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistance.DatabaseQueryExecutor;
import utility.DateUtil;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class ExhibitReport implements Report {

    @Override
    public String generateReport(String timePeriod, DatabaseQueryExecutor db) {
        int numWeeks = Integer.parseInt(timePeriod);

        String cutoffDate = DateUtil.getDateString(DateUtil.subtractWeeksFromCurrentDate(numWeeks));

        ResultSet reportResults = ReportSQLUtility.getAllReportsInPeriod(db, "ExhibitLog", cutoffDate);
        String overallHtml = "";
        try {
            if (!reportResults.isBeforeFirst()) {
                return "<h2> No logs exist for that period. </h2>";
            }

            overallHtml = "<table border='1'><tr><th>Log Number</th><th>Action</th>"
                    + "<th>Exhibit ID</th><th>Exhibit Name</th><th>Maintainer</th><th>Date</th></tr>";
            while (reportResults.next()) {
                String logNumber = reportResults.getString("LogID");
                String action = reportResults.getString("action");
                String exhibitID = reportResults.getString("exhibitID");
                String exhibitName = reportResults.getString("exhibitName");
                String maintainerUser = reportResults.getString("maintainerUsername");
                String logDate = reportResults.getString("logDate");
                overallHtml += "<tr><td>" + logNumber + "</td><td>" + action
                        + "</td><td>" + exhibitID + "</td><td>" + exhibitName
                        + "</td><td>" + maintainerUser + "</td><td>" + logDate
                        + "</td></tr>";
            }
            overallHtml += "</table>";
            return overallHtml;

        } catch (SQLException ex) {
            return "<h2> No logs exist for that period.</h2>";
        }

    }
}

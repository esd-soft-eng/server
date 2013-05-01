package reporting;

import utility.ReportSQLUtility;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistance.DatabaseQueryExecutor;
import utility.DateUtil;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Report implementation which generates a report describing the
 * directional information logs for a certain period
 */
public class WifiReport implements Report {

    @Override
    public String generateReport(String timePeriod, DatabaseQueryExecutor db) {
        int numWeeks = Integer.parseInt(timePeriod);

        String cutoffDate = DateUtil.getDateString(DateUtil.subtractWeeksFromCurrentDate(numWeeks));

        ResultSet reportResults = ReportSQLUtility.getAllReportsInPeriod(db, "WifiLog", cutoffDate);
        String overallHtml = "";
        try {
            if (!reportResults.isBeforeFirst()) {
                return "<h2> No logs exist for that period. </h2>";
            }

            overallHtml = "<table border='1'><tr><th>Log Number</th><th>Login Code</th><th>MAC</th>"
                    + "<th>Date</th></tr>";
            while (reportResults.next()) {
                String logNumber = reportResults.getString("LogID");
                String loginCode = reportResults.getString("loginCode");
                String MAC = reportResults.getString("MAC");
                String logDate = reportResults.getString("logDate");
                overallHtml += "<tr><td>" + logNumber + "</td><td>" + loginCode + "<td>" + MAC
                        + "<td>" + logDate + "</td></tr>";
            }
            overallHtml += "</table>";
            return overallHtml;

        } catch (SQLException ex) {
            return "<h2> No logs exist for that period.</h2>";
        }
    }
}

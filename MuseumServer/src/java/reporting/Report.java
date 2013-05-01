package reporting;

import persistance.DatabaseQueryExecutor;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: Base report interface which all report types implement
 */
public interface Report {
    public String generateReport(String timePeriod, DatabaseQueryExecutor db);
}

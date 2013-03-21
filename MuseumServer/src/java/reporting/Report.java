package reporting;

import persistance.DatabaseQueryExecutor;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public interface Report {
    public String generateReport(String timePeriod, DatabaseQueryExecutor db);
}

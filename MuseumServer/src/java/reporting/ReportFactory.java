package reporting;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class ReportFactory {

    public Report getFactory(ReportGeneratorServlet.ReportType reportType) {
        switch (reportType) {
            case AUDIO:
                return new AudioReport();
            case EXHIBIT:
                return new ExhibitReport();
            case HANDSET:
                return new HandsetReport();
            case LOGIN:
                return new LoginReport();
            case REGISTER:
                return new RegisterReport();
            case ROUTER:
                return new RouterReport();
            case TOUR:
                return new TourReport();
            case USER:
                return new UserReport();
            case WIFI:
                return new WifiReport();
            default:
                return null;
        }
    }
}

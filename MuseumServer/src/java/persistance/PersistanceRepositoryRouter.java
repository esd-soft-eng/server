package persistance;

import businessDomainObjects.Router;
import java.util.ArrayList;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class PersistanceRepositoryRouter {

    private DatabaseQueryExecutor executor;

    public PersistanceRepositoryRouter(DatabaseQueryExecutor executor) {
        this.executor = executor;
    }

    public synchronized boolean removeRouter(String MAC) {
        String removalSQL = "DELETE FROM routers WHERE MacID ='" + MAC + "';";
        return executor.executeUpdate(removalSQL);
    }

    public synchronized ArrayList<Router> getAllRouters() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

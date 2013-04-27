package persistance;

import businessDomainObjects.Router;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * @author Simon Edwins <Darkstar>
 * Desc: The PR class which is responsible for manipulating the database for all
 * Router related functionality
 */
public class PersistanceRepositoryRouter {

    private DatabaseQueryExecutor executor;

    public PersistanceRepositoryRouter(DatabaseQueryExecutor executor) {
        this.executor = executor;
    }

    public synchronized boolean addRouter(String mac, String audioLocation, String description) {
        String sql = "INSERT INTO `router` (MacID, AudioLocation, Description) "
                + "VALUES ('" + mac + "', '" + audioLocation + "', '" + description + "')";

        if (!executor.executeUpdate(sql)) {
            return false;
        }

        return true;
    }

    public synchronized boolean modifyRouter(String mac, String audioLocation, String description) {
        String sql = "UPDATE router "
                + "SET AudioLocation='" + audioLocation + "'"
                + ", Description='" + description + "'"
                + " WHERE router.MacID=" + mac;

        if (!executor.executeUpdate(sql)) {
            return false;
        }

        return true;
    }

    public synchronized boolean removeRouter(String MAC) {
        String removalSQL = "DELETE FROM routers WHERE MacID ='" + MAC + "';";
        return executor.executeUpdate(removalSQL);
    }

    public synchronized ArrayList<Router> getAllRouters() {
        String sql = "SELECT * FROM router";

        ResultSet rs = executor.executeStatement(sql);
        return mapResultSetToArrayList(rs);
    }

    private ArrayList<Router> mapResultSetToArrayList(ResultSet rs) {
        ArrayList<Router> routerList = new ArrayList();
        String macID = "";
        String audioLocation = "";
        String description = "";

        Router newRouter = null;

        try {
            while (rs.next()) {
                macID = rs.getString("macID");
                audioLocation = rs.getString("audioLocation");
                description = rs.getString("description");
                newRouter = new Router();
                newRouter.setAudioLocation(audioLocation);
                newRouter.setDescription(description);
                newRouter.setMACAddress(macID);
                routerList.add(newRouter);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistanceRepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return routerList;
    }
}

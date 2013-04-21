package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neil
 */
public class PersistanceRepositoryHandset {

    private DatabaseQueryExecutor executor;

    public PersistanceRepositoryHandset(DatabaseQueryExecutor executor) {
        this.executor = executor;
    }

    public HashSet<String> getAllowedDevices() {

        HashSet<String> allowedDevices = new HashSet<String>();

        String sql = "SELECT * FROM `alloweddevices`";

        createAllowedDevicesList(sql, allowedDevices);
        return allowedDevices;

    }

    private void createAllowedDevicesList(String sql, HashSet<String> allowedDevices) {

        ResultSet rs = executor.executeStatement(sql);

        try {
            while (rs.next()) {
                allowedDevices.add(rs.getString("macAddress"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistanceRepositoryUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addDevice(String macAddress) {
        String sql = "INSERT INTO "
                + "`alloweddevices` "
                + "VALUES ('" + macAddress + "')";

        executor.executeUpdate(sql);
    }

    public void removeDevice(String macAddress) {
        String sql = "DELETE FROM "
                + "`alloweddevices` "
                + "WHERE `macaddress` = '" + macAddress + "'";

        executor.executeUpdate(sql);
    }
}

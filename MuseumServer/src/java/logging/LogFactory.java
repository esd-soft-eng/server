package logging;

import persistance.DatabaseQueryExecutor;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class LogFactory {
    
    private DatabaseQueryExecutor db;

    public LogFactory(DatabaseQueryExecutor db) {
        this.db = db;
    }

    Log getFactory(Logger.LogType logType) {
        switch (logType) {
            case REGISTER:
                return new RegisterLog(db);
            case LOGIN:
                return new LoginLog(db);
            case AUDIO:
                return new AudioLog(db);
            case WIFI:
                return new WifiAudioLog(db);
            case EXHIBITADD:
                return new ExhibitLog(db, Logger.LogAction.ADD);
            case EXHIBITREM:
                return new ExhibitLog(db, Logger.LogAction.REMOVE);
            case EXHIBITMODIFY:
                return new ExhibitLog(db, Logger.LogAction.MODIFY);
            case HANDSETADD:
                return new HandsetLog(db, Logger.LogAction.ADD);
            case HANDSETREM:
                return new HandsetLog(db, Logger.LogAction.REMOVE);
            case ROUTERADD:
                return new RouterLog(db, Logger.LogAction.ADD);
            case ROUTERREM:
                return new RouterLog(db, Logger.LogAction.REMOVE);
            case TOURADD:
                return new TourLog(db, Logger.LogAction.ADD);
            case TOURREM:
                return new TourLog(db, Logger.LogAction.REMOVE);
            case TOURMOD:
                return new TourLog(db, Logger.LogAction.MODIFY);
            case USERADD:
                return new UserLog(db, Logger.LogAction.ADD);
            case USERREM:
                return new UserLog(db, Logger.LogAction.REMOVE);
            case USERMOD:
                return new UserLog(db, Logger.LogAction.MODIFY);
        }
        return null;
    }
}

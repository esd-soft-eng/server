package logging;

import persistance.DatabaseQueryExecutor;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * Desc: the factory class which returns the correct implementation of the log
 * interface upon receiving a request
 */
public class LogFactory {

    private DatabaseQueryExecutor db;

    public LogFactory(DatabaseQueryExecutor db) {
        this.db = db;
    }

    //simple method which determines which log object to use for the log type
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
            case EXHIBITREMOVE:
                return new ExhibitLog(db, Logger.LogAction.REMOVE);
            case EXHIBITMODIFY:
                return new ExhibitLog(db, Logger.LogAction.MODIFY);
            case HANDSETADD:
                return new HandsetLog(db, Logger.LogAction.ADD);
            case HANDSETREMOVE:
                return new HandsetLog(db, Logger.LogAction.REMOVE);
            case ROUTERADD:
                return new RouterLog(db, Logger.LogAction.ADD);
            case ROUTERREMOVE:
                return new RouterLog(db, Logger.LogAction.REMOVE);
            case TOURADD:
                return new TourLog(db, Logger.LogAction.ADD);
            case TOURREMOVE:
                return new TourLog(db, Logger.LogAction.REMOVE);
            case TOURMODIFY:
                return new TourLog(db, Logger.LogAction.MODIFY);
            case USERADD:
                return new UserLog(db, Logger.LogAction.ADD);
            case USERREMOVE:
                return new UserLog(db, Logger.LogAction.REMOVE);
            case USERMODIFY:
                return new UserLog(db, Logger.LogAction.MODIFY);
        }
        return null;
    }
}

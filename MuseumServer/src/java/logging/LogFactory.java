package logging;

import persistance.DatabaseQueryExecutor;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class LogFactory {

    public enum LogType {

        REG, LOGIN, AUDIO, WIFI, EXHIBITADD, EXHIBITREM, EXHIBITMODIFY,
        HANDSETADD, HANDSETREM, ROUTERADD, ROUTERREM, TOURADD, TOURREM, TOURMOD,
        USERADD, USERREM, USERMOD;
    };

    public enum LogAction {

        ADD, REMOVE, MODIFY
    };
    private DatabaseQueryExecutor db;

    public LogFactory(DatabaseQueryExecutor db) {
        this.db = db;
    }

    Log getFactory(LogType logType) {
        switch (logType) {
            case REG:
                return new RegisterLog(db);
            case LOGIN:
                return new LoginLog(db);
            case AUDIO:
                return new AudioLog(db);
            case WIFI:
                return new WifiAudioLog(db);
            case EXHIBITADD:
                return new ExhibitLog(db, LogAction.ADD);
            case EXHIBITREM:
                return new ExhibitLog(db, LogAction.REMOVE);
            case EXHIBITMODIFY:
                return new ExhibitLog(db, LogAction.MODIFY);
            case HANDSETADD:
                return new HandsetLog(db, LogAction.ADD);
            case HANDSETREM:
                return new HandsetLog(db, LogAction.REMOVE);
            case ROUTERADD:
                return new RouterLog(db, LogAction.ADD);
            case ROUTERREM:
                return new RouterLog(db, LogAction.REMOVE);
            case TOURADD:
                return new TourLog(db, LogAction.ADD);
            case TOURREM:
                return new TourLog(db, LogAction.REMOVE);
            case TOURMOD:
                return new TourLog(db, LogAction.MODIFY);
            case USERADD:
                return new UserLog(db, LogAction.ADD);
            case USERREM:
                return new UserLog(db, LogAction.REMOVE);
            case USERMOD:
                return new UserLog(db, LogAction.MODIFY);
        }
        return null;
    }
}

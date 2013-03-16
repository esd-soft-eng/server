package logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class Logger {

    public enum LogType {

        REGISTER, LOGIN, AUDIO, WIFI, EXHIBITADD, EXHIBITREMOVE, EXHIBITMODIFY,
        HANDSETADD, HANDSETREMOVE, ROUTERADD, ROUTERREMOVE, TOURADD, TOURREMOVE, TOURMODIFY,
        USERADD, USERREMOVE, USERMODIFY;
    };

    public enum LogAction {

        ADD, REMOVE, MODIFY
    };

    public static String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    private static LogFactory logFactory;

    public static void setLogFact(LogFactory logFact) {
        logFactory = logFact;
    }

    public static void Log(LogType logType, String[] params) {
        Log logger = logFactory.getFactory(logType);
        logger.log(params);
    }
}

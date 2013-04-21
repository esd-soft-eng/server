package logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class Logger {

    //Enumerate the various possible log types
    public enum LogType {

        REGISTER, LOGIN, AUDIO, WIFI, EXHIBITADD, EXHIBITREMOVE, EXHIBITMODIFY,
        HANDSETADD, HANDSETREMOVE, ROUTERADD, ROUTERREMOVE, TOURADD, TOURREMOVE, TOURMODIFY,
        USERADD, USERREMOVE, USERMODIFY;
    };

    //Some logs allow for different types of actions
    public enum LogAction {

        ADD, REMOVE, MODIFY
    };
    
    //The object which actually determines which log object to use
    private static LogFactory logFactory;

    public static void setLogFact(LogFactory logFact) {
        logFactory = logFact;
    }

    public static void Log(LogType logType, String[] params) {
        Log logger = logFactory.getFactory(logType);
        logger.log(params);
    }
}

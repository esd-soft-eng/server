package logging;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class Logger {

    private LogFactory logFactory;

    public void setLogFact(LogFactory logFact) {
        this.logFactory = logFact;
    }

    public void Log(LogFactory.LogType logType, String[] params) {
        Log logger = logFactory.getFactory(logType);
        logger.log(params);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class LogFactory {

    public static final int REG = 0, LOGIN = 1, AUDIO = 2, WIFI = 3, EXHIBITADD = 4, EXHIBITREM = 5,
            HANDSETADD = 6, HANDSETREM = 7, ROUTERADD = 8, ROUTERREM = 9, TOURADD = 10, TOURREM = 11,
            USERADD = 12, USERREM = 13;

    Log getFactory(int logType) {
        switch(logType)
        {
            case REG:
                break;
            case LOGIN:
                break;
            case AUDIO:
                break;
            case WIFI:
                break;
            case EXHIBITADD:
                break;
            case EXHIBITREM:
                break;
            case HANDSETADD:
                break;
            case HANDSETREM:
                break;
            case ROUTERADD:
                break;
            case ROUTERREM:
                break;
            case TOURADD:
                break;
            case TOURREM:
                break;
            case USERADD:
                break;
            case USERREM:
                break;
        }
    }
}

package utility;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class InputValidator {

    public static String clean(String inputString) {
        //Deliberately left out colon as this will break mac addresses
        //also allowed are commas and hyphens
        //add more as appropriate
        String badChars = "`<>!'\"&*^%$!()~#\\@£/?][}{|+=_"; 
        for(char c : badChars.toCharArray())
        {
            inputString = inputString.replace(String.valueOf(c), "");
        }
        //mysql comment character
        inputString = inputString.replace("--", "");
        return inputString;
    }
}

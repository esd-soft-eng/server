package utility;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 * @author Neil Donnelly <neil.m.donnelly@gmail.com>
 */
public class InputValidator {

    public static String clean(String inputString) {
        
        //Deliberately left out colon as this will break mac addresses
        //also allowed are commas and hyphens
        //add more as appropriate
        String badChars = "`<>!'\"&*^%$!()~#@£?][}{|+=_"; 
        for(char c : badChars.toCharArray())
        {
            inputString = inputString.replace(String.valueOf(c), "");
        }
        //mysql comment character
        inputString = inputString.replace("--", "");
        return inputString;
    }
    
    
    public static String escape(String inputString){
        
        //Same job as above method, but used to add an escape
        //slash to characters instead of removing them
        String escapeChars = "`<>!'\"&*^%$!()~#@£?][}{|+=_"; 
        for(char c : escapeChars.toCharArray())
        {
            inputString = inputString.replace(String.valueOf(c), "\\" + c);
        }
                
        inputString = inputString.replace("--", "");
        return inputString;
    }
}

package utility;

import java.io.File;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class FileUtil {

    public static ArrayList<String> listFilesInDir(ServletContext context, String directory) {
        String fullPath = getFileSystemPath(context, directory);
        ArrayList<String> strings = new ArrayList<String>();
        for (String s : new File(fullPath).list()) {
            strings.add(s);
        }
        return strings;

    }

    public static ArrayList<File> getFilesInDir(ServletContext context, String directory) {
        String fullPath = getFileSystemPath(context, directory);

        ArrayList<File> files = new ArrayList<File>();
        for (File f : new File(fullPath).listFiles()) {
            files.add(f);
        }
        return files;

    }

    public static String getFileSystemPath(ServletContext context, String resource) {
        return context.getRealPath(resource);

    }
}

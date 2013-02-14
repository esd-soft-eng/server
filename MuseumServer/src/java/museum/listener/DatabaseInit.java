package museum.listener;

import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import persistance.DatabaseQueryExecutor;

/**
 *
 * @author Alex
 */
public class DatabaseInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        // Initialise context and get our context variables from the DD
        ServletContext ctx = sce.getServletContext();
        String url = ctx.getInitParameter("dbURL");
        String user = ctx.getInitParameter("dbUser");
        String pass = ctx.getInitParameter("dbPassword");

        // Java trickery to get round the password being set to nothing
        if (pass.equals("null")) {
            pass = "";
        }

        DatabaseQueryExecutor db = new DatabaseQueryExecutor(url, user, pass);
        System.out.println("helll yeeaaaah!");
        
        ctx.setAttribute("dataBean", db);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // move along ... nothing happening here ...
    }
}
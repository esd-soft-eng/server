package museum.listener;

import domainObjects.UserManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import persistance.DatabaseQueryExecutor;
import persistance.PersistanceRepositoryUser;

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
        PersistanceRepositoryUser pr = new PersistanceRepositoryUser(db);
        UserManager um = new UserManager(pr);
        
        ctx.setAttribute("persistance", pr);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // move along ... nothing happening here ...
    }
}
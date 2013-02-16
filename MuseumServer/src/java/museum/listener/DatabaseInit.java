package museum.listener;

import domainObjects.HandsetAccessManager;
import domainObjects.UserManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import persistance.DatabaseQueryExecutor;
import persistance.PersistanceRepositoryHandset;
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
        DatabaseQueryExecutor db = initDatabaseConnection(ctx);

        //init managers and set them in the context    
        ctx.setAttribute("userManager", initUserManager(db));
        ctx.setAttribute("handsetAccessManager", initHandsetAccessManager(db));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // move along ... nothing happening here ...
    }

    private DatabaseQueryExecutor initDatabaseConnection(ServletContext ctx) {
        String url = ctx.getInitParameter("dbURL");
        String user = ctx.getInitParameter("dbUser");
        String pass = ctx.getInitParameter("dbPassword");
        // Java trickery to get round the password being set to nothing
        if (pass.equals("null")) {
            pass = "";
        }
        DatabaseQueryExecutor db = new DatabaseQueryExecutor(url, user, pass);
        return db;
    }

    private UserManager initUserManager(DatabaseQueryExecutor db) {
        //user persistance and manipulation
        PersistanceRepositoryUser pr = new PersistanceRepositoryUser(db);
        return new UserManager(pr);
    }

    private HandsetAccessManager initHandsetAccessManager(DatabaseQueryExecutor db) {
        //handset access persistance and manipulation
        PersistanceRepositoryHandset hr = new PersistanceRepositoryHandset(db);
        return new HandsetAccessManager(hr);
    }
}
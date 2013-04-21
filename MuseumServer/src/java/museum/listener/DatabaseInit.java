package museum.listener;

import QuestionsAndAnswers.QuestionSetManager;
import businessDomainObjects.AudioManager;
import businessDomainObjects.ExhibitManager;
import businessDomainObjects.HandsetAccessManager;
import businessDomainObjects.RouterManager;
import businessDomainObjects.TourManager;
import businessDomainObjects.UserManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import logging.LogFactory;
import logging.Logger;
import persistance.DatabaseQueryExecutor;
import persistance.PersistanceRepositoryAudio;
import persistance.PersistanceRepositoryExhibit;
import persistance.PersistanceRepositoryGroups;
import persistance.PersistanceRepositoryHandset;
import persistance.PersistanceRepositoryQuestions;
import persistance.PersistanceRepositoryRouter;
import persistance.PersistanceRepositoryTour;
import persistance.PersistanceRepositoryUser;
import visitorsAndGroups.GroupManager;

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
        ctx.setAttribute("tourManager", initTourManager(db));
        ctx.setAttribute("exhibitManager", initExhibitManager(db));
        ctx.setAttribute("audioManager", initAudioManager(db));
        ctx.setAttribute("questionSetManager", initQuestionSetManager(db));
        ctx.setAttribute("routerManager", initRouterManager(db));
        ctx.setAttribute("databaseExecutor", db);
        ctx.setAttribute("groupManager", initGroupManager(db));
        Logger.setLogFact(new LogFactory(db));
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

    private TourManager initTourManager(DatabaseQueryExecutor db) {
        PersistanceRepositoryTour pr = new PersistanceRepositoryTour(db);
        return new TourManager(pr);
    }

    private ExhibitManager initExhibitManager(DatabaseQueryExecutor db) {
        PersistanceRepositoryExhibit pr = new PersistanceRepositoryExhibit(db);
        return new ExhibitManager(pr);
    }

    private AudioManager initAudioManager(DatabaseQueryExecutor db) {
        PersistanceRepositoryAudio pr = new PersistanceRepositoryAudio(db);
        return new AudioManager(pr);
    }

    private QuestionSetManager initQuestionSetManager(DatabaseQueryExecutor db) {
        PersistanceRepositoryQuestions pr = new PersistanceRepositoryQuestions(db);
        return new QuestionSetManager(pr);
    }

    private RouterManager initRouterManager(DatabaseQueryExecutor db) {
        PersistanceRepositoryRouter pr = new PersistanceRepositoryRouter(db);
        return new RouterManager(pr);
    }
    
    private GroupManager initGroupManager(DatabaseQueryExecutor db) {
        PersistanceRepositoryGroups pr = new PersistanceRepositoryGroups(db);
        return new GroupManager(pr);
    }
}

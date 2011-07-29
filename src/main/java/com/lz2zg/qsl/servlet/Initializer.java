package com.lz2zg.qsl.servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.lz2zg.qsl.model.QslCard;
import com.lz2zg.qsl.model.QslCardDao;
import com.lz2zg.qsl.model.QslCardDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        QslCardDao dao = new QslCardDaoImpl(datastore);
        ServletContext context = sce.getServletContext();
        context.setAttribute("dao", dao);
        MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService();
        OAuth2ClientImpl oauth2client = new OAuth2ClientImpl(datastore, memcacheService);
        context.setAttribute("oauth2client", oauth2client);
        //populateTestData(dao);
    }

    void populateTestData(QslCardDao dao) {
        String[] front = new String[] {"http://files.lz2zg.com/3DA0WW_1.JPG", "http://files.lz2zg.com/5W0NU_1.JPG"};
        String[] back = new String[] {"http://files.lz2zg.com/3DA0WW_2.JPG", "http://files.lz2zg.com/5W0NU_2.JPG"};
        for (int i = 0 ; i < 100 ; i++) {
            QslCard qslCard = new QslCard();
            qslCard.setCallsign(i + "LZ2ZG");
            qslCard.setFrontImageUrl(front[i % front.length]);
            qslCard.setBackImageUrl(back[i % front.length]);
            dao.add(qslCard);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}

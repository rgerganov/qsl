package com.lz2zg.qsl.servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
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
        //populateTestData(dao);
    }

    void populateTestData(QslCardDao dao) {
        String[] urls = new String[] {"/imgs/3DA0WW_1.JPG", "/imgs/5W0NU_1.JPG"};
        for (int i = 0 ; i < 100 ; i++) {
            QslCard qslCard = new QslCard();
            qslCard.setCallsign("LZ2ZG" + i);
            qslCard.setFrontImageUrl(urls[i % urls.length]);
            qslCard.setBackImageUrl(urls[i % urls.length]);
            dao.add(qslCard);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}

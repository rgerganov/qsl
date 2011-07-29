package com.lz2zg.qsl.model;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.List;

public class QslCardDaoImpl implements QslCardDao {

    public static final int NEXT_COUNT = 3;
    public static final int PREV_COUNT = 4;
    final DatastoreService datastore;

    public QslCardDaoImpl(DatastoreService datastore) {
        this.datastore = datastore;
    }

    @Override
    public Page getPage(String query, int pageSize, int pageNumber) {
        Key parentKey = KeyFactory.createKey("QSL", "LZ2ZG");
        Query dsQuery = new Query(QslCard.class.getName(), parentKey);
        if (query != null) {
            query = query.toUpperCase();
            dsQuery.addFilter("callsign", Query.FilterOperator.GREATER_THAN_OR_EQUAL, query);
            dsQuery.addFilter("callsign", Query.FilterOperator.LESS_THAN, query + Character.MAX_VALUE);
        }
        int limit = NEXT_COUNT * pageSize;
        int offset = (pageNumber - 1) * pageSize;
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(limit).offset(offset);
        List<Entity> entities = datastore.prepare(dsQuery).asList(fetchOptions);
        Page result = new Page();
        if (!entities.isEmpty()) {
            int totalCount = entities.size();
            int pageCount = Math.min(totalCount, pageSize);
            addEntitiesToPage(result, entities.subList(0, pageCount));
            result.setStartPage(Math.max(1, pageNumber - PREV_COUNT));
            int endPage = pageNumber + ((totalCount - 1) / pageSize);
            result.setEndPage(endPage);
        }
        return result;
    }

    void addEntitiesToPage(Page page, List<Entity> entities) {
        List<QslCard> cards = new ArrayList<QslCard>();
        for (Entity entity : entities) {
            QslCard card = entityToCard(entity);
            cards.add(card);
        }
        page.setCards(cards);
    }

    @Override
    public void add(QslCard qslCard) {
        Key parentKey = KeyFactory.createKey("QSL", "LZ2ZG");
        Entity entity = new Entity(QslCard.class.getName(), parentKey);
        fillEntity(entity, qslCard);
        datastore.put(entity);
    }

    @Override
    public void delete(long id) {
        Key parentKey = KeyFactory.createKey("QSL", "LZ2ZG");
        Key key = KeyFactory.createKey(parentKey, QslCard.class.getName(), id);
        datastore.delete(key);
    }

    @Override
    public QslCard get(long id) {
        Key parentKey = KeyFactory.createKey("QSL", "LZ2ZG");
        Key key = KeyFactory.createKey(parentKey, QslCard.class.getName(), id);
        try {
            Entity entity = datastore.get(key);
            return entityToCard(entity);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public void update(QslCard qslCard) {
        Key parentKey = KeyFactory.createKey("QSL", "LZ2ZG");
        Entity entity = new Entity(QslCard.class.getName(), qslCard.getId(), parentKey);
        fillEntity(entity, qslCard);
        datastore.put(entity);
    }

    void fillEntity(Entity entity, QslCard qslCard) {
        entity.setProperty("callsign", qslCard.getCallsign());
        entity.setProperty("frontImageUrl", qslCard.getFrontImageUrl());
        entity.setProperty("backImageUrl", qslCard.getBackImageUrl());
    }

    QslCard entityToCard(Entity entity) {
        String callsign = (String) entity.getProperty("callsign");
        String frontUrl = (String) entity.getProperty("frontImageUrl");
        String backUrl = (String) entity.getProperty("backImageUrl");
        QslCard card = new QslCard();
        card.setId(entity.getKey().getId());
        card.setCallsign(callsign);
        card.setFrontImageUrl(frontUrl);
        card.setBackImageUrl(backUrl);
        return card;
    }
}

package com.lz2zg.qsl.servlet;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OAuth2ClientImpl implements OAuth2Client {

    static final Logger log = Logger.getLogger(OAuth2ClientImpl.class.getName());
    static final String OAUTH_URL = "https://accounts.google.com/o/oauth2/token";
    final DatastoreService datastore;
    final MemcacheService memcacheService;
    final static String ENC = "UTF-8";
    final static String ACCESS_TOKEN_KEY = "accessToken";

    public OAuth2ClientImpl(DatastoreService datastore, MemcacheService memcacheService) {
        this.datastore = datastore;
        this.memcacheService = memcacheService;
    }

    @Override
    public String getAccessToken() {
        return (String) memcacheService.get(ACCESS_TOKEN_KEY);
    }

    @Override
    public String getAccessToken(String refreshToken) {
        try {
            String data = String.format("client_id=%s&client_secret=%s&refresh_token=%s&grant_type=refresh_token",
                    URLEncoder.encode(CLIENT_ID, ENC),
                    URLEncoder.encode(CLIENT_SECRET, ENC),
                    URLEncoder.encode(refreshToken, ENC));
            String response = doPost(data);
            log.info("Response after sending refreshToken: " + response);
            String accessToken = parseStringField("access_token", response);
            int expiresIn = parseIntField("expires_in", response);
            memcacheService.put(ACCESS_TOKEN_KEY, accessToken, Expiration.byDeltaSeconds(expiresIn));
            return accessToken;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fetchAccessAndRefreshTokens(String authCode) {
        try {
            String data = String.format("code=%s&client_id=%s&client_secret=%s&redirect_uri=%s&grant_type=authorization_code",
                                            URLEncoder.encode(authCode, ENC),
                                            URLEncoder.encode(CLIENT_ID, ENC),
                                            URLEncoder.encode(CLIENT_SECRET, ENC),
                                            URLEncoder.encode(REDIRECT_URI, ENC)); // what is this for?
            String response = doPost(data);
            log.info("Response after sending authCode: " + response);
            String accessToken = parseStringField("access_token", response);
            String refreshToken = parseStringField("refresh_token", response);
            int expiresIn = parseIntField("expires_in", response);
            memcacheService.put(ACCESS_TOKEN_KEY, accessToken, Expiration.byDeltaSeconds(expiresIn));
            saveRefreshToken(refreshToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Integer parseIntField(String field, String text) {
        Pattern p = Pattern.compile(String.format("\"%s\":(\\d+)", field));
        Matcher m = p.matcher(text);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        return null;
    }

    String parseStringField(String field, String text) {
        Pattern p = Pattern.compile(String.format("\"%s\":\"(.+?)\"", field));
        Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    String doPost(String data) throws IOException {
        URL url = new URL(OAUTH_URL);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.getOutputStream().write(data.getBytes(ENC));
        conn.getOutputStream().flush();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = null;
        String response = "";
        while ((line = rd.readLine()) != null) {
            response += line;
        }
        rd.close();
        return response;
    }

    @Override
    public String getRefreshToken() {
        Key key = KeyFactory.createKey("refreshToken", "GoogleStorage");
        try {
            Entity entity = datastore.get(key);
            return (String) entity.getProperty("value");
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    void saveRefreshToken(String refreshToken) {
        Key key = KeyFactory.createKey("refreshToken", "GoogleStorage");
        Entity entity = new Entity(key);
        entity.setProperty("value", refreshToken);
        datastore.put(entity);
    }
}

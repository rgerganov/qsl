package com.lz2zg.qsl.servlet;


public interface OAuth2Client {

    String CLIENT_ID = "348460834037.apps.googleusercontent.com";

    // this is useless until coupled with refresh token,
    // so it safe to be hardcoded as a constant
    String CLIENT_SECRET = "11DrV8S8kCglvYrxlG5Gt61Y";

    String REDIRECT_URI = "http://localhost:8080/admin/oauth2callback";

    String getAccessToken();

    String getAccessToken(String refreshToken);

    void fetchAccessAndRefreshTokens(String authCode);

    String getRefreshToken();
}

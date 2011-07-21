package com.lz2zg.qsl.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesService.OutputEncoding;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.lz2zg.qsl.model.QslCard;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class UploadServlet extends HttpServlet {

    public static final String GS_BUCKET = "http://commondatastorage.googleapis.com/files.lz2zg.com/";
    public static final String PUBLIC_URL = "http://files.lz2zg.com/";

    private static final long serialVersionUID = 1L;
    private static final int THUMB_W = 415;
    private static final int THUMB_H = 265;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accessToken = (String) req.getAttribute("accessToken");
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            resp.sendError(400);
            return;
        }
        ServletFileUpload upload = new ServletFileUpload();
        try {
            FileItemIterator iter = upload.getItemIterator(req);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                if (item.isFormField()) {
                    resp.sendError(400);
                    return;
                } else {
                    upload(item, accessToken);
                    String fileName = item.getName();
                    resp.setContentType("text/html");
                    resp.getWriter().println("Upload successful: " + PUBLIC_URL + fileName);
                    return;
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
        resp.sendError(400);
    }

    void upload(FileItemStream item, String accessToken) throws IOException {
        InputStream is = item.openStream();
        byte[] imageData = IOUtils.toByteArray(is);
        String fileName = item.getName();
        upload(fileName, imageData, item.getContentType(), accessToken);
        ImagesService imagesService = ImagesServiceFactory.getImagesService();
        Image image = ImagesServiceFactory.makeImage(imageData);
        Transform resize = ImagesServiceFactory.makeResize(THUMB_W, THUMB_H);
        Image thumb = imagesService.applyTransform(resize, image, OutputEncoding.JPEG);
        String thumbName = QslCard.getThumbName(fileName);
        upload(thumbName, thumb.getImageData(), item.getContentType(), accessToken);
    }

    void upload(String fileName, byte[] data, String contentType, String accessToken) throws IOException {
        URL url = new URL(GS_BUCKET + fileName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        conn.addRequestProperty("Authorization", "OAuth " + accessToken);
        conn.addRequestProperty("Content-Type", contentType);
        conn.addRequestProperty("x-goog-api-version", "2");
        conn.addRequestProperty("x-goog-acl", "public-read");
        conn.setChunkedStreamingMode(0);
        OutputStream os = conn.getOutputStream();
        os.write(data);
        os.close();
        int respCode = conn.getResponseCode();
        if (respCode != 200) {
            throw new IOException("Unexpected response code: " + respCode);
        }
    }
}

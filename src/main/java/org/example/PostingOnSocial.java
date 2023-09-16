package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.asynchttpclient.util.UriEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class PostingOnSocial {
    CloseableHttpClient httpClient;
    PostingOnSocial(){

    }

    /**
     * Использует GET-запрос
     * @param GROUP_ID
     * @param ACCESS_TOKEN_APP
     * @param message
     * @return Успех выполнения запроса
     * @throws Exception
     */
    public boolean publishToVkGET(Integer GROUP_ID, String ACCESS_TOKEN_APP, MessageVK message) throws Exception {
        httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder("https://api.vk.com/method/wall.post");
            builder.setParameter("v", "5.131")
                    .setParameter("owner_id", "-" + GROUP_ID)
                    .setParameter("message", message.text)
                    .setParameter("access_token", ACCESS_TOKEN_APP);

            URI uri = builder.build();
            HttpGet getRequest = new HttpGet(uri);

            CloseableHttpResponse response = httpClient.execute(getRequest);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                // На всякий случай:
                httpClient.close();
                return true;
            } else {
                assert response != null;
                throw new IOException("Сервер вернул ошибку: " + response.getStatusLine());
            }

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            httpClient.close();
        }
    }

    /**
     * Использует POST-запрос
     * @param GROUP_ID
     * @param ACCESS_TOKEN_APP
     * @param message
     * @return Успех выполнения отправки
     * @throws Exception
     */
    public boolean publishToVk(Integer GROUP_ID, String ACCESS_TOKEN_APP, MessageVK message) throws Exception {
        httpClient = HttpClients.createDefault();
        try {
            //TODO: прикол с версионкой можно исправить потом...
            HttpPost postRequest = new HttpPost("https://api.vk.com/method/wall.post?v=5.131");
            List<NameValuePair> params =  new ArrayList<>();
            params.add(new BasicNameValuePair("owner_id", "-"+GROUP_ID));
            params.add(new BasicNameValuePair("message", message.text));
            params.add(new BasicNameValuePair("access_token", ACCESS_TOKEN_APP));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
            postRequest.setEntity(entity);


            CloseableHttpResponse response = httpClient.execute(postRequest);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println(responseString);

            }
            if (response != null && response.getStatusLine().getStatusCode() == 200){
                // На всякий случай:
                httpClient.close();
                return true;
            } else {
                assert response != null;
                throw new IOException("Сервер вернул ошибку: "+response.getStatusLine());
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            httpClient.close();
        }

    }

    /**
     * Использует GET-запрос
     * @param TG_BOT_TOKEN
     * @param CHAT_ID
     * @param message
     * @return Ответ сервера
     */
    public String publishToTelegram(String TG_BOT_TOKEN, String CHAT_ID, MessageTG message) throws Exception {
        httpClient = HttpClients.createDefault();
        try {
            if (message.text.isEmpty()) {
                return "Ошибка: текст сообщения пуст.";
            }

            String urlQuery = "https://api.telegram.org/bot" + TG_BOT_TOKEN + "/sendMessage" + "?chat_id=" + CHAT_ID + "&text=" + URLEncoder.encode(message.text, "UTF-8");

            // вполняем запрос
            URL url = new URL(urlQuery);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();

            // ответ:
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}

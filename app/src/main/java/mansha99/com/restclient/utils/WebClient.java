package mansha99.com.restclient.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import mansha99.com.restclient.models.Contact;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebClient {

    public static String FetchContacts(Integer page, Integer size, String sort, String dir) throws IOException {
        String s = String.format("players?page=%d&size=%d&sort=%s&dir=%s", page, size, sort, dir);
        return GET(s);
    }
    public static String SaveContact(Contact model) throws IOException {
        return JSON("players",model);
    }



    private static String Absolute(String url) {
        return "http://192.168.42.167:8080/" + url;
        //return "http://localhost:8080/"+url;
    }

    private static String GET(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Absolute(url))
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    private static String JSON(String url,Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json= mapper.writeValueAsString(object);
        MediaType JSON= MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(Absolute(url))
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }




package mki.kehrwochenprojekt.mobilecomputing_sose17.Utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RESTClient {

    private String url;
    HttpURLConnection connection;

    public RESTClient(String apiURL) {
        if (apiURL == null || apiURL.length() <= 0) {
            throw new IllegalArgumentException("Invalid apiURL specified");
        }

        this.url = apiURL;
    }


    public String sendRequest(String route, String payload, String requestType) throws IOException {
        System.out.println("Inside sendRequest");
        if (route == null) {
            throw new IllegalArgumentException("Invalid route specified");
        }
        if (requestType == null || !validRequest(requestType)) {
            throw new IllegalArgumentException("Invalid requestType specified");
        }
        System.out.println("Arguments O.K.");


        if (payload != null) {
            System.out.println("Payload OK");
            if (!isGet(requestType)) {
                System.out.println("not a GET request");
                URL tempURL = new URL(url + route);
                connection = (HttpURLConnection) tempURL.openConnection();
                connection.setRequestMethod(requestType);
                String request = payload;

                byte[] postData = request.getBytes();
                int postDataSize = postData.length;

                connection.setDoOutput(true);
                connection.setInstanceFollowRedirects(false);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("charset", "utf-8");
                connection.setRequestProperty("Content-Length", Integer.toString(postDataSize));
                connection.setUseCaches(false);

                System.out.println("Connection setup complete");
                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                System.out.println("Stream open");
                dos.write(postData);
                dos.flush();
                dos.close();


            } else {
                System.out.println("Its a get");
                //If it is a GET instead AND we have a payload AS PARAMETERS
                URL tempURL = new URL(url + route + payload);
                connection = (HttpURLConnection) tempURL.openConnection();
                connection.setRequestMethod(requestType);
                System.out.println("RESTCLient: Created URL " + tempURL.toString());
                connection.connect();
                Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (int c; (c = in.read()) >= 0; ) {
                    sb.append((char) c);
                }
                String response = sb.toString();
                connection.disconnect();
                return response;
            }
            System.out.println("Now reading response");
            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0; ) {
                sb.append((char) c);
            }
            System.out.println("Response read succesfully, returnign");
            String response = sb.toString();
            return response;
        }

        throw new IllegalArgumentException("Expected valid arguments");


    }

    private boolean validRequest(String type) {
        if (type != null) {
            return (type.equals("POST") || type.equals("GET") || type.equals("PUT") || type.equals("DELETE") || type.equals("PATCH"));
        }
        return false;
    }

    private boolean isGet(String route) {
        return route.equals("GET");
    }
}
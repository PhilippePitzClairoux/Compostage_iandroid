package com.compostage.Data;

import java.net.*;
import java.io.*;

public class HttpRequester implements Serializable {

    private URL url;

    public HttpRequester(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public String processRequest() throws IOException {

        URLConnection urlConnection = this.url.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        urlConnection.getInputStream()));

        String response = "";
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("{"))
                response += inputLine;
        }
        in.close();

        return response;
    }


}

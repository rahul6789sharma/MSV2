package org.stocksrin.telegram.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TelegramNotifactionUtils {


    private static final String seprator = "-------------------------------------";

    public static void main(String[] args) throws Exception {

        System.out.println("↑");


    }

    public static void sendNotifcation(String messsage)  {
        try {
            String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

            String apiToken = "1305807183:AAEwlR391Donp64z8j0RDctZS0goT_7esio";
            String chatId = "-439206897";
            // String chatId = "@stocksrin";
            //String text = maxPainMessages();

            urlString = String.format(urlString, apiToken, chatId, messsage);
            //urlString=encode(urlString);
            System.out.println(urlString);
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();

            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String response = sb.toString();
            System.out.println(response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private static String maxPainMessages() {
        StringBuilder response = new StringBuilder();

        response.append(seprator);
        response.append("%0A");
        response.append("Alert: MaxPain Changed");
        response.append("%0A");
        response.append(seprator);
        response.append("%0A");
        response.append("%0A");
        response.append("Nifty Max Pain : [ " + " 11200 ]");
        response.append("%0A");
        response.append("%0A");
        //response.append("Updated Time : ")
        response.append(seprator);
        System.out.println(response.toString());
        return response.toString();


    }
}

package testGETPOST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gfox on 13/05/2016.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        //######################### GET REQUEST #######################
       /* URL url = new URL("http://echo.getpostman.com/get?test=123");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(huc.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        Gson gson = new Gson();
        PostmanClass obj =gson.fromJson(response.toString(), PostmanClass.class);
        obj.getUrl();*/

        //########################## POST REQUEST ##########################
        String postParameters = "{\"numOfDays\":1, \"checkInDate\":\"2013-04-10\"}";
        StringBuffer jsonString = new StringBuffer();
        String line;

        URL url = new URL("http://localhost:9090/bookRoom");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("POST");
        huc.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(huc.getOutputStream(), "UTF-8");
        writer.write(postParameters);
        writer.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }
        br.close();
        huc.disconnect();
        System.out.println(jsonString.toString());

    }
}

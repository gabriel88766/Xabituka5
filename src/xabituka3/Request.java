/*
 * Developed by Gabriel Vinícius
 * Method requesting: returns a string from the url requested. params : String url.
 * 
 */
package xabituka3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Request {
    
    private static HttpURLConnection con;

    public static String requesting(String url) throws MalformedURLException,
            ProtocolException, IOException {


        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            return content.toString();

        } finally {
            
            con.disconnect();
        }
    }
}
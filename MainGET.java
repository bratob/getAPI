import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MainGET {

    public static void main(String[] args) {
        try {

            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=dresden&appid=8fd2d96a373a341230be93f9623e3961&units=metric");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                System.out.println(data_obj.toString());

                //Get the required object from the above created object
                JSONObject obj = (JSONObject) data_obj.get("main");

                //Get the required data using its key
                System.out.println("Aktuelle Temperatur in der Stadt Dresden: " + obj.get("temp"));

                //Get the required object from the above created object

                obj = (JSONObject) data_obj.get("wind");
                System.out.println("Aktuelle Windgeschwindigkeit in der Stadt Dresden beträgt: " + obj.get("speed"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

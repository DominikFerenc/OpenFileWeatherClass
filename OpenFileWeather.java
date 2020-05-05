import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.*;

public class OpenFileWeather
{
    private static String json_data = null;
    private static final String urlServer = "https://danepubliczne.imgw.pl/api/data/synop.json";


    public OpenFileWeather()
    {
        GetDataFromJSONFile();
    }

    public static void GetDataFromJSONFile()
    {
        BufferedReader buffer = null;
        try
        {
            URL url = new URL(urlServer);
            buffer = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));

            StringBuffer bufferString = new StringBuffer();


            while ((json_data = buffer.readLine()) != null)
                bufferString.append(json_data);

            String newString = bufferString.toString();
            JSONArray jsonArray = new JSONArray(newString);
            JSONObject jsonObject = jsonArray.getJSONObject(59);
            System.out.println(jsonObject);
            String nameStation = jsonObject.getString("stacja");
            double temp = jsonObject.getDouble("temperatura");
            System.out.println("Stacja: " + nameStation);
            System.out.println("Temperatura: " + temp); 

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException je)
        {
            je.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        OpenFileWeather openFile = new OpenFileWeather();
    }
}

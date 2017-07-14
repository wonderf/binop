package moais.websocket.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by Stas on 20.12.2016.
 */
public class DataCollector {
    /*
    guide for url
    https://developer.yahoo.com/yql/guide/yql_url.html
     */
    final private static String url = "https://query.yahooapis.com/v1/public/yql?q=" +
            "select+*+from+yahoo.finance.xchange+" +
            "where+pair+=+%22USDRUB%22&" +
            "format=json&" +
            "env=store%3A%2F%2Fdatatables.org/alltableswithkeys&callback=";

    final private static String historyUrl = "https://query.yahooapis.com/v1/public/yql?q=" +
            "select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22RUB%3DX%22%20" +
            "and%20startDate%20%3D%20%222009-09-11%22%20and%20endDate%20%3D%20%222010-03-10%22" +
            "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

    private URL request;
    private URL historyRequest;

    private static DataCollector ourInstance = new DataCollector();

    public static DataCollector getInstance() {
        return ourInstance;
    }

    private DataCollector() {
        Calendar cd = Calendar.getInstance();
        String endDate = String.format("%tY-%<tm-%<td",cd);
        cd.add(Calendar.WEEK_OF_MONTH,-1);
        String historyUrl = "https://query.yahooapis.com/v1/public/yql?q=" +
                "select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22RUB%3DX%22%20" +
                "and%20startDate%20%3D%20%22"+String.format("%tY-%<tm-%<td",cd)+"%22%20and%20endDate%20%3D%20%22"+endDate+"%22" +
                "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        try {
            request = new URL(url);
            historyRequest = new URL(historyUrl);
        } catch (MalformedURLException e) {
            System.out.println("cant resolve url");
        }
    }
    public String getData(){
        return getDataFromUrl(request);
    }

    public String getLastWeekData(){
        //TODO: Найти откуда брать данные для истории (для построения графика до момента запуска)
        return getDataFromUrl(historyRequest);
    }

    private String getDataFromUrl(URL from){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(from.openStream()))){
            String line;
            while((line=bf.readLine())!=null)
                sb.append(line);
            return sb.toString();
        }
        catch (Exception e){System.out.println("cant read data from url");}
        return null;
    }
}

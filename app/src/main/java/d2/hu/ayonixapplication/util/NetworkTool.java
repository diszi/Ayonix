package d2.hu.ayonixapplication.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkTool {

    private static int CONNECTION_TIME_OUT = 5000;
    private static int READ_TIME_OUT = 5000;



    public static HttpURLConnection createConnection(String url) throws IOException {
        return createConnection(url,null,null,false,true);
    }


    public static HttpURLConnection createSOAPConnection(String url, String soapAction,String soapPayload) throws IOException {
        // System.out.println(" --> soap  = "+soapPayload);
        return createConnection(url, soapAction,soapPayload, true,true);
    }

    public static HttpURLConnection createSOAPGETConnection(String url, String soapAction, String soapPayload) throws IOException {
        return createConnection(url, soapAction, soapPayload, true,false);
    }

    private static synchronized HttpURLConnection createConnection(String url, String soapAction, String soapPayload, boolean isSoap,boolean isPost) throws IOException {
         URL serviceUrl = new URL("http://192.168.100.21:8888/");
//        URL serviceUrl = new URL("tns:BasicHttpBinding_IAyonixService");
        System.out.println("NETWORKTOOL ===> service Url = "+serviceUrl+"\n soap Action = "+soapAction);
        HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(isPost?"POST":"GET");
        connection.setDoInput(true);
        connection.setConnectTimeout(CONNECTION_TIME_OUT);
        connection.setReadTimeout(READ_TIME_OUT);
        String connLength="";

        if (isSoap){
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setRequestProperty("charset", "UTF-8");
            connection.setRequestProperty("SOAPAction","http://tempuri.org/IAyonixService/GetData");

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(soapPayload.getBytes());

        } else {
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        }
        System.out.println("Connection ==> "+connection);
        return connection;
    }
}

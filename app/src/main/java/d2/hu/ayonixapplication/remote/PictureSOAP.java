package d2.hu.ayonixapplication.remote;

public class PictureSOAP {

    public static String SOAP_ACTION = "http://tempuri.org/IAyonixService/GetData";

    public static String getSOAPPayload(String encode){

        /*String encode = "";
        for (Character c : encodeInCharArray){
            encode += c.toString();
        }
        System.out.println(" --->charArray length = "+encodeInCharArray.length+" ---> encode string length = "+encode.length());*/

        if (encode != null){
            System.out.println("ENCODE LENGTH = "+encode.length());
        }



        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetData>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:value>data:image/jpeg;base64,"+encode+"</tem:value>\n" +
                "      </tem:GetData>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }





}




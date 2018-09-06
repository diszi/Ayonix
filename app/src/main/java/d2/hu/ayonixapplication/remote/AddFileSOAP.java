package d2.hu.ayonixapplication.remote;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import d2.hu.ayonixapplication.app.CustomerProperties;
import io.reactivex.ObservableEmitter;

public class AddFileSOAP<T extends String> extends AbstractSOAP<T> {


    private String encode;
    private String urlname;
    private String pictureName;


    public AddFileSOAP(String encode, String urlname, String pictureName){
        this.encode = encode;
        this.urlname = urlname;
        this.pictureName = pictureName;
        System.out.println(" AddFileSOAP =====>>>>> \n----> urlname = "+urlname+" --> pic name = "+pictureName);
        if (encode != null){
            System.out.println(" ENCODE != null");
        }
    }



    @Override
    protected void onSucces(InputStream inputStream, ObservableEmitter<T> emitter) throws IOException, SAXException, ParserConfigurationException {
       /* List<Attachment> attachmentList = EntityMapper.transformAttachmentList(inputStream);
        emitter.onNext((T)attachmentList);*/
     //  List<Attachment> attachmentList = EntityMapper.transformAttachmentList(inputStream);
       System.out.println("pictureName = "+pictureName);
       emitter.onNext((T)pictureName);
        emitter.onComplete();
    }

    @Override
    protected String getSOAPURL() {
       return CustomerProperties.SOAP_UPLOAD_IMAGE;
    }

    @Override
    protected String getSOAPPayload() {

        System.out.println("getSOAPPayload  ---->>>> size = "+encode.length());

        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:GetData>\n" +
                "         <tem:value>data:image/jpeg;base64,"+encode+"</tem:value>\n" +
                "      </tem:GetData>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";



    }
}

//data:image/jpeg,"+encode+"



package d2.hu.ayonixapplication.util;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import d2.hu.ayonixapplication.ui.model.Attachment;

public class EntityMapper {


    public static List<Attachment> mlist = new ArrayList<>();



    public static List<Attachment> transformAttachmentListSAX(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {

     //   writeinFile(inputStream);
// jo az xml, amit visszakapok , valoszinu hogy a parse miatt valtozik a megjelenites
        SAXParserFactory factory = SAXParserFactory.newInstance();
        List<Attachment> attachmentList = null;

        SAXParser saxParser =factory.newSAXParser();
        MyHandler myHandler = new MyHandler();
        saxParser.parse(inputStream,myHandler);
        attachmentList =myHandler.getAttachmentList();

        setAttachmentList(attachmentList);
        return attachmentList;

    }



//    private static void writeinFile(InputStream inputStream){
//        File file = new File("/storage/emulated/0/DCIM/results3.xml");
//        System.out.println(" FILE : "+file.getAbsolutePath());
//        try {
//            byte[] buff = new byte[4096];
//            int n;
//            OutputStream outputStream = new FileOutputStream(file);
//          //  OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file);
//            while ((n = inputStream.read(buff)) != -1){
//                outputStream.write(buff, 0, n);
//            }
//            outputStream.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    private static String getNodeValue(Element element, String tag) {

        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

    private  static boolean getNodeValueBoolean(Element element, String tag){
        return element.getElementsByTagName(tag).item(0).getTextContent().equals("1") ;
    }

    public static void setAttachmentList(List<Attachment> list){
       // System.out.println("setAttachmentList = "+list);
        mlist = list;
       // System.out.println(" Attachment List size = "+mlist.size());
//        for (int i=0;i< list.size();i++){
//            System.out.println(" ["+i+"] name="+list.get(i).getName()+", birthdate ="+list.get(i).getBirthdate()+", blacklist ="+list.get(i).getBlackList()+", score ="+list.get(i).getScore());
//        }
    }

    public static List<Attachment> getAttachmentList(){
        System.out.println(" getAttachmentList ----->  mList size = "+mlist.size());
        return mlist;
    }


}



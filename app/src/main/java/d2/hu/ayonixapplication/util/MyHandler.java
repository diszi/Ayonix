package d2.hu.ayonixapplication.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import d2.hu.ayonixapplication.ui.model.Attachment;

public class MyHandler extends DefaultHandler {

    private List<Attachment> attachmentList = null;
    private Attachment attachment = null;
//    private ArrayAyonixFaceType arrayAyonixFaceTypeObj = null;
//    List<ArrayAyonixFaceType> arrayAyonixFaceTypesList = null;

    public List<Attachment> getAttachmentList(){
        return attachmentList;
    }

    boolean bbirthdate = false;
    boolean bblacklist = false;
    boolean bdetails = false;
    boolean bimage = false;
    boolean bname = false;
    boolean bother = false;
    boolean bscore = false;
    boolean bResultAyonixFaceType = false;
    boolean resultFacesNotFound = false;


    public List<Attachment> geteAttachmentList(){
        return attachmentList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
     System.out.println("START -> qname = " + qName);

        setFields(false);

        if (qName.equalsIgnoreCase("a:resultFaces")){
            System.out.println("resultFaces => "+attributes.getLength());

        }

        if (qName.equalsIgnoreCase("a:ResultAyonixFaceType")) {
            //System.out.println("RESULTAYONIXFACETYPE -> "+attachment+" -> "+attachmentList);
            attachment = new Attachment();

            if (attachmentList == null) {
                attachmentList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("a:birthdate")) {

                bbirthdate = true;
//            System.out.println("bbirthdate = "+bbirthdate);
        } else if (qName.equalsIgnoreCase("a:blacklist")) {

            bblacklist = true;
      //      System.out.println("bblacklisst = "+bblacklist);
        } else if (qName.equalsIgnoreCase("a:details")) {
            bdetails = true;
   //         System.out.println("bdetails = "+bdetails);
        } else if (qName.equalsIgnoreCase("a:image")) {

            bimage = true;
   //         System.out.println("bimage = "+bimage);
        } else if (qName.equalsIgnoreCase("a:name")) {
            bname = true;

    //        System.out.println("bname = "+bname);
        } else if (qName.equalsIgnoreCase("a:other")) {

            bother = true;

    //        System.out.println("bother = "+bother);
        } else if (qName.equalsIgnoreCase("a:score")) {
            bscore = true;
      //      System.out.println("bscore = "+bscore);
        }

       // System.out.println(" BOOLEAN VALUES => bd = "+bbirthdate+" - bl = "+bblacklist+" - bd = "+bdetails+" - bi = "+bimage+" - bn = "+bname+" - bo = "+bother+" - bs = "+bscore);
    }


    private void setFields(boolean value){
        bblacklist = value;
        bbirthdate = value;
        bname = value;
        bdetails = value;
        bimage = value;
        bother = value;
        bscore = value;
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        if (qName.equalsIgnoreCase("a:resultFaces")){
//            //EnvironmentTool.facesNotFound();
            System.out.println(" end -> "+qName);
//            //attachmentList = null;
//        }
        if (qName.equalsIgnoreCase("a:ResultAyonixFaceType")){
           // System.out.println(" attachment GET name= "+attachment.getName());
           // if (attachmentList.size() < 2){
                attachmentList.add(attachment);
                System.out.println(" List size = "+attachmentList.size());
            //}

         //   System.out.println(" attachmentList size = "+attachmentList.size());
//            for (int k=0;k<attachmentList.size();k++){
//                System.out.println("["+k+"]="+attachmentList.get(k).getName());
//            }
            attachment = null;
        }

    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

           System.out.println("length => "+length+" -> characters -> "+new String(ch,start,length));



        if (bbirthdate){

            attachment.setBirthdate(new String(ch,start,length));
            bbirthdate = false;
        }

        if (bblacklist){


            attachment.setBlackList(Boolean.parseBoolean(new String(ch,start,length)));
            bblacklist = false;
        }
        if (bdetails){


            attachment.setDetails(new String(ch,start,length));
            bdetails = false;
        }

        if (bimage){

            //[System.out.println(" charact length - "+new String(ch,start,length).length()+" \n ch length = "+length);

            //thread
           // System.out.println("bimage => length = "+length+" -> start = "+start+" -> "+ch.length);
            attachment.setImage(new String(ch,start,length));

            bimage = false;
        }
        if(bname){


            attachment.setName( new String(ch,start,length));
            bname = false;
        }

        if (bother){


            attachment.setOther( new String(ch,start,length));
            bother = false;
        }


        if (bscore){

            attachment.setScore(new String(ch,start,length));
            bscore = false;
        }

    }



}

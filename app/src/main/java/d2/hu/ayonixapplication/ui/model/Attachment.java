package d2.hu.ayonixapplication.ui.model;

import android.graphics.Bitmap;

import java.io.Serializable;

import d2.hu.ayonixapplication.util.EnvironmentTool;

public class Attachment implements Serializable {

    public static String SERIALIZABLE_NAME = "Attachment_Serializable";

    private String name;
    private String details;
    private String birthdate;
    private String score;
    private String image;
    private boolean blackList;
    private String other;

    private String base64Code;
    private String errorMsg;

    private Bitmap bitmap;

    public Attachment() {
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("--------------------------------> setName = "+this.name);
    }

    public String getName() {
        //System.out.println("--------------------------------> getName = "+name);
        return name;
    }

    public void setDetails(String details) {
        this.details = details;
        System.out.println("--------------------------------> setDetails = "+this.details);
    }

    public String getDetails() {
        return details;
    }

    public void setBirthdate(String birthdate) {

        this.birthdate = birthdate;
        System.out.println("--------------------------------> setBD = "+this.birthdate);
    }

    public String getBirthdate() {
        return birthdate;

    }

    public void setImage(String image) {
        this.image = image;
       // EnvironmentTool.getBase64FromImageField(this.image);
        System.out.println("--------------------------------> setImage = ");
       // EnvironmentTool.getBase64FromImageField(this.image);
        setBase64Code(EnvironmentTool.getencodedImageBase64(this.image));
    }

    public String getImage() {
        return image;
    }

//    public void setBase64Code(StringBuffer base64Code){
//        System.out.println("setBase64 = "+base64Code.length()); //5231 - T.Z. - 88,6%
//        this.base64Code = base64Code;
//    }
//
//    public StringBuffer getBase64Code(){
//        return base64Code;
//    }

    public void setBase64Code(String base64Code){
        System.out.println("setBase64 = "+base64Code.length()); //887 - T.Z
       // System.out.println(" ----------> BASE 64 = "+base64Code);
//        setBitmap(EnvironmentTool.convertBase64ToImage(base64Code));
        this.base64Code = base64Code;

        setBitmap(EnvironmentTool.convertBase64ToImage(this.base64Code));
    }

    public void setBitmap(Bitmap bitmap){
        System.out.println(" setBitmap -> "+bitmap.getWidth()+" - "+bitmap.getHeight());
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }
    public String getBase64Code(){
        return base64Code;
    }


    public void setScore(String score) {
        this.score = score;
        System.out.println("--------------------------------> setScore = "+this.score);

    }

    public String getScore() {
        return score;
    }


    public void setBlackList(boolean blackList) {
        this.blackList = blackList;
        System.out.println("--------------------------------> setBlackList ="+blackList);
    }

    public boolean getBlackList() {
        return blackList;
    }

    public void setOther(String other) {
        this.other = other;
        System.out.println("--------------------------------> setOther = "+this.other);
    }

    public String getOther() {
        return other;
    }

    public void setErrorMsg(String msg){
        this.errorMsg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}



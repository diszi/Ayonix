package d2.hu.ayonixapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnvironmentTool  {

    public static String mCurrentPhotoPath;
    public static String imageInBase64;

    public static File  createImageFile() throws IOException {

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ UIConstans.PHOTO_SAVE_DIR);
        String timeStamp = EnvironmentTool.convertDate(new Date(),UIConstans.DATE_PATTERN_PHOTO);
        File image = new File(dir,"IMG_"+timeStamp+UIConstans.IMAGE_EXTENSION);
        mCurrentPhotoPath = image.getAbsolutePath();

        System.out.println(" dir = "+dir+" --> timeStamp = "+timeStamp+" --> mCurrentPhotoPath = "+mCurrentPhotoPath);
        return image;
    }

    public static String convertDate(Date date,String pattern){
        return new SimpleDateFormat(pattern).format(date);
    }

//    public static void setImageBase64(String base64code){
//        imageInBase64 = base64code;
//    }
//
//    public static String getImageInBase64(){
//        return imageInBase64;
//    }

    /*public static String encodeImage(String imagePath){
        String base64image = "" ;
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
                // Reading a Image file from file system
                byte imageData[] = new byte[(int) file.length()];
                imageInFile.read(imageData);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                base64image = Base64.getEncoder().encodeToString(imageData);
            }

            System.out.println("file = "+file+" -> imagePath = "+imagePath+" ---> imageData = "+imageData.length);

            } catch (FileNotFoundException e) {
                System.out.println("Image not found" + e);
            } catch (IOException ioe) {
                System.out.println("Exception while reading the Image " + ioe);
            }

        if(base64image != null){
            System.out.println(" BASE64IMAGE != null");
        }
            return base64image;

    }

*/
    public static String encodeFile(Uri fileUri, Context context){
        String encodeBase64 = "";
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(fileUri);
            byte[] inputData = getBytes(inputStream );
            encodeBase64 = Base64.encodeToString(inputData,Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodeBase64;

    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int buffSize = 1024;
        byte[] buffer = new byte[buffSize];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1){
            baos.write(buffer,0,len);
        }
        return baos.toByteArray();
    }

    public static String getencodedImageBase64(String imageField){
        String base64Image = imageField.split(",")[1];

        return base64Image;
    }


    //convert base64 to jpg
    public static Bitmap convertBase64ToImage(String resultBase64){
        Bitmap bitmap = null;
       // System.out.println("BASE 64 = "+resultBase64);
        System.out.println("convertBase64ToImage = "+resultBase64.length());


        byte[] decodeString = Base64.decode(resultBase64,Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);

        return bitmap;
    }



}

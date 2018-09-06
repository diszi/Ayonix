package d2.hu.ayonixapplication.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import d2.hu.ayonixapplication.BuildConfig;
import d2.hu.ayonixapplication.R;
import d2.hu.ayonixapplication.ui.component.NofaceDialog;
import d2.hu.ayonixapplication.util.EnvironmentTool;


public class MainActivity extends AppCompatActivity{

    private static int TAKE_PICTURE_REQUEST =1;
    private static int PICK_FILE_REQUEST_CODE = 0;

    public static Context mContext;

    public static Activity mActivity;

    public ImagePresenter presenter;

    @BindView(R.id.createPicbutton)
    Button createPic;


    @BindView(R.id.settingsButton)
    Button settingsButton;


    public File file;
    public Uri imageUri;
    public String pictureName = "hello1234", path="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.mContext = getApplicationContext();
        ButterKnife.bind(this);
        mActivity = this;

        presenter = new ImagePresenter(this);

        Log.d(" ------------> ","Main Activity");

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadSettingsPage();
            }
        });




    }


    @OnClick(R.id.createPicbutton)
    public void onClickCreatePictureBtn(){
        System.out.println("TAKE PICTURE");
        loadCamera();
    }

    private void loadSettingsPage(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    Uri photoUri;



    private void loadCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;


        try{
            photoFile = EnvironmentTool.createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (photoFile != null){
            System.out.println("photoFile != null");
            photoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".provider",photoFile);
            System.out.println("photoURI = "+photoUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
            startActivityForResult(intent,TAKE_PICTURE_REQUEST);
        }

      /*  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.takijo);
        System.out.println(" BITMAP SIZE = "+bitmap.getWidth()+" - "+bitmap.getHeight());
        bitmap.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStream);
        byte[] daniImageBytes = byteArrayOutputStream.toByteArray();
        String imageString =  Base64.encodeToString(daniImageBytes,Base64.DEFAULT);


        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.4),(int)(bitmap.getHeight()*0.4),true);
        System.out.println("RESIZED BITMAP = "+resizedBitmap.getWidth()+" -> "+resizedBitmap.getHeight());
        ByteArrayOutputStream byteArrayOutputStreamRESIZE = new ByteArrayOutputStream();
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStreamRESIZE);
        byte[] resizeImageBytes = byteArrayOutputStreamRESIZE.toByteArray();
        String resizeImage = Base64.encodeToString(resizeImageBytes,Base64.DEFAULT);
        System.out.println(" -> resize takijo image length = "+resizeImage.length());
        //EnvironmentTool.setImageBase64(imageString);

        System.out.println(" takijo image length = "+imageString.length());
        presenter.uploadFile(imageString);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        System.out.println("RequestCode = "+requestCode+" --> resultCode = "+resultCode+" --> data = "+data);

        if (requestCode == 1 && resultCode ==RESULT_OK ) {
            System.out.println("TAKE PICTURE + SAVE");
           /* android.app.FragmentManager fm = this.getFragmentManager();
            SavePictureDialog savePictureDialog = SavePictureDialog.newInstance(EnvironmentTool.mCurrentPhotoPath);
            savePictureDialog.show(fm,"SaveAndUploadPicture");*/
            try {
                resizeImageandUpload(EnvironmentTool.mCurrentPhotoPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //uploadImage(EnvironmentTool.mCurrentPhotoPath);


        }else {
            System.out.println(" requestCode != 1");
        }


    }

    private void resizeImageandUpload(String path) throws IOException {
        System.out.println("PATH = "+path);
        String encodedImage="";
        Bitmap bitmapResize = null;
        Uri imageUri = Uri.parse(path);
        File file = new File(imageUri.getPath());

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        System.out.println("RESIZE method \nBitmap 1 => w = "+bitmap.getWidth()+"; h = "+bitmap.getHeight()); //3264, 1836
        double w = bitmap.getWidth()*0.5;
        double h = bitmap.getHeight()*0.5;
        bitmapResize = Bitmap.createScaledBitmap(bitmap, (int)w,(int)h,false);
        System.out.println(" -> resized w = "+(int)w+" --> h = "+(int)h);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmapResize.compress(Bitmap.CompressFormat.JPEG,70,bytes);

        System.out.println("Bitmap 2 --> w = "+bitmapResize.getWidth()+" --> h = "+bitmapResize.getHeight());
        byte[] byteArrayImage = bytes.toByteArray();
        encodedImage = Base64.encodeToString(byteArrayImage,Base64.DEFAULT);
        System.out.println("ENCODED IMAGE LENGTH = "+encodedImage.length());
//        try {
//            FileOutputStream fo = new FileOutputStream(file);
//            fo.write(bytes.toByteArray());
//            fo.flush();
//            fo.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        presenter.uploadFile(encodedImage);


    }


  /*  public void uploadImage(String currentPhotoPath) {
      //  System.out.println("PHOTO PATH ===> " + currentPhotoPath);


        imageUri = Uri.parse(currentPhotoPath);
        file = new File(imageUri.getPath());
        String encodedImage="";
        path = currentPhotoPath;
       // System.out.println("ImageURI = "+imageUri+" --> file = "+file+" ---> path = "+path);
        System.out.println(" Uri imageUri == String mCurrentPhotoPath == File file");

        Bitmap bm = BitmapFactory.decodeFile(currentPhotoPath);
        System.out.println("Bitmap 1 => "+bm.getWidth()+" x "+bm.getHeight()); // 3264, 1836
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArrayImage = baos.toByteArray();
        encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

        System.out.println(" encodedImage length = "+encodedImage.length()+" --> Bitmap 2 w = "+bm.getWidth()+" --> h = "+bm.getHeight()); // 3264, 1836

        try {
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(baos.toByteArray());
            fo.flush();
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        presenter.uploadFile(encodedImage);




    }
*/

    public void showMessage(){
        System.out.println("FACE NOT FOUND");
//        message = findViewById(R.id.errorMessage);
//        message.setVisibility(View.VISIBLE);

        FragmentManager fm = getFragmentManager();
        NofaceDialog nofaceDialog = new NofaceDialog();
        nofaceDialog.show(fm,"AlertDialog");

    }


    public void loadResultActivity(){
        Intent intent = new Intent(this,ResultActivity.class);
        startActivity(intent);
    }




}

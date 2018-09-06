package d2.hu.ayonixapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

public class ConvertTask extends AsyncTask<String,Integer,Bitmap> {



    @Override
    protected Bitmap doInBackground(String... strings) {
        String base64Code = strings[0];
        byte[] decodeString = Base64.decode(base64Code,Base64.DEFAULT);
        Bitmap  bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);


    }
}

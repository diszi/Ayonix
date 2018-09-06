package d2.hu.ayonixapplication.ui;


import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;

import d2.hu.ayonixapplication.R;
import d2.hu.ayonixapplication.app.CustomerProperties;
import d2.hu.ayonixapplication.remote.PictureSOAP;
import d2.hu.ayonixapplication.ui.model.Attachment;
import d2.hu.ayonixapplication.util.EntityMapper;
import d2.hu.ayonixapplication.util.NetworkTool;
import d2.hu.ayonixapplication.util.UIThrowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ImagePresenter extends BasePresenter {

    private MainActivity view;
    private Observable<List<Attachment>> addImageObservable;
    private ResultActivity activity = new ResultActivity();
    public Disposable disposable;


    public ImagePresenter(MainActivity view){
        this.view = view;
    }




   public void uploadFile(String  encode){
       addImageObservable = createUploadFileObservable(encode);
       System.out.println(" -> add Image Observable = "+addImageObservable);
       disposable = addImageObservable
               .subscribe((attachmentList) -> { // onNext Consumer
                   Log.d("------------------>"," Get Data  ");

                   //if (attachmentList.size()==0){
//                   if (attachmentList != null){
                       view.loadResultActivity();
//                   }else
//                   {
//                       view.showMessage();
//                   }
               }, (throwable) -> { // onError Consumer
                   int errorMessageCode = R.string.error_general;
                   if (throwable instanceof UIThrowable){
                       UIThrowable uiThrowable = (UIThrowable) throwable;
                       errorMessageCode = uiThrowable.getMessageId();
                   }

               }, () -> { // onComplate Action

               });
   }

   public Observable<List<Attachment>> createUploadFileObservable(String encode){

       Observable<List<Attachment>> result = Observable.create(emitter -> {
           try {
//                Thread.sleep(2000);
               Log.d("------------------>"," Start Remote SOAP Call ");
               HttpURLConnection connection = null;
               InputStream inputStream = null;
               try {

                   connection = NetworkTool.createSOAPConnection(CustomerProperties.SOAP_UPLOAD_IMAGE,PictureSOAP.SOAP_ACTION,String.format(PictureSOAP.getSOAPPayload(encode)));
                   System.out.println(" connection = "+connection);
                   int responseCode = connection.getResponseCode();
                   System.out.println("=================> RESPONSE code = "+responseCode);
                   if (responseCode == 200) {
                       inputStream = connection.getInputStream();
                       List<Attachment> attachmentList  = EntityMapper.transformAttachmentListSAX(inputStream);

                       System.out.println(" --> list = "+attachmentList);
                       if (attachmentList != null){
                           emitter.onNext(attachmentList);
                       }else{
                           //System.out.println("VIEW => "+view);
                           view.showMessage();
                       }

//                       List<Attachment> attachmentList = EntityMapper.transformAttachmentList(inputStream);
//                       emitter.onNext(attachmentList);
                       emitter.onComplete();
                   }else if (responseCode == 500) {
                       emitter.onError(new UIThrowable(R.string.error_general));
                   } else {
                       emitter.onError(new UIThrowable(R.string.error_network));
                   }
               } finally {
                   if (connection != null) {
                       if (inputStream != null){
                           inputStream.close();
                       }
                       connection.disconnect();
                   }
               }
           } catch (Exception ex) {
               Log.e("", "---------->", ex);
               emitter.onError(new UIThrowable(R.string.error_unknown));
           }
       });
       return result.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
   }




    @Override
    public void disposeAll() {
        super.disposeAll();
    }
}

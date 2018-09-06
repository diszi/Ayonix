package d2.hu.ayonixapplication.ui;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import d2.hu.ayonixapplication.R;
import d2.hu.ayonixapplication.util.UIThrowable;
import io.reactivex.disposables.Disposable;

public class BasePresenter {

    private List<Disposable> disposableList = new ArrayList<>();

    public void disposeAll() {
        for (Disposable disposable: disposableList) {
            if (disposable != null && !disposable.isDisposed()) {
                Log.d("------------------>"," Dispose observer");
                disposable.dispose();
            }
        }

    }

    protected int getErrorMessage(Throwable throwable){
        int errorMessageCode = R.string.error_general;
        System.out.println(" THROWABLE ");

        if (throwable instanceof UIThrowable){
            UIThrowable uiThrowable = (UIThrowable) throwable;
            errorMessageCode = uiThrowable.getMessageId();
        }
        return errorMessageCode;
    }

    protected void addDisposableToList(Disposable disposable){
        disposableList.add(disposable);
    }

    public void onComplete(){

    }
}

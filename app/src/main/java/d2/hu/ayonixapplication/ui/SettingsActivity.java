package d2.hu.ayonixapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import d2.hu.ayonixapplication.R;

public class SettingsActivity extends AppCompatActivity{



    @BindView(R.id.serverIPvalue)
    EditText serverIpValue;
    @BindView(R.id.portValue)
    EditText portValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

    }
}

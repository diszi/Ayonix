package d2.hu.ayonixapplication.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import d2.hu.ayonixapplication.R;
import d2.hu.ayonixapplication.util.EntityMapper;

public class ResultActivity extends Activity {

    @BindView(R.id.actDetails_attachmentList)
    RecyclerView compAttachmentList;

    private ResultAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        ButterKnife.bind(this);
        Log.e("==========> ","Result activity");
        System.out.println("RESULT ==> list size = "+EntityMapper.getAttachmentList().size());

        this.setupRecyclerView();

    }


    private void setupRecyclerView() {
        Context context = getApplicationContext();
        System.out.println("\ncontext  = "+context+"\n");
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        this.adapter = new ResultAdapter(this);
        this.compAttachmentList.setLayoutManager(layoutManager);
        this.compAttachmentList.setAdapter(this.adapter);

        adapter.setAttachments(EntityMapper.getAttachmentList());
    }


}

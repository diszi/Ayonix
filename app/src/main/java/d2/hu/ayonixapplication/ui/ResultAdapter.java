package d2.hu.ayonixapplication.ui;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import d2.hu.ayonixapplication.R;
import d2.hu.ayonixapplication.ui.model.Attachment;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.AttachmentViewHolder>{


    private ResultActivity resultActivity;
    private List<Attachment> attachmentList = new ArrayList<>();
   // List<Attachment> newList = new ArrayList<>();

    public ResultAdapter(ResultActivity activity){
        this.resultActivity = activity;
        System.out.println("adapter -> "+activity);
    }

    public void setAttachments(List<Attachment> attachments){
       this.attachmentList.clear();
        this.attachmentList.addAll(attachments);

        //newList.add(this.attachmentList.get(1));
        System.out.println("setAttachments => list size = "+this.attachmentList.size());
        this.notifyDataSetChanged();
    }


    @Override
    public AttachmentViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_result_page_items,
                parent, false);
        return new ResultAdapter.AttachmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( AttachmentViewHolder holder, int position) {
        Attachment attachment = attachmentList.get(position);
        System.out.println(" attachment in position "+position+" : "+attachment.getName());
        holder.bind(attachment);

      //  holder.bind(newList.get(0));

    }

    @Override
    public int getItemCount() {

        if (attachmentList != null && attachmentList.size() >0){
            return attachmentList.size();
        }else {
            return 0;
        }
//        if (newList != null && newList.size() >0){
//            return newList.size();
//        }else {
//            return 0;
//        }
    }


    public class AttachmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.actDetails_name)
        TextView imageName;
        @BindView(R.id.actDetails_birthdate)
        TextView birthdate;
        @BindView(R.id.actDetails_score)
        TextView score;

        @BindView(R.id.actDetails_image)
        ImageView result_image;

        public AttachmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void bind(Attachment attachment){
            imageName.setText(attachment.getName());
            birthdate.setText(attachment.getBirthdate());
            score.setText(attachment.getScore());
          //  System.out.println(" - bind = "+attachment.getBase64Code().length()+" ---> first char = "+attachment.getBase64Code().charAt(0)+" \n--> bitmap="+EnvironmentTool.convertBase64ToImage(attachment.getBase64Code()));
           // result_image.setImageBitmap(EnvironmentTool.convertBase64ToImage(attachment.getBase64Code()));
            result_image.setImageBitmap(attachment.getBitmap());

        }
    }
}

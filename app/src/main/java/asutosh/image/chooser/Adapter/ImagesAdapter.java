package asutosh.image.chooser.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.ArrayList;
import asutosh.image.chooser.R;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.Viewholder> {

    private ArrayList<String> data;
    private Context context;
    private ItemClickCallback mCallback;

    public ImagesAdapter(ArrayList<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        // 1. Declare your Views here

        public ImageView image_thumbnail;


        public Viewholder(View itemView) {
            super(itemView);

            // 2. Define your Views here

            image_thumbnail = (ImageView)itemView.findViewById(R.id.image_thumbnail);

        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_row, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {

        Glide.with(context).load(new File(data.get(position))).centerCrop().into(holder.image_thumbnail);

        holder.image_thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallback.onPhotoSelected(data.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * interface with method that gives the position of te photo selected
     */
    public interface ItemClickCallback{
        public void onPhotoSelected(String image_path);
    }

    /**
     * Get the callback object
     */
    public void setCallBack(ItemClickCallback mCallback){
        this.mCallback = mCallback;
    }

}



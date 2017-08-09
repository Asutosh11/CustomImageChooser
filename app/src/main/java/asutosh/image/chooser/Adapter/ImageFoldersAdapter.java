package asutosh.image.chooser.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.ArrayList;
import asutosh.image.chooser.Model.ImageData;
import asutosh.image.chooser.R;

public class ImageFoldersAdapter extends RecyclerView.Adapter<ImageFoldersAdapter.Viewholder> {

    private ArrayList<ImageData> data;
    private ImageData model;
    private Context context;
    private ItemClickCallback mCallback;

    public ImageFoldersAdapter(ArrayList<ImageData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        // 1. Declare your Views here

        public ImageView folder_thumbnail;
        public TextView folder_name;


        public Viewholder(View itemView) {
            super(itemView);

            // 2. Define your Views here

            folder_thumbnail = (ImageView)itemView.findViewById(R.id.folder_thumbnail);
            folder_name = (TextView)itemView.findViewById(R.id.folder_name);

        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_folder_row, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {

        model = data.get(position);

        Glide.with(context).load(new File(model.getImagePathList().get(0))).centerCrop().into(holder.folder_thumbnail);
        holder.folder_name.setText(model.getimage_container_folder());

        holder.folder_thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallback.onFolderSelected(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * interface with method that gives the position of te Gallery Folder selected
     */
    public interface ItemClickCallback{
        public void onFolderSelected(int position);
    }

    /**
     * Get the callback object
     */
    public void setCallBack(ItemClickCallback mCallback){
        this.mCallback = mCallback;
    }

}



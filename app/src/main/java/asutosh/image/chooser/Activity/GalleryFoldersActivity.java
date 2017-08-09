package asutosh.image.chooser.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import asutosh.image.chooser.Adapter.ImageFoldersAdapter;
import asutosh.image.chooser.Adapter.ImagesAdapter;
import asutosh.image.chooser.Model.ImageData;
import asutosh.image.chooser.R;

public class GalleryFoldersActivity extends AppCompatActivity implements ImageFoldersAdapter.ItemClickCallback,
        ImagesAdapter.ItemClickCallback{

    private ImageFoldersAdapter mImageFoldersAdapter;
    private ImagesAdapter mImagesAdapter;
    private ArrayList<ImageData> folders_and_path_of_images = new ArrayList<ImageData>();
    private String[] projection;
    private Uri images;
    private Cursor cur;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private ImageData folder;
    private String bucketComparator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        queryImages();
        initViews();
    }


    public void initViews(){

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mImageFoldersAdapter = new ImageFoldersAdapter(folders_and_path_of_images, GalleryFoldersActivity.this);

        mImageFoldersAdapter.setCallBack(GalleryFoldersActivity.this);

        mRecyclerView.setAdapter(mImageFoldersAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Read all the image data to get
     * 1. Image IDs
     * 2. Image Folder names
     * 3. Image taken dates
     * 4. Full path of images
     */
    public void queryImages(){

        folder = new ImageData();

        projection = new String[] {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN
        };

        images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        cur = getContentResolver().query(images, projection, null, null, MediaStore.Images.Media.BUCKET_DISPLAY_NAME + " DESC");

        if (cur.moveToLast()) {

            String imagePath;
            String bucket;

            int bucketColumn = cur.getColumnIndex(
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

            int imageColoumn = cur.getColumnIndex(MediaStore.Images.Media.DATA);

            do {
                bucket = cur.getString(bucketColumn);
                imagePath = cur.getString(imageColoumn);

                if(bucketComparator.equals(bucket)){
                    folder.addToImagePathList(imagePath);
                }
                else{

                    if(!bucketComparator.equals(""))
                    folders_and_path_of_images.add(folder);

                    folder = new ImageData();
                    folder.setimage_container_folder(bucket);
                    folder.addToImagePathList(imagePath);
                    bucketComparator = bucket;

                }

            } while (cur.moveToPrevious());

        }
    }


    @Override
    public void onFolderSelected(int position) {
        /**
         * Show all the images on clicked folder
         */
        mImagesAdapter = new ImagesAdapter(folders_and_path_of_images.get(position).getImagePathList(), GalleryFoldersActivity.this);
        mImagesAdapter.setCallBack(GalleryFoldersActivity.this);
        mRecyclerView.setAdapter(mImagesAdapter);
    }


    /**
     * Returns the path of the photo selected from Gallery to MainActivity
     */
    @Override
    public void onPhotoSelected(String image_path) {

        Intent intent=new Intent();
        intent.putExtra("selected_gallery_image",image_path);
        setResult(2,intent);
        finish();

    }

}

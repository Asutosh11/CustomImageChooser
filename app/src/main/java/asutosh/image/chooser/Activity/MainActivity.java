package asutosh.image.chooser.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import asutosh.image.chooser.Fragment.ImageChooserDialog;
import asutosh.image.chooser.R;
import asutosh.image.chooser.Util.RuntimePermissions;

public class MainActivity extends AppCompatActivity{

    private RuntimePermissions mRuntimePermissions;
    private String image_path;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);

        /**
         * Run time permissions need to be granted by the user in order to use Camera and External storage
         */
        getPhotoWithPermissions();
    }


    public void getPhotoWithPermissions(){
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        mRuntimePermissions = new RuntimePermissions(permissions, MainActivity.this);

        /**
         * If Camera and External storage permissions have been granted, show option to open Camera or Gallery
         */
        if(mRuntimePermissions.hasPermissions() == true){
            ImageChooserDialog imageChooserDialogFragment = ImageChooserDialog.newInstance();
            imageChooserDialogFragment.show(getFragmentManager().beginTransaction(), null);
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * When we select a photo from the custom Gallery, we get the path here
         */
        if(requestCode==2) {
          if(data != null){
                if(data.getStringExtra("selected_gallery_image") != null){
                    image_path = data.getStringExtra("selected_gallery_image");
                    Toast.makeText(getApplicationContext(), "Selected Image Path: " + image_path, Toast.LENGTH_LONG).show();
                }
            }
          }


        /**
         * When we take a Photo from camera, we get the path here
         */
        if(requestCode==3) {
            if(data != null){
                if(data.getStringExtra("selected_camera_image") != null){
                    image_path = data.getStringExtra("selected_camera_image");
                    Toast.makeText(getApplicationContext(), "Clicked Image Path: " + image_path, Toast.LENGTH_LONG).show();
                }
            }
        }


        /**
         * From Camera or gallery, the image that we got, set it to the ImageView here
         */
        if(image_path != null){
            if(!image_path.equals("")){
                Glide.with(getApplicationContext())
                        .load("file://"+image_path)
                        .centerCrop()
                        .into(imageView);
            }
            else{
                Toast.makeText(getApplicationContext(), "Image path cannot be empty. Please try again.", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                /**
                 * Once the permissions are granted, it goes to this function, checks and opens a Dialog Fragment
                 * to open Camera or Gallery
                 */
                getPhotoWithPermissions();
            }
        }
    }

}

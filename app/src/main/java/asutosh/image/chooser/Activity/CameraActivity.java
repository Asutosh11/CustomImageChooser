package asutosh.image.chooser.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import asutosh.image.chooser.Fragment.CameraFragment;
import asutosh.image.chooser.R;

public class CameraActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the Toolbar, make the Activity full screen height
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.fragment_container);

        /**
         * Load the Camera Fragment
         */
        getFragmentManager().beginTransaction()
                .replace(R.id.container, CameraFragment.newInstance())
                .commit();
    }


    /**
     * Returns the path of the photo clicked from Camera to MainActivity
     */
    public void onPhotoClicked(String image_path) {

        Intent intent=new Intent();
        intent.putExtra("selected_camera_image",image_path);
        setResult(3,intent);
        finish();

    }

}

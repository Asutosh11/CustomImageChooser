package asutosh.image.chooser.Fragment;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import asutosh.image.chooser.Activity.CameraActivity;
import asutosh.image.chooser.Activity.GalleryFoldersActivity;
import asutosh.image.chooser.R;

public class ImageChooserDialog extends DialogFragment {

    private ImageView mCamera, mGallery;
    private View rootView;


    public static ImageChooserDialog newInstance() {
        ImageChooserDialog imageChooserDialogFragment = new ImageChooserDialog();
        return imageChooserDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initViews(inflater);
        registerListeners();

        return rootView;
    }


    public void initViews(LayoutInflater inflater){
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        rootView = inflater.inflate(R.layout.camera_or_gallery, null);

        mCamera = (ImageView)rootView.findViewById(R.id.camera);
        mGallery = (ImageView)rootView.findViewById(R.id.gallery);
    }


    public void registerListeners(){

        /**
         * When clicked on Gallery icon
         */
        mGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                Intent i = new Intent(getActivity(), GalleryFoldersActivity.class);
                getActivity().startActivityForResult(i, 2);

            }
        });


        /**
         * When clicked on Camera icon
         */
        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                Intent i = new Intent(getActivity(), CameraActivity.class);
                getActivity().startActivityForResult(i, 3);

            }
        });

    }

}

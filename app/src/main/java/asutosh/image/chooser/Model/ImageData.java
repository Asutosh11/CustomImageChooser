package asutosh.image.chooser.Model;

import java.util.ArrayList;


public class ImageData {

    private String image_container_folder;
    private ArrayList<String> image_path_list = new ArrayList<String>();

    public String getimage_container_folder() {
        return image_container_folder;
    }
    public void setimage_container_folder(String image_container_folder) {
        this.image_container_folder = image_container_folder;
    }

    public ArrayList<String> getImagePathList() {
        return image_path_list;
    }
    public void addToImagePathList(String path) {
        
        image_path_list.add(path);
        this.image_path_list = image_path_list;
    }

}
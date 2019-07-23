package com.example.ima.Model;

/**
 * Created by Ahyan on 7/23/2019.
 */

public class MachineImage {

    int Image_ID ;
    int Machine_ID;
    String Uri_Image;

    public MachineImage(int image_ID, int machine_ID, String uri_Image) {
        Image_ID = image_ID;
        Machine_ID = machine_ID;
        Uri_Image = uri_Image;
    }

    public MachineImage(){}

    public int getImage_ID() {
        return Image_ID;
    }

    public void setImage_ID(int image_ID) {
        Image_ID = image_ID;
    }

    public int getMachine_ID() {
        return Machine_ID;
    }

    public void setMachine_ID(int machine_ID) {
        Machine_ID = machine_ID;
    }

    public String getUri_Image() {
        return Uri_Image;
    }

    public void setUri_Image(String uri_Image) {
        Uri_Image = uri_Image;
    }




}

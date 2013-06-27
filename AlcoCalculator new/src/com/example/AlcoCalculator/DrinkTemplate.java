package com.example.AlcoCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: stuzenco
 * Date: 30/05/13
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public class DrinkTemplate {
    private String Id;
    private String Name;
    private float Volume;
    private float Percent;
    private String Image;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getVolume() {
        return Volume;
    }

    public void setVolume(float volume) {
        Volume = volume;
    }

    public float getPercent() {
        return Percent;
    }

    public void setPercent(float percent) {
        Percent = percent;
    }
}


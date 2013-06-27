package com.example.AlcoCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: Сергей
 * Date: 13.05.13
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public class Drink {
    private String Id;
    private String Name;
    private String Time;
    private float Volume;
    private float Percent;
    private boolean IsFromTemplate;
    private String Image;

    public Drink(DrinkTemplate template) {
        setId(template.getId());
        setImage(template.getImage());
        setPercent(template.getPercent());
        setVolume(template.getVolume());
        setName(template.getName());
        setIsFromTemplate(true);
    }
    public  Drink()
    {}

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isIsFromTemplate() {
        return IsFromTemplate;
    }

    public void setIsFromTemplate(boolean isFromTemplate) {
        IsFromTemplate = isFromTemplate;
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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
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

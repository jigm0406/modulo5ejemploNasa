package model;

/**
 * Created by Mario on 14/08/2016.
 */
public class ModelItemFavorites
{
    public int id;
    public  String imgphoto;
    public String camfull;
    public  String landdate;
    public String camname;
    public  String rovname;
    public ModelItemFavorites(String imgphoto,String camfull, String landdate,String camname,String rovname){
        this.imgphoto = imgphoto;
        this.camfull = camfull;
        this.landdate = landdate;
        this.camname = camname;
        this.rovname = rovname;
    }
}
package ClothesEcommerce.Backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class cloudinary {
    private static Cloudinary instance;

    private cloudinary(){}

    public static synchronized Cloudinary getInstance(){
        if(instance == null){
            instance = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dwqwjohey",
                    "api_key", "683674228195998",
                    "api_secret", "ne7O-rYNGQStGBrX3cWLoGQQ5o4",
                    "secure", true));
        }
        return instance;
    }
}

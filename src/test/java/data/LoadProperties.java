package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    public static Properties userData = loadProperties(System.getProperty("user.dir")+"/src/main/java/properties/userData.properties");
    public static Properties loadProperties(String path){
        Properties properties = new Properties();
        try {
            FileInputStream stream = new FileInputStream(path);
            properties.load(stream);
        } catch (FileNotFoundException e) {
            System.out.println("error message: "+e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("error message: "+e.getMessage());
            throw new RuntimeException(e);
        }
        return properties;
    }
}

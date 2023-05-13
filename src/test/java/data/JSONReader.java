package data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    String fName;
    String lName;
    String email;
    String password;

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public JSONReader(String filepath) throws IOException, ParseException {
        File srcFile = new File(filepath);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(srcFile));
        for (Object jsonObj: jsonArray) {
            JSONObject person = (JSONObject) jsonObj;
            fName = (String) person.get("fName");
            lName = (String) person.get("lName");
            email = (String) person.get("email");
            password = (String) person.get("password");
        }
    }
}

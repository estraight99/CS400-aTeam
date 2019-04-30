package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.scene.image.Image;

/**
 * 
 * @author Dung Viet Bui
 *
 */
public class TypeOfLocationList {
  final String pathToJSONFile = "."+File.separator+"database"+File.separator+"typeOfLocation.json";
  int size;
  ArrayList<String> name;
  ArrayList<Image> image;
  
  TypeOfLocationList()
  {
    Object obj = null;
    try {
      obj = new JSONParser().parse(new FileReader(pathToJSONFile));
    } catch (IOException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    JSONObject jo = (JSONObject) obj;
    JSONArray arr = (JSONArray) jo.get("data");
    this.size=0;
    name = new ArrayList<>();
    image = new ArrayList<>();
    for (Object i: arr)
    {
      this.size++;
      JSONObject type = (JSONObject) i;
      name.add((String) type.get("name"));
      //System.out.println("."+File.separator+"img"+File.separator+"big"+File.separator+(n-1)+".png");
      Image current = new Image(getClass().getResourceAsStream(".."+File.separator+"img"+File.separator+"big"+File.separator+(size-1)+".png"));
      image.add(current);
    }
  }
  public String getName(int id)
  {
    return name.get(id);
  }
  public Image getImage(int id)
  {
    return image.get(id);
  }
  public int getSize()
  {
    return size;
  }
}

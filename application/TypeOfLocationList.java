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
  static final String pathToJSONFile = "."+File.separator+"database"+File.separator+"typeOfLocation.json";
  
  private int size;
  private ArrayList<String> name;
  private ArrayList<Image> image;
  
  public TypeOfLocationList()
  {
    Object obj = null;
    try {
      obj = new JSONParser().parse(new FileReader(pathToJSONFile));
    } catch (IOException | ParseException e) {
      // do nothing
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

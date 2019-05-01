package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.scene.image.Image;

/**
 * This class contains all types of Location in the game
 * @author Dung Viet Bui
 *
 */
public class TypeOfLocationList {
  static final String pathToJSONFile = "."+File.separator+"database"+File.separator+"typeOfLocation.json";
  
  private int size; // this is the size of the list
  private List<String> name; // this list contains the name of the location
  private List<Image> image; // this list contains the large image of the location
  
  /**
   * This method initializes a TypeOfLocationList from the default JSON File
   */
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
  
  /**
   * @param id is the id number of the location
   * @return the name of the location
   */
  public String getName(int id)
  {
    return name.get(id);
  }
  
  /**
   * @param id the id number of the location
   * @return the large image of the location
   */
  public Image getImage(int id)
  {
    return image.get(id);
  }
  
  /**
   * @return the number of types of location in the JSON File
   */
  public int getSize()
  {
    return size;
  }
}

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TypeOfStationList {
  final String pathToJSONFile = "."+File.separator+"database"+File.separator+"typeOfLocation.json";
  int n;
  ArrayList<String> name;
  TypeOfStationList() throws FileNotFoundException, IOException, ParseException
  {
    Object obj = new JSONParser().parse(new FileReader(pathToJSONFile));
    JSONObject jo = (JSONObject) obj;
    JSONArray arr = (JSONArray) jo.get("data");
    this.n=0;
    name = new ArrayList<>();
    for (Object i: arr)
    {
      this.n++;
      JSONObject type = (JSONObject) i;
      name.add((String) type.get("name"));
    }
  }
  public String getName(int id)
  {
    return name.get(id);
  }
  public int getN()
  {
    return n;
  }
  
  public static void main(String[] args) throws FileNotFoundException, IOException, ParseException
  {
    TypeOfStationList list = new TypeOfStationList();
    System.out.println(list.getN());
  }
}

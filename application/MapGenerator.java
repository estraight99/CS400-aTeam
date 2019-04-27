package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MapGenerator {
  static final String smallPath = "."+File.separator+"database"+File.separator+"smallMap.json";
  static final String mediumPath = "."+File.separator+"database"+File.separator+"mediumMap.json";
  static final String bigPath = "."+File.separator+"database"+File.separator+"bigMap.json";
  public static void generateMap(String pathToJSONFile,int length,int width) throws FileNotFoundException
  {
    JSONObject jo = new JSONObject();
    jo.put("length", length);
    jo.put("width", width);
    
    JSONArray data = new JSONArray();
    for (int i=1; i<=length; i++)
      for (int j=1; j<=width; j++)
      {
        JSONObject location = (new Location(i,j,"","",0)).createJSONObject();
        data.add(location);
      }
    
    jo.put("data", data);
    
    PrintWriter pw = new PrintWriter(pathToJSONFile); 
    pw.write(jo.toJSONString()); 
      
    pw.flush(); 
    pw.close(); 
  }
  
  public static void main(String[] args) throws FileNotFoundException
  {
    generateMap(smallPath,10,10);
    generateMap(mediumPath,1000,1000);
    generateMap(bigPath,1000,2000);
    System.out.println("Done!");
  }
}

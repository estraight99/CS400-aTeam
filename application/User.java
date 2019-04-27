package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class User {
  private String name;
  private int money;
  private String pathToJSONFile;

  public User(String name, int money) {
    this.name = name;
    this.money = money;
  }

  public User(String pathToJSONFile) throws FileNotFoundException, IOException, ParseException {
    this.pathToJSONFile = pathToJSONFile;
    // based on https://www.geeksforgeeks.org/parse-json-java/
    Object obj = new JSONParser().parse(new FileReader(pathToJSONFile));
    JSONObject jo = (JSONObject) obj;
    this.name = (String) jo.get("name");
    this.money = ((Long) jo.get("money")).intValue();
  }
  

  public void pay(int x) {
    this.money -= x;
  }
  
  
  public void updateJSONFile() throws FileNotFoundException
  {
    JSONObject jo = new JSONObject();
    jo.put("name", this.name);
    jo.put("money", this.money);
    
    PrintWriter pw = new PrintWriter(this.pathToJSONFile); 
    pw.write(jo.toJSONString()); 
      
    pw.flush(); 
    pw.close(); 
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

}

package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * This class represents an User of this game
 * @author Dung Viet Bui
 *
 */
public class User {
  private String name; // the name of the user
  private int money; // the amount of money this user has
  private String pathToJSONFile; // the JSON File that contains the information of this User

  /**
   * This method initializes an User with the given name and the given amount of money
   * @param name is the username
   * @param money is the amount of money this user has
   */
  public User(String name, int money) {
    this.name = name;
    this.money = money;
  }

  /**
   * This method initializes an User from the information in the JSON File
   * @param pathToJSONFile is the path to the JSON File
   * @throws FileNotFoundException is never thrown if no file is missing
   * @throws IOException is never thrown if no file is missing
   * @throws ParseException is never thrown if no file is missing
   */
  public User(String pathToJSONFile) throws FileNotFoundException, IOException, ParseException {
    this.pathToJSONFile = pathToJSONFile;
    // based on https://www.geeksforgeeks.org/parse-json-java/
    Object obj = new JSONParser().parse(new FileReader(pathToJSONFile));
    JSONObject jo = (JSONObject) obj;
    this.name = (String) jo.get("name");
    this.money = ((Long) jo.get("money")).intValue();
  }
  

  /**
   * This method take away an amount of money from the User
   * @param x is the amount of money this User needs to pay
   */
  public void pay(int x) {
    this.money -= x;
  }
  
  
  /**
   * This method update the JSON File that stores the information of this User
   * @throws FileNotFoundException is never thrown if no file is missing
   */
  @SuppressWarnings("unchecked")
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

  /**
   * @return the name of the User
   */
  public String getName() {
    return name;
  }

  /**
   * This method changes the User name
   * @param name is the new name of the User
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the amount of money this User has
   */
  public int getMoney() {
    return money;
  }

  /**
   * This method sets the amount of money of this User
   * @param money is the new amount of money of this User
   */
  public void setMoney(int money) {
    this.money = money;
  }

}

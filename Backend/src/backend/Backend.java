/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author BALAJI
 */
public class Backend {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        // TODO code application logic here
        Scanner scan =new Scanner(System.in);
        System.out.println("1.Existing File 2. Create new File");
        int ch=scan.nextInt();
        File file;
        if(ch==1){
              file=new File("store.json");
        }
        else{
             System.out.println("Enter New File name with extension .json");
             String File =scan.next();
            file =new File(File+"\\store.json");
            file.createNewFile();
        }
        boolean bool=true;
        while(bool){
            System.out.println("1.Create 2.Read 3.Delete 4.Exit");
            ch=scan.nextInt();
            System.out.println("Key:");
            String key=scan.next();
            switch(ch){
                case 1:
                    JSONObject ob=new JSONObject();
                    Object obj = new JSONParser().parse(new FileReader("C:\\Users\\BALAJI\\Documents\\NetBeansProjects\\Backend\\balaji.json")); 
                    JSONObject jo = (JSONObject) obj; 
        JSONArray ja = new JSONArray();
        ja.add(jo);
        ob.put(key,ja);
        FileReader fr=new FileReader(file);
        JSONArray ja1=null;
        if(fr.read()==-1){
            ja1 = new JSONArray();
            ja1.add(ob);    
            FileWriter file1 = new FileWriter(file);
            file1.write(ja1.toJSONString());
            file1.close();
          System.out.println("Created Successfully");
        }
        else{
            Object obj1 = new JSONParser().parse(new FileReader(file));
            ja1 = (JSONArray) obj1;
            ja1.add(ob);
            FileWriter file1 = new FileWriter(file);
            file1.write(ja1.toJSONString());
            file1.close();
            System.out.println("Created Successfully");
        }break;
                case 2:
                    Object obj1 = new JSONParser().parse(new FileReader(file)); 
        JSONArray ja3 = (JSONArray) obj1; 
        Iterator itr3 = ja3.iterator(); 
        int flag1=0;
        while (itr3.hasNext())  
        { 
            Iterator<Map.Entry> itr1 = ((Map) itr3.next()).entrySet().iterator();   
            while (itr1.hasNext()) { 
                Map.Entry pair = itr1.next();
                if(pair.getKey().equals(key)){
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                    flag1=1;
                    break;
                }
            }
            if(flag1==1){
                break;
            }
        }
        if(flag1==0){
            System.out.println("Error");
        }break;
                case 3:
                    Object obj2 = new JSONParser().parse(new FileReader(file)); 
        JSONArray ja2 = (JSONArray) obj2; 
        Iterator itr2 = ja2.iterator(); 
        int i=0;int flag=0;
        while (itr2.hasNext())  
        { 
            Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator(); 
            while (itr1.hasNext()) { 
                Map.Entry pair = itr1.next();
                if(pair.getKey().equals(key)){
                    Object i1=ja2.remove(i);
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                break;
            }
            i++;
        }
        if(flag==0){
            System.out.println("Element not present");
        }
        else{
            FileWriter file1 = new FileWriter(file);
            file1.write(ja2.toJSONString());
            file1.close();
            System.out.println("Deleted Successfully");
        }break;
                case 4:
                    bool=false;
                    break;
                default:
                    System.out.println("Enter valid choice");
            }
        }
    }
}

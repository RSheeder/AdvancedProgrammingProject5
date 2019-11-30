import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TestDB 
{
	/*static String instrumentType = "guitar";
	static String instrumentBrand = "gibson"; 
	static String warehouseLocation = "Pensacola Florida"; 
	static int maxCostAmount = 1000;*/
	
   public static void main(String[] args, String instrumentType, String instrumentBrand, String warehouseLocation, int maxCostAmount) throws Exception
   {   
      if (args.length == 0)
      {   
         System.out.println(
               "Usage: java -classpath driver_class_path" 
               + File.pathSeparator 
               + ". TestDB database.properties");
         //return;
      }
      else 
		{
		   System.out.println("args[0] = " + args[0]);
         SimpleDataSource.init(args[0]);
		}
      
      SimpleDataSource.init("./src/database.properties");
      Connection conn = SimpleDataSource.getConnection();
      conn.setAutoCommit(false);
      Statement stat = conn.createStatement();
      //Statement stat2 = conn.createStatement();
      
 	   try {  
		  stat.execute("DROP TABLE IF EXISTS Instruments");
		  stat.execute("DROP TABLE IF EXISTS Locations");
		  stat.execute("DROP TABLE IF EXISTS Inventory");
      }
	   catch (Exception e)
		{ }//System.out.println("drop failed"); }      

      try
      {
    	  //ResultSet result =  TestDB.createInstruments(stat);
    	  stat.execute("CREATE TABLE Instruments (instName CHAR(12),instNumber INTEGER,cost DOUBLE,descrip CHAR(20))");
          stat.execute("INSERT INTO Instruments VALUES ('guitar',1,100.0,'yamaha')");
          stat.execute("INSERT INTO Instruments VALUES ('guitar',2,500.0,'gibson')");
          stat.execute("INSERT INTO Instruments VALUES ('bass',3,250.0,'fender')");
          stat.execute("INSERT INTO Instruments VALUES ('keyboard',4,600.0,'roland')");
          stat.execute("INSERT INTO Instruments VALUES ('keyboard',5,500.0,'alesis')");
          
          stat.execute("CREATE TABLE Locations (locName CHAR(12),locNumber INTEGER,address CHAR(50))");
          stat.execute("INSERT INTO Locations VALUES ('PNS',1,'Pensacola Florida')");
          stat.execute("INSERT INTO Locations VALUES ('CLT',2,'Charlotte North Carolina')");
          stat.execute("INSERT INTO Locations VALUES ('DFW',3,'Dallas Fort Worth Texas')");
          
          stat.execute("CREATE TABLE Inventory (iNumber INTEGER,lNumber INTEGER,quantity INTEGER)");
          stat.execute("INSERT INTO Inventory VALUES (1,1,15)");
          stat.execute("INSERT INTO Inventory VALUES (1,2,27)");
          stat.execute("INSERT INTO Inventory VALUES (1,3,20)");
          stat.execute("INSERT INTO Inventory VALUES (2,1,10)");
          stat.execute("INSERT INTO Inventory VALUES (2,2,10)");
          stat.execute("INSERT INTO Inventory VALUES (2,3,35)");
          stat.execute("INSERT INTO Inventory VALUES (3,1,45)");
          stat.execute("INSERT INTO Inventory VALUES (3,2,10)");
          stat.execute("INSERT INTO Inventory VALUES (3,3,17)");
          stat.execute("INSERT INTO Inventory VALUES (4,1,28)");
          stat.execute("INSERT INTO Inventory VALUES (4,2,10)");
          stat.execute("INSERT INTO Inventory VALUES (4,3,16)");    
          
          //ResultSet result = stat.executeQuery("SELECT * FROM Instruments INNER JOIN Locations ON Instruments.instNumber = Locations.locNumber WHERE Instruments.instName = 'guitar' AND Instruments.descrip='yamaha' AND Instruments.cost =100 AND Locations.address='Pensacola Florida'");
          ResultSet result = stat.executeQuery("SELECT * FROM Instruments "
          		+ "								INNER JOIN Inventory ON Instruments.instNumber = Inventory.iNumber "
          		+ "								INNER JOIN Locations ON Locations.locNumber = Inventory.lNumber "
          		+ "								WHERE Instruments.instName = '"+instrumentType+"' "
          		+ "								AND Instruments.cost < "+maxCostAmount+ ""
          		+ "								AND Instruments.descrip = '"+ instrumentBrand + "' "
          		+ "								AND Locations.address = '"+warehouseLocation+"'");
    	  conn.commit();
          
			System.out.println("after inserts");
			ResultSetMetaData rsm = result.getMetaData();
			int cols = rsm.getColumnCount();
			StringBuilder resultString = new StringBuilder();
			  while(result.next())
			  {
			    for(int i = 1; i <= cols; i++)
               resultString.append(result.getString(i)+" ");
			   System.out.println(resultString);
			   System.out.println("");      
			  }
			  JOptionPane.showMessageDialog(null, resultString, "Query Results:", JOptionPane.INFORMATION_MESSAGE);
			  
			  
			try { 
				stat.execute("DROP TABLE Instruments"); 
				stat.execute("DROP TABLE Locations"); 
				stat.execute("DROP TABLE Inventory");
				stat.close();
				//stat2.close();
         }
			catch (Exception e)
			{ }//System.out.println("drop failed"); }
			
			
		}
      finally
      {
    	  conn.commit();
    	  conn.close();
		  System.out.println("dropped Table Instruments, closed connection and ending program");  
      }
   }
   
   public static ResultSet createInstruments(Statement stat) throws Exception
   {
	   	  String instrumentType = "guitar";
	   	  String guitarBrand = "gibson";
	   	  double instrumentCost = 1000;
	   	  String warehouseLocation = "Pensacola Florida";
	   	  
          stat.execute("CREATE TABLE Instruments (instName CHAR(12),instNumber INTEGER,cost DOUBLE,descrip CHAR(20))");
          stat.execute("INSERT INTO Instruments VALUES ('guitar',1,100.0,'yamaha')");
          stat.execute("INSERT INTO Instruments VALUES ('guitar',2,500.0,'gibson')");
          stat.execute("INSERT INTO Instruments VALUES ('bass',3,250.0,'fender')");
          stat.execute("INSERT INTO Instruments VALUES ('keyboard',4,600.0,'roland')");
          stat.execute("INSERT INTO Instruments VALUES ('keyboard',5,500.0,'alesis')");
          
          stat.execute("CREATE TABLE Locations (locName CHAR(12),locNumber INTEGER,address CHAR(50))");
          stat.execute("INSERT INTO Locations VALUES ('PNS',1,'Pensacola Florida')");
          stat.execute("INSERT INTO Locations VALUES ('CLT',2,'Charlotte North Carolina')");
          stat.execute("INSERT INTO Locations VALUES ('DFW',3,'Dallas Fort Worth Texas')");
          
          stat.execute("CREATE TABLE Inventory (iNumber INTEGER,lNumber INTEGER,quantity INTEGER)");
          stat.execute("INSERT INTO Inventory VALUES (1,1,15)");
          stat.execute("INSERT INTO Inventory VALUES (1,2,27)");
          stat.execute("INSERT INTO Inventory VALUES (1,3,20)");
          stat.execute("INSERT INTO Inventory VALUES (2,1,10)");
          stat.execute("INSERT INTO Inventory VALUES (2,2,10)");
          stat.execute("INSERT INTO Inventory VALUES (2,3,35)");
          stat.execute("INSERT INTO Inventory VALUES (3,1,45)");
          stat.execute("INSERT INTO Inventory VALUES (3,2,10)");
          stat.execute("INSERT INTO Inventory VALUES (3,3,17)");
          stat.execute("INSERT INTO Inventory VALUES (4,1,28)");
          stat.execute("INSERT INTO Inventory VALUES (4,2,10)");
          stat.execute("INSERT INTO Inventory VALUES (4,3,16)");    
          
          //ResultSet result = stat.executeQuery("SELECT * FROM Instruments INNER JOIN Locations ON Instruments.instNumber = Locations.locNumber WHERE Instruments.instName = 'guitar' AND Instruments.descrip='yamaha' AND Instruments.cost =100 AND Locations.address='Pensacola Florida'");
          ResultSet result = stat.executeQuery("SELECT * FROM Instruments "
          		+ "								INNER JOIN Inventory ON Instruments.instNumber = Inventory.iNumber "
          		+ "								INNER JOIN Locations ON Locations.locNumber = Inventory.lNumber "
          		+ "								WHERE Instruments.instName = '"+instrumentType+"' "
          		+ "								AND Instruments.cost < "+instrumentCost+ ""
          		+ "								AND Instruments.descrip = '"+ guitarBrand + "' "
          		+ "								AND Locations.address = '"+warehouseLocation+"'");
          return result;
   }
   
   public static ResultSet createLocations(Statement stat2) throws Exception
   {
	   	  stat2.execute("CREATE TABLE Locations (locName CHAR(12),locNumber INTEGER,address CHAR(50))");
          stat2.execute("INSERT INTO Locations VALUES ('PNS',1,'Pensacola Florida')");
          stat2.execute("INSERT INTO Locations VALUES ('CLT',2,'Charlotte North Carolina')");
          stat2.execute("INSERT INTO Locations VALUES ('DFW',3,'Dallas Fort Worth Texas')");
          ResultSet result = stat2.executeQuery("SELECT * FROM Locations");
          return result;
   }
   
   public ResultSet createInventory(Statement stat) throws Exception
   {
          stat.execute("CREATE TABLE Inventory (iNumber INTEGER,lNumber INTEGER,quantity INTEGER)");
          stat.execute("INSERT INTO Inventory VALUES (1,1,15)");
          stat.execute("INSERT INTO Inventory VALUES (1,2,27)");
          stat.execute("INSERT INTO Inventory VALUES (1,3,20)");
          stat.execute("INSERT INTO Inventory VALUES (2,1,10)");
          stat.execute("INSERT INTO Inventory VALUES (2,2,10)");
          stat.execute("INSERT INTO Inventory VALUES (2,3,35)");
          stat.execute("INSERT INTO Inventory VALUES (3,1,45)");
          stat.execute("INSERT INTO Inventory VALUES (3,2,10)");
          stat.execute("INSERT INTO Inventory VALUES (3,3,17)");
          stat.execute("INSERT INTO Inventory VALUES (4,1,28)");
          stat.execute("INSERT INTO Inventory VALUES (4,2,10)");
          stat.execute("INSERT INTO Inventory VALUES (4,3,16)");        
          ResultSet result = stat.executeQuery("SELECT * FROM Inventory");
          return result;
   }
}

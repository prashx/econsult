package try1;
import java.io.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.sql.*;
import java.util.ArrayList;


@Path("/ec")
public class hell {
	  Connection conn=null;
      Statement stmt=null;
      String sql="";
      ResultSet rs=null;

@Path("/DList")
@GET
public String ret()
{
	int count=0,x=0;
	String f="",ch,doc="jdbc:ucanaccess://E:\\econsult\\doc\\Data.mdb",def="";
	String ss[]=new String[2];
ss[0]="";
ss[1]="";
//ArrayList a=new ArrayList();
	
		
		 try {
			
				
			  Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	          conn=DriverManager.getConnection(doc);
	          stmt=conn.createStatement();
	          sql="select * from table1";
	          rs=stmt.executeQuery(sql);

	        
	      
	      
				while(rs.next()){
		             
				def=def+(rs.getString("ID")) + "\n" + (rs.getString("DEGREES")) + "\n" + (rs.getString("SPL")) + "\n";					
	
			       count++;
			
				}

				
			       
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		 
		
     return Integer.toString(count) + "\n" + def;
}



@Path("/Plogin")
@POST
@Consumes("text/plain")

public String plogin(InputStream incomingData) {
	int count=0,x=0;
	String f="",ch,pat="jdbc:ucanaccess://E:\\econsult\\patient\\Data.mdb",doc="jdbc:ucanaccess://E:\\econsult\\doc\\Data.mdb",def="";
	String ss[]=new String[2];
ss[0]="";
ss[1]="";
	
		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
		String line = null;
		 try {
			 ch=in.readLine();
			 if(ch.equals("1"))
				 def=pat;
			 else
				 def=doc;
				while ((line = in.readLine()) != null) 
					ss[x++]=line;
				
			  Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	          conn=DriverManager.getConnection(def);
	          stmt=conn.createStatement();
	          sql="select * from table1";
	          rs=stmt.executeQuery(sql);

	        
	      
	      
				while(rs.next()){
		             
				if(ss[0].equals(rs.getString("ID")) && ss[1].equals(rs.getString("PASS")))
					count=1;
		
			       
			
				}

				
			       
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		 
		if(count==1)
		return "yes"; 
		else
			return "no " ;
}

@Path("/Pregister")
@POST
@Consumes("text/plain")
public String  preg(InputStream incomingData) {
	int count=0,x=0;
	String f="";
	String ss[]=new String[3];
ss[0]="";
ss[1]="";
ss[2]="";
		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
		String line = "";
		 try {
				ss[0]=in.readLine();
				ss[1]=in.readLine();
				ss[2]=in.readLine();
				
			  Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		       Connection conn=DriverManager.getConnection("jdbc:ucanaccess://E:\\econsult\\patient\\Data.mdb");
	          
	        
	          String q = "INSERT INTO Table1 ([ID], [PASS], [NO]) VALUES (?, ?, ?)";
	          PreparedStatement st = conn.prepareStatement (q);
	          st.setString(1, ss[0]);
	          st.setString(2, ss[1]);
	          st.setString(3, ss[2]);
	          st.executeUpdate();
	      

		} catch (Exception e) {
	
			e.printStackTrace();
		}
		 //return Response.status(200).entity("posted").build();
		return "posted";
		 
}


@Path("/Dregister")
@POST
@Consumes("text/plain")
public String  dreg(InputStream incomingData) {
	int count=0,x=0;
	String f="";
	String ss[]=new String[5];
ss[0]="";
ss[1]="";
ss[2]="";
ss[3]="";
ss[4]="";
		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
		String line = "";
		 try {
				while ((line = in.readLine()) != null) 
					ss[x++]=line;
				
				
			  Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		       Connection conn=DriverManager.getConnection("jdbc:ucanaccess://E:\\econsult\\doc\\Data.mdb");
	          
	        
	          String q = "INSERT INTO Table1 ([ID], [PASS], [NO],[DEGREES],[SPL]) VALUES (?, ?, ?, ? , ?)";
	          PreparedStatement st = conn.prepareStatement (q);
	          st.setString(1, ss[0]);
	          st.setString(2, ss[1]);
	          st.setString(3, ss[2]);
	          st.setString(4, ss[3]);
	          st.setString(5, ss[4]);
	          st.executeUpdate();
	      

		} catch (Exception e) {
	
			e.printStackTrace();
		}
		 //return Response.status(200).entity("posted").build();
		return "posted";
		 
}






@Path("/getmessage")
@POST
@Consumes("text/plain")
public String  post3(InputStream incomingData) {
	int count=0,x=0;
	String ff="";
	String s2="",ss="";
	
		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
		String line = "";
		try{
			
		ss = in.readLine();
			
		s2 = in.readLine();
		
		 
				
			  Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	          conn=DriverManager.getConnection("jdbc:ucanaccess://E:\\econsult\\message\\Data.mdb");
	      
	          stmt=conn.createStatement();
	          sql="select * from Table1";
	          rs=stmt.executeQuery(sql);
				while(rs.next()){
		             
				if(s2.equals(rs.getString("D_ID")) && ss.equals(rs.getString("P_ID")))
				{	ff=ff+(rs.getString("message"))+ "\n";
				if(rs.getString("P").equals("1"))
					ff=ff+"1" + "\n";
				else
					ff=ff+"2" + "\n";
				}  
			
				}
	      

		} catch (Exception e) {
	
			e.printStackTrace();
		}
		 return ff;
		 
}

@Path("/putmessage")
@POST
@Consumes("text/plain")
public String  post4(InputStream incomingData) {
	int count=0,x=0;
	String ff="";

	String ss[]=new String[10];
		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
		String line = null;
		 try {
			 while ((line = in.readLine()) != null) 
					ss[x++]=line;
				
			  Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			  conn=DriverManager.getConnection("jdbc:ucanaccess://E:\\econsult\\message\\Data.mdb");
	          String q = "INSERT INTO Table1 ([D_ID], [P_ID], [P],[D],[MESSAGE]) VALUES (?, ?, ?, ? , ?)";
	          PreparedStatement st = conn.prepareStatement (q);
	          st.setString(1, ss[0]);
	          st.setString(2, ss[1]);
	          st.setString(3, ss[2]);
	          st.setString(4, ss[3]);
	          st.setString(5, ss[4]);
	          st.executeUpdate();
	        
	
	      
				

		} catch (Exception e) {
	
			e.printStackTrace();
		}
		 return "";
		 
}

}

package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentCRUD 
{
	public static Connection con=null;
	public static Statement s=null;
	public static PreparedStatement ps=null;
	public static ResultSet rs=null;
	
	
	public static final String classname="com.mysql.cj.jdbc.Driver";
	public static final String url="jdbc:mysql://localhost:3306/mydb";
	public static final String username="root";            
	public static final String password="admin";
	
	 
   public void creating() 
   {
	       
				try 
				{
					Class.forName(classname);
					con=DriverManager.getConnection(url,username,password);
				    
					s=con.createStatement();
					
					s.executeUpdate("create table student(sno int primary key  auto_increment,"
							+ "sname varchar(40) ,sub varchar(40),score int )");
						              
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				} 
				catch (SQLException e) 
				{
				    e.printStackTrace();
				}
				finally
				{
			        try 
			        {
			        	if(s!=null)
			        	{
			        		s.close();
			        	}
			        	if(con!=null)
			        	{
			        		con.close();
			        	}
			        	
			        }
				    catch (SQLException e) 
			        {
					  e.printStackTrace();
					}
				}
   }
   
   
   public void inserting(String name,String subject,int score) 
  	{
  		   try 
            {
				Class.forName(classname);
				con=DriverManager.getConnection(url,username,password);
	  			
	  			ps=con.prepareStatement("insert into student(sname,sub,score) values (?,?,?)");
	  			ps.setString(1,name);
	  			ps.setString(2,subject);
	  			ps.setInt(3, score);
	  			
	  			ps.executeUpdate();
			} 
            catch (ClassNotFoundException e) 
            {
				e.printStackTrace();
			} 
            catch (SQLException e) 
            {
				e.printStackTrace();
			}
  		   finally
			{
		        try 
		        {
		        	if(ps!=null)
		        	{
		        		ps.close();
		        	}
		        	if(con!=null)
		        	{
		        		con.close();
		        	}
		        	
		        }
			    catch (SQLException e) 
		        {
				  e.printStackTrace();
				}
			}
   }
   
   
   public void updating(int no,String subject,int score)
   {
	   try 
	   {
		Class.forName(classname);
		con=DriverManager.getConnection(url,username,password);
		ps=con.prepareStatement("update student set sub=? ,score=?  where sno=?");
	    ps.setString(1,subject );
		ps.setInt(2, score);
	    ps.setInt(3,no );
	    ps.executeUpdate();
	   } 
	   catch (ClassNotFoundException e) 
	   {
	     e.printStackTrace();
	   } 
	   catch (SQLException e) 
	   {
		e.printStackTrace();
	   }
	   finally
	   {
           try 
	       {
	    	   if(ps!=null)
	    	   {
	    		   ps.close();  
	    	   }
	    	   if(con!=null)
	    	   {
	    		  con.close();
	    	   }
	       }
		  catch (SQLException e) 
	       {
	        e.printStackTrace();
	       }
	   }
	   
   }
   
   
   public void delete(int no)
   {
	   try 
	   {
		Class.forName(classname);
		con=DriverManager.getConnection(url,username,password);
		ps=con.prepareStatement("delete from student where sno=?");
		ps.setInt(1, no);
		ps.executeUpdate();
      } 
	  catch (ClassNotFoundException e) 
	  {
		e.printStackTrace();
	  } 
	  catch (SQLException e) 
	  {
		e.printStackTrace();
	  }
	  finally
	  {
	     try 
	     {
		  if(ps!=null)
		  {
			  ps.close(); 
		  }
		  if(con!=null)
		  {
			  con.close();
		  }
         }
	     catch (SQLException e) 
	     {
		   e.printStackTrace();
	     }
	  }
  }
   

public void read() 
{
	   List<Map<String, Object>> resultList = new ArrayList<>();

	   try 
	   {
		Class.forName(classname);
		con=DriverManager.getConnection(url,username,password);
		String qry="select sno,sname,sub,score from student";
		ps=con.prepareStatement(qry);
		rs=ps.executeQuery(qry);
		
                 while (rs.next()) {
                 Map<String, Object> row = new HashMap<>();
                 row.put("sno", rs.getInt("sno"));
                 row.put("sname", rs.getString("sname"));
                 row.put("sub", rs.getString("sub"));
                 row.put("score", rs.getInt("score"));
                 resultList.add(row);
             }
         }
	    catch (ClassNotFoundException e) 
		{
		 e.printStackTrace();
		} 
        catch (SQLException e) 
	    {
         e.printStackTrace();
        }
	    finally
		  {
		     try
		     {
		    	 if(rs!=null)
		    	 {
		    		 rs.close(); 
		    	 }
		     	 if(s!=null)
		    	 {
		    		 s.close(); 
		    	 }
		    	 if(con!=null)
		    	 {
		    		 con.close();
		    	 }
		      }
		     catch (SQLException e) 
		     {
			   e.printStackTrace();
		     }
          }

     // Print list of HashMaps with column names
     for (Map<String, Object> row : resultList) {
         System.out.println("sno: " + row.get("sno") + ", sname: " + row.get("sname") + ", sub: " + row.get("sub")+ ", score: " + row.get("score"));
     }
 }
}


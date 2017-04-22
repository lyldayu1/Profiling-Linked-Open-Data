package com.mvc.resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Con_mysql {
	public Connection conn=null;
	public Statement stmt_sql=null;
	public ResultSet re_sql=null;
	public ResultSet re_sql1=null;
	public Con_mysql(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.print("Successfully loading Mysql Driver!");
			}catch(Exception e)
			{
				System.out.print("Error loading Mysql Driver!");
				e.printStackTrace();
			}
		try{
		 conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/LOD1","root","123456");
		}catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	}
	public void insert(String predicate,String object)
	{
			try{
				stmt_sql=conn.createStatement();			
				re_sql=stmt_sql.executeQuery("SELECT ID FROM predicates_list WHERE predicate_name='"+predicate+"'");				
				if(re_sql.first())
				{			
					
					
					re_sql1=stmt_sql.executeQuery("SELECT ID FROM predicates_list WHERE predicate_name='"+predicate+"'");
					re_sql1.first();
					int index=re_sql1.getInt(1);
					stmt_sql.execute("INSERT INTO predicate_"+index+"(predicate_name) values('"+object+"')"); //在关联表中，插入一条
					
				}
				else
				{				
					stmt_sql.execute("INSERT INTO predicates_list(predicate_name) values('"+predicate+"')");   //在主表插入一条，创建一个关联表
					re_sql1=stmt_sql.executeQuery("SELECT ID FROM predicates_list WHERE predicate_name='"+predicate+"'");
					re_sql1.first();
					int index=re_sql1.getInt(1);
					stmt_sql.executeUpdate("CREATE TABLE predicate_"+index+"(predicate_name VARCHAR(255) NOT NULL);");   //创建一个关联表
					
					stmt_sql.execute("INSERT INTO predicate_"+index+"(predicate_name) values('"+object+"')");  //在关联表中，插入一条；
					
					
				}
			   }catch (SQLException ex){
					System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
			
			
	}
	public static void main(String[] args)
	{
		Con_mysql a=new Con_mysql();
	}
	
}

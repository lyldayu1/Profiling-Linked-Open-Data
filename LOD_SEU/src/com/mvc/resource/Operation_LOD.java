package com.mvc.resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;
public class Operation_LOD{
	
	public static void operator() throws IOException
	{
//		int dataProperty_court=0;
//		ArrayList<String> list_dataProperty=new ArrayList<String>();    //all the dataProperty's name
//		HashMap<String,ArrayList> map= new HashMap<String,ArrayList>();  //store each dataproperty's values
//	    FileWriter file_out=new FileWriter("/Applications/graduation_project/dataset/dbpedia_literal_all.txt");  //write all values of literal into the file
		File f= new File("/Applications/graduation_project/dataset/literal/xaa");           //read the rdf file
		InputStream in = new FileInputStream(f);
		Model  model= ModelFactory.createDefaultModel();
		model.read(in,null,"TTL");
		StmtIterator iter=model.listStatements();
		Statement stmt;
		Con_mysql contt1=new Con_mysql();
		System.out.println("start");
		while(iter.hasNext()) //store all predicate into list_dataProperty, and store each predacate's value into arraylist1 by hashmap1
		{
			 stmt=iter.nextStatement();
			RDFNode object=stmt.getObject();
			Property predicate = stmt.getPredicate();
			if(object instanceof Resource);
			else
			{		
				if((object.toString().indexOf("XMLSchema#"))!=-1)
				{
				contt1.insert(predicate.toString(), object.toString());
				if((object.toString().indexOf("XMLSchema#string"))!=-1)
				{
					System.out.println(predicate.toString());
					break;
				}
				}
				else;
//				if(list_dataProperty.contains(predicate.toString()))
//				{
//					map.get(predicate.toString()).add(object.toString());
//				}
//				else
//				{
//					map.put(predicate.toString(),new ArrayList<String>());
//					map.get(predicate.toString()).add(object.toString());
//					list_dataProperty.add(predicate.toString());
//				}
//				//System.out.println(predicate.toString());
//				//System.out.println(object.toString());
			}
		}
//		System.out.println("begin"); //write in file
//		Iterator it1=list_dataProperty.iterator();   //read all the literal and dataProperty from the arraylist into file
//		while(it1.hasNext())     
//		{
//			String dataProperty_name= (String)it1.next();
////			System.out.println(dataProperty_name);
////			System.out.println(map.get(dataProperty_name).size());
////			System.out.println(list_dataProperty.size());
//			file_out.write("\n"+dataProperty_name+"\n");
//			int literal_court=map.get(dataProperty_name).size();
//			for(int i=0;i<literal_court;i++)
//			{
//				file_out.write((String)map.get(dataProperty_name).get(i)+",");
//			}
//			file_out.write("\n");
//			file_out.flush();
//		}
//		file_out.flush();
//		file_out.close();
		
//		//using sql
//		String queryString = 
//			    "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
//			    "SELECT ?url " +
//			    "WHERE {" +
//			    "      ?contributor foaf:name \"Jon Foobar\" . " +
//			    "      ?contributor foaf:weblog ?url . " +
//			    "      }";
//			System.out.println(queryString.hashCode());
//			Query query = QueryFactory.create(queryString);
//			 
//			// Execute the query and obtain results
//			QueryExecution qe = QueryExecutionFactory.create(query, model);
//			ResultSet results = qe.execSelect();
//			 
//			// Output query results 
//			ResultSetFormatter.out(System.out, results, query);
//			 
//			// Important - free up resources used running the query
//			qe.close();
	}
	
	public static void main(String[] args) throws IOException
	{
		operator();
		System.out.println("end");
	}
}
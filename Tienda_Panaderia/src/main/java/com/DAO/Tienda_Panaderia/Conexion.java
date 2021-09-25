package com.DAO.Tienda_Panaderia;

import java.sql.*;
public class Conexion {
	
	//Parametros de Conexión
	String bd="DB_Panaderia";
	String Login="root";
	String password="Admin_2021";
	String url="jdbc:mysql://localhost/"+bd;
	
	Connection con=null;
	
	//Constructor de conexión
	public Conexion()
	{
		try
		{
			//Obtener el driver para mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Obtener la conexión
			con=DriverManager.getConnection(url,Login,password);
			if(con!=null)
				System.out.println("Conexion a la base de datos: "+bd+" Exitosa");
		}
		catch(SQLException e)
		{
			System.out.println("Error de conexión: "+ e);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Error de conexión: "+ e);
		}
		catch(Exception e)
		{
			System.out.println("Error de conexión: "+ e);
		}
	}
	
	//Método para retornar la conexión
	public Connection getCon()
	{
		return con;
	}
	
	//Método para desconectar base de datos
	public void desconectar()
	{
		con=null;
	}

}

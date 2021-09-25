package com.DAO.Tienda_Panaderia;

import java.sql.*;
import java.util.ArrayList;
//Esta clase permite el acceso a la base de datos 

import com.DTO.Tienda_Panaderia.UsuarioVO;

public class UsuarioDAO {
	
	//Método para consultar los usuarios
	
	public ArrayList<UsuarioVO> listaUsuarios()
	{
		ArrayList<UsuarioVO> miLista =new ArrayList<UsuarioVO>();
		Conexion con= new Conexion();
		
		try
		{
			PreparedStatement consulta=con.getCon().prepareStatement("SELECT * FROM T_Usuarios");
			ResultSet rs = consulta.executeQuery();
			while(rs.next())
			{
				UsuarioVO persona=new UsuarioVO();
				persona.setCedula(Integer.parseInt(rs.getString("Cedula_Usu")));
				persona.setNombre(rs.getString("Nombre_Usu"));
				persona.setCorreo(rs.getString("Correo_Usu"));
				persona.setUsuario(rs.getString("Usuario"));
				persona.setClave(rs.getString("Clave_Usu"));
				
				miLista.add(persona);
			}
			rs.close();
			consulta.close();
			con.desconectar();
			
		}catch(Exception e)
		{
			System.out.println("Error No se pudo conectar "+e);
		}
		return miLista;
	}
	
	//Para consultar un usuario por número de documento
	public ArrayList<UsuarioVO> ConsultarUsuario(int documento)
	{
		ArrayList<UsuarioVO> miLista= new ArrayList<UsuarioVO>();
		Conexion con=new Conexion();
		try
		{
			PreparedStatement consulta=con.getCon().prepareStatement("SELECT * FROM T_Usuarios WHERE Cedula_Usu = ? ");
			consulta.setInt(1, documento);
			ResultSet rs=consulta.executeQuery();
			
			if(rs.next())
			{
				UsuarioVO persona=new UsuarioVO();
				persona.setCedula(Integer.parseInt(rs.getString("Cedula_Usu")));
				persona.setNombre(rs.getString("Nombre_Usu"));
				persona.setCorreo(rs.getString("Correo_Usu"));
				persona.setUsuario(rs.getString("Usuario"));
				persona.setClave(rs.getString("Clave_Usu"));
				
				miLista.add(persona);
			}
			rs.close();
			consulta.close();
			con.desconectar();
		}
		catch(Exception e)
		{
			System.out.println("Error Usuario no encontrado: "+e);
		}
		return miLista;
	}
	public void registrarUsuario(UsuarioVO persona)
	{
		Conexion con= new Conexion();
		try
		{
			Statement stmt= con.getCon().createStatement();
			stmt.executeUpdate("INSERT INTO T_Usuarios Values('"+persona.getCedula()+"','"+
			persona.getNombre()+"','"+persona.getCorreo()+"','"+persona.getUsuario()+"','"+
			persona.getClave()+"')");
			System.out.println("El ususario ha sido registrado");
			con.desconectar();
		}
		catch(Exception e)
		{
			System.out.println("Error de conexión: Usuario no registrado "+e);
			
		}
	}
	//Para eliminar por documento
	public void eliminarUsuario(int documento)
	{
		Conexion con=new Conexion();
		try
		{
			PreparedStatement consulta=con.getCon().prepareStatement("DELETE FROM T_Usuarios WHERE Cedula_Usu = ? ");
			consulta.setInt(1, documento);
			consulta.executeUpdate();
			
			System.out.println("El usuario ha sido eliminado");
			
			consulta.close();
			con.desconectar();
		}
		catch(Exception e)
		{
			System.out.println("Error Usuario no encontrado: "+e);
		}
		
	}
	//Para actualizar por nombre y correo
	public void actualizarUsuario(int documento, String nombre, String correo)
	{
		Conexion con=new Conexion();
		try
		{
			PreparedStatement consulta=con.getCon().prepareStatement("UPDATE T_Usuarios SET Nombre_Usu=?, Correo_Usu=? WHERE Cedula_Usu = ? ");
			consulta.setString(1, nombre);
			consulta.setString(2, correo);
			consulta.setInt(3, documento);
			consulta.executeUpdate();
			
			System.out.println("El usuario ha sido actualizado");
			
			consulta.close();
			con.desconectar();
		}
		catch(Exception e)
		{
			System.out.println("Error Usuario no encontrado: "+e);
		}
		
	}
}

package com.grupo8.gi;

import java.util.*;

public class Contacto implements Comparable<Contacto> 
{
	private static String BD_SERVER = "192.168.1.16";
	private static String BD_NAME = "GI";

	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	
	public static List<Contacto> ListaContactos()
	{
		List<Contacto> lista = new ArrayList<Contacto>();

		BD miBD = new BD(BD_SERVER, BD_NAME);
		String sel = "SELECT telefono FROM Contacto;";
		for(Object[] tupla: miBD.Select(sel))
			lista.add(new Contacto((String)tupla[0]));
		
		return lista;
	}
	public Contacto(String n, String a, String e, String t)
	{
		if (n == null || a == null || e == null || t == null)
			throw new Error("ERROR: Argumentos no válidos");

		BD miBD = new BD(BD_SERVER, BD_NAME);
		String ins = "INSERT INTO Contacto VALUES("
			+ "'" + n + "',"
			+ "'" + a + "',"
			+ "'" + e + "',"
			+ "'" + t + "');";
		miBD.Insert(ins);
		
		nombre = n;
		apellido = a;
		email = e;
		telefono = t;
		
	}
	
	public Contacto(String t)
	{
		BD miBD = new BD(BD_SERVER, BD_NAME);
		String sel = "SELECT * FROM Contacto WHERE telefono = '" + t +"';";
		Object[] tupla = miBD.Select(sel).get(0);
		
		nombre = (String)tupla[0];
		apellido = (String)tupla[1];
		email = (String)tupla[2];
		telefono = (String)tupla[3];
		
	}
	
	public String getValor(Campo campo)
	{
		String res = "";
		switch(campo)
		{
			case NOMBRE: 	res = nombre; break;
			case APELLIDO: 	res = apellido; break;
			case EMAIL: 	res = email; break;
			case TELEFONO:	res = telefono; break;
		}
		return res;
	}
	
	public void setValor(Campo campo, String valor)
	{
		if (valor == null)
			throw new Error("ERROR: Argumento no valido");

		BD miBD = new BD(BD_SERVER, BD_NAME);
		String up = "UPDATE Contacto SET ";
		
		switch(campo)
		{
			case NOMBRE: 	up += "nombre"; break;
			case APELLIDO: 	up += "apellido"; break;
			case EMAIL: 	up += "email"; break;
			case TELEFONO:	up += "telefono"; break;
		}

		up += " = '" + valor + "' WHERE telefono = '" + telefono +"';";
		
		miBD.Update(up);
		
		switch(campo)
		{
			case NOMBRE: 	nombre = valor; break;
			case APELLIDO:  apellido = valor; break;
			case EMAIL: 	email = valor; break;
			case TELEFONO:	telefono = valor; break;
		}
		
	}
	
	public void borraContacto()
	{
		BD miBD = new BD(BD_SERVER, BD_NAME);
		String del = "DELETE Contacto WHERE telefono = '" + telefono +"';";
		miBD.Delete(del);
		
		nombre = null;
		apellido = null;
		email = null;
		telefono = null;
	}
	

	public String toString()
	{
		return "Contacto: " + getValor(Campo.NOMBRE) +", "
				+ getValor(Campo.APELLIDO) + ", "
				+ getValor(Campo.TELEFONO) + ", "
				+ getValor(Campo.EMAIL);
	}
	
	public boolean equals(Object o)
	{
		return (o instanceof Contacto)
				&& this.getValor(Campo.NOMBRE).equalsIgnoreCase(((Contacto) o).getValor(Campo.NOMBRE))
				&& this.getValor(Campo.APELLIDO).equalsIgnoreCase(((Contacto) o).getValor(Campo.APELLIDO))
				&& this.getValor(Campo.TELEFONO).equalsIgnoreCase(((Contacto) o).getValor(Campo.TELEFONO))
				&& this.getValor(Campo.EMAIL).equalsIgnoreCase(((Contacto) o).getValor(Campo.EMAIL));
	}


	public int compareTo(Contacto c)
	{
		int res = getValor(Campo.NOMBRE).compareTo(c.getValor(Campo.NOMBRE));
		if (res==0)
			res = getValor(Campo.APELLIDO).compareTo(c.getValor(Campo.APELLIDO));
		if (res==0)
			res = getValor(Campo.TELEFONO).compareTo(c.getValor(Campo.TELEFONO));
		if (res==0)
			res = getValor(Campo.EMAIL).compareTo(c.getValor(Campo.EMAIL));
		return res;
	}
	

}

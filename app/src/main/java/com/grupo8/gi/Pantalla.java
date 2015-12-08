package com.grupo8.gi;

import java.util.*;

public class Pantalla 
{
	public enum PANTALLAS {MEDICAMENTOS};

    private static String BD_SERVER = "192.168.1.16";
    private static String BD_NAME = "GI";

	private String pantalla;


    public static List<Pantalla> ListaPantalla()
    {
		// Retorna una lista con todos los obejtos de la clase almacenados en la base de datos
		BD miBD = new BD(BD_SERVER, BD_NAME);
		ArrayList<Pantalla> lista = new ArrayList<Pantalla>();
					
		for(Object[] tupla: miBD.Select("SELECT pantalla FROM tPantalla")) {
			String p = (String) tupla[0];
			Pantalla pantalla = new Pantalla(p);
			lista.add(pantalla);
		}
		
    	miBD.finalize();
		return lista;
    }
    
    public Pantalla(String p)
    {
	// Crea el objeto cargando sus valores de la base de datos. Si no existe lo inserta

		BD miBD = new BD(BD_SERVER, BD_NAME);

		int num= (Integer) miBD.SelectEscalar("SELECT COUNT(*) FROM tPantalla WHERE pantalla ='"+ p + "';");
		if (num==0)
			miBD.Insert("INSERT INTO tPantalla VALUES ('" + p +"');"); 
		
    	miBD.finalize();
        pantalla = p;        
    }
    
	public void setPantalla(String value) 
	{
		// Actualiza el atributo en memoria y en la base de datos
		BD miBD = new BD(BD_SERVER, BD_NAME);
		miBD.Update("UPDATE tPantalla SET pantalla = '" + value + "' WHERE pantalla = '" + pantalla + "'");
		miBD.finalize();
		pantalla = value;
	}

	public String getPantalla() 
	{
		return pantalla;
	}    

}

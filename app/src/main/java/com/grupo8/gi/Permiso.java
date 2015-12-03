package com.grupo8.gi;

import java.util.*;


public class Permiso 
{
    private static String BD_SERVER = "192.168.1.16";
    private static String BD_NAME = "GI";
    
	private String rolName;
	private String pantalla;
    private boolean acceso;
    private boolean modificacion;


    public static List<Permiso> ListaPermisosRol(String rolName)
    {
		BD miBD = new BD(BD_SERVER, BD_NAME);
		List<Permiso> lista = new ArrayList<Permiso>();
    	// Retorna una lista con todos los obejtos de la clase almacenados en la base de datos
		for(Object[] tupla : miBD.Select("SELECT rolName, pantalla FROM tPermiso WHERE rolName = '" + rolName + "'")) {
			String r = (String) tupla[0];
			String p = (String) tupla[1];
    		Permiso permiso = new Permiso(r, p);
    		lista.add(permiso);
    	}
		
    	miBD.finalize();
    	return lista;
				
    }
    
    public Permiso(String r, String p)
    {
		// Crea el objeto cargando sus valores de la base de datos
		BD miBD = new BD(BD_SERVER, BD_NAME);

		rolName = r;
		pantalla = p;
		
		Object[] tupla = miBD.Select("SELECT acceso, modificacion FROM tPermiso WHERE rolName = '" + rolName + "'").get(0);
		acceso = (boolean) tupla[0];
		modificacion = (boolean) tupla[1];
		
		miBD.finalize();
    }

    public Permiso(String r, String p, boolean a, boolean m)
    {
    	int iA=0,iM=0;
    	
    	if (a) iA=1;
    	if (m) iM = 1;
    	
		// Crea el objeto y lo inserta en la base de datos
    	
    	rolName = r;
    	pantalla = p;
    	acceso = a;
    	modificacion = m;
		BD miBD = new BD(BD_SERVER, BD_NAME);
		miBD.Insert("INSERT tPermiso (rolName,pantalla,acceso,modificacion) VALUES ('" + rolName + "','" + pantalla + "'," + iA + "," + iM + ")");
		miBD.finalize();
	
    }
    
	public void setRolName(String value) 
	{
		BD miBD = new BD(BD_SERVER, BD_NAME);

		miBD.Update("UPDATE tPermiso SET rolName = '"+ value 
				+ "' WHERE rolName='"+ this.rolName + "';");
    	miBD.finalize();
		this.rolName = value;
	}

	public String getRolName() 
	{
		return rolName;
	}
    
    public String getPantalla() 
    {
    	return pantalla; 
    }
    
    public void setPantalla(String value) 
    {
		// Actualiza el atributo en memoria y en la base de datos
		BD miBD = new BD(BD_SERVER, BD_NAME);
		miBD.Update("UPDATE tPermiso SET pantalla = '" + value + "' WHERE rolName = '" + rolName + "'");
		miBD.finalize();
    	pantalla = value;
    }
    

    public boolean getAcceso() 
    { 
    	return acceso; 
    }
        
    public void setAcceso(boolean value) 
    { 
    	int iA =0;
    	if (value) iA=1;
		// Actualiza el atributo en memoria y en la base de datos

		BD miBD = new BD(BD_SERVER, BD_NAME);
		miBD.Update("UPDATE tPermiso SET acceso = " + iA + " WHERE rolName = '" + rolName + "'");
		miBD.finalize();
    	acceso = value;
    }

    
    public boolean getModificacion() 
    { 
    	return modificacion; 
    }
    
    public void setModificacion(boolean value) 
    { 
    	int iM =0;
    	if (value) iM=1;
    	
		// Actualiza el atributo en memoria y en la base de datos
		BD miBD = new BD(BD_SERVER, BD_NAME);
		miBD.Update("UPDATE tPermiso SET modificacion = " + iM + " WHERE rolName = '" + rolName + "'");
		miBD.finalize();
    	modificacion = value;
    }


}

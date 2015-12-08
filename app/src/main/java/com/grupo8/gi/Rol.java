package com.grupo8.gi;

import android.os.Parcel;

import java.util.*;


public class Rol 
{

    public enum ROLES {ADMINISTRADOR,USUARIO,INVITADO};
    
    private String rolName;
    private String rolDes;
    private boolean admin;
    private List<Permiso> permisos;


    public static List<Rol> ListaRoles()
	{
        BD miBD = new BD();
        ArrayList<Rol> lista = new ArrayList<Rol>();
		// Retorna una lista con todos los obejtos de la clase almacenados en la base de datos
		for(Object[] tupla : miBD.Select("SELECT rolName FROM tROL")) {
    		String rolName = (String) tupla[0];
			Rol r = new Rol(rolName);
			lista.add(r);
    	}
		
    	miBD.finalize();
		return lista;
	}
	
    public Rol(String name)
    {
		// Crea el objeto cargando sus valores de la base de datos
    	rolName = name;
        BD miBD = new BD();
        Object[] tupla = miBD.Select("SELECT rolDes, admin FROM tRol WHERE rolName = '" + rolName + "'").get(0);
    	rolDes = (String) tupla[0];
    	admin = (boolean) tupla[1];
    	
    	permisos = Permiso.ListaPermisosRol(rolName);
    	miBD.finalize();
    }
    
    public Rol(String name, String des, boolean adm)
    {
		// Crea el objeto y lo inserta en la base de datos
    	rolName = name;
    	rolDes = des;
    	admin = adm;
    	int a = 0;
    	if(adm)
    		a = 1;

        BD miBD = new BD();
        miBD.Insert("INSERT tROL (rolName, rolDes, admin) VALUES ('" + rolName + "','" + rolDes + "'," + a + ")");
    	miBD.finalize();
    	permisos = null;
    }

    public String getRolName() 
    { 
    	return rolName; 
    }
        
    public void setRolName(String value) 
    { 
		// Actualiza el atributo en memoria y en la base de datos
        BD miBD = new BD();
        miBD.Update("UPDATE tROL SET rolName = '" + value + "' WHERE rolName = '" + rolName + "'");
        miBD.finalize();
        rolName = value;
    }

    public String getRolDes() 
    { 
    	return rolDes; 
    }
    
    public void setRolDes(String value) 
    {
		// Actualiza el atributo en memoria y en la base de datos
        BD miBD = new BD();
        miBD.Update("UPDATE tROL SET rolDes = '" + value + "' WHERE rolName = '" + rolName + "'");
    	miBD.finalize();
    	rolDes = value;
    }

    public boolean getAdmin()
    { 
    	return admin; 
    }
    
    public void setAdmin(boolean value)
    {
    	throw new Error("Un Rol no puede concederse permisos de administraci�n a s� mismo.");
    }
    public void setAdmin(Rol other, boolean value)
    {
    	if (!admin) throw new Error("Rol sin permiso para establecer administradores.");
    	
    	int i = 0;
    	if (value) i=1;
    	
		// Actualiza el atributo admin de other en memoria y en la base de datos
    	for (Rol r : ListaRoles())
			if(r.getRolName().equalsIgnoreCase(other.getRolName())){
                //other.setAdmin(value);
                other.admin = value;
            }


        BD miBD = new BD();
        miBD.Update("UPDATE tROL SET admin = '" + value + "' WHERE rolName = '" + other.getRolName() + "'");
    	miBD.finalize();
    	
    }

    
    public boolean Acceso(String pantalla)
    {
        for (Permiso p : permisos)
        {
            if (p.getPantalla().equals(pantalla)) return p.getAcceso();
        }
        return false;
    }

    public boolean Modificacion(String pantalla)
    {
        for (Permiso p : permisos)
        {
            if (p.getPantalla().equals(pantalla)) return p.getModificacion();
        }
        return false;
    }

    public void AddPermiso(Permiso p)
    {
        if (!permisos.contains(p))
        {
            permisos.add(p);
        }
    }
    
}

package com.grupo8.gi;

import java.io.Serializable;
import java.util.*;

public class Usuario
{

    private String nombre;
    private String password;
    private Rol rol;

	public static List<Usuario> ListaUsuarios()
	{
		// Retorna una lista con todos los obejtos de la clase almacenados en la base de datos
		BD miBD = new BD();
		ArrayList<Usuario> lista = new ArrayList<Usuario>(); 
					
		for(Object[] tupla: miBD.Select("SELECT nombre,password FROM tUsuario")) {
			String id = (String) tupla[0];
			String p = (String) tupla[1];
			Usuario u = new Usuario(id, p);
			lista.add(u);
		}
		
    	miBD.finalize();
		return lista;
	}
	
    public Usuario(String n, String p)
    {
		// Crea el objeto cargando sus valores de la base de datos
        BD miBD = new BD();
        Object[] tupla = miBD.Select("SELECT * FROM tUsuario WHERE nombre='"+n+"';").get(0);
    	miBD.finalize();
    	
        nombre = (String)tupla[0];
        password = (String)tupla[1];

        if (p.compareTo(password)!=0) {
        	nombre = password = "";
        	throw new Error("Usuario o Contraseña Incorrecta");
        }
        
        rol = new Rol((String) tupla[2]);                	
    }
    
    public Usuario(String n, String p, Rol r)
    {
		// Crea el objeto y lo inserta en la base de datos
        BD miBD = new BD();

    	miBD.Insert("INSERT INTO tUsuario VALUES("
				+ "'" + n + "', '" + p + "', '" + r.getRolName() + "');" );			
    	miBD.finalize();
        nombre = n;
        password = p;
        rol = r;
    }

    public String getNombre() 
    { 
    	return nombre; 
    }

    public void setNombre(String value) 
    { 
    	
		// Actualiza el atributo en memoria y en la base de datos
        BD miBD = new BD();
        miBD.Update("UPDATE tUsuario SET nombre = '" + value
    			+ "' WHERE nombre ='"+ this.nombre + "';");
    	miBD.finalize();
    	nombre = value; 
    }

    public String getPassword() 
    { 
    	return password; 
    }
        
    public void setPassword(String value)
    { 
		// Actualiza el atributo en memoria y en la base de datos
        BD miBD = new BD();
        miBD.Update("UPDATE tUsuario SET password = '" + value + "' WHERE nombre = '" + nombre + "'");
    	miBD.finalize();
    	password = value;
    }

    public Rol getRol()
    {
        return rol;
    }
    
    public void setRol()
    {
    	throw new Error("Error: Un usuario no puede cambiar el rol de si mismo.");     
    }

    public void ModiRol(Usuario u, Rol r)
    {
        if (this.rol.getAdmin()) {
        	// Actualiza el atributo rol de u en memoria y en la base de datos
            BD miBD = new BD();
            miBD.Update("UPDATE tUsuario SET rolName = '" + r.getRolName() + "' WHERE nombre = '" + u.getNombre() + "'");
        	miBD.finalize();
        	u.rol = r;
        }
        else throw new Error("El usuario " + this.getNombre() 
        		+ " no puede cambiar el rol del usuario " 
        		+ u.getNombre());
    }

    public boolean AccesoPantalla(String p)
    {
        return rol.Acceso(p);
    }

    public boolean ModificaPantalla(String p)
    {
        return rol.Modificacion(p);
    }
	
	public String toString() {
        return nombre + "     " + password + "     " + rol.getRolName();
    }

}

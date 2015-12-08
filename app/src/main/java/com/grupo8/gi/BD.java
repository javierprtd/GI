package com.grupo8.gi;


import android.os.StrictMode;
import android.util.Log;
import java.sql.*;
import java.util.*;

public class BD 
{
	private static final String SERVER = "192.168.1.131";
    private static final String DATA_BASE_NAME = "GI";
    private static final String USER_BD = "invitado";
    private static final String USER_PASS = "invitado";

	private Connection con = null;

	public BD (String server, String databaseName) {
		try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connUrl = "jdbc:jtds:sqlserver://" + SERVER + "/" + DATA_BASE_NAME;
            con = DriverManager.getConnection(connUrl, USER_BD, USER_PASS);
		} catch (SQLException e) {
			throw new Error(e.getMessage());
		} catch (ClassNotFoundException e) {
            throw new Error(e.getMessage());
		}
	}

	public BD () {
		try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connUrl = "jdbc:jtds:sqlserver://" + SERVER + "/" + DATA_BASE_NAME;
            con = DriverManager.getConnection(connUrl, USER_BD, USER_PASS);
		} catch (SQLException e) {
			throw new Error(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new Error(e.getMessage());
		}
	}

	public void finalize () {
		try
		{
			if (con != null)  con.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error al Cerrar la Conexión");
		}
    }

	public Object SelectEscalar(String sel) {
		ResultSet rset;
		Object res = null;
		try
		{
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(sel);
			rset.next();
			res = rset.getObject(1);
			rset.close();
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error en el SELECT: " + sel);
		}		
		
		return res;
	}
	
	public List<Object[]> Select(String sel) {
		ResultSet rset;
		List<Object[]> lista = new ArrayList<Object[]>();
		try
		{
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(sel);
			ResultSetMetaData meta = rset.getMetaData();
			int numCol = meta.getColumnCount();
			while (rset.next()) {
				Object[] tupla = new Object[numCol];
				for(int i = 0; i < numCol;i++)
					tupla[i] = rset.getObject(i+1);

				lista.add(tupla);
			}

			rset.close();
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error en el SELECT: " + sel);
		}		
		
		return lista;
	}
	
	public void Insert(String ins) {
		try
		{
			Statement stmt = con.createStatement();
			stmt.execute(ins);
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error en el INSERT: " + ins);
		}
	}

	public void Delete(String del) {
		try
		{
			Statement stmt = con.createStatement();
			stmt.execute(del);
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error en el DELETE: " + del);
		}
	}

	public void Update(String up) {
		try
		{
			Statement stmt = con.createStatement();
			stmt.execute(up);
			stmt.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error en el UPDATE: " + up);
		}
	}

}

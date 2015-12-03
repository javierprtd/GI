package com.grupo8.gi;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.*;
import java.util.*;

public class BD 
{
	private Connection con;

	public BD (String server, String databaseName) {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			String connUrl = "jdbc:jtds:sqlserver://" + server + "/" + databaseName;
			con = DriverManager.getConnection(connUrl);
		} catch (SQLException e) {
			Log.e("Error1", e.getMessage());
		} catch (ClassNotFoundException e) {
			Log.e("Error2", e.getMessage());
		}
	}

	public void finalize ()
	{
		try
		{
			if (con != null)  con.close();
		}
		catch (SQLException ex)
		{
			throw new Error("Error al Cerrar la Conexi�n");
		}
    }

	public Object SelectEscalar(String sel)
	{
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
	
	public List<Object[]> Select(String sel)
	{
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
	
	public void Insert(String ins)
	{
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

	public void Delete(String del)
	{
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

	public void Update(String up)
	{
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

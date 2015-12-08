package com.grupo8.gi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Laboratorio implements Serializable{

    private static final String NAME_TABLE = "tLaboratorio";
    private static final String PK_TABLE = "ID_LABORATORIO";

    private int ID_LABORATORIO;
    private String NOMBRE_LABORATORIO;

    public static List<Laboratorio> ListaLaboratorios() {
        List<Laboratorio> lista = new ArrayList<Laboratorio>();

        BD miBD = new BD();
        String sel = "SELECT "+PK_TABLE+" FROM "+NAME_TABLE+";";

        for(Object[] tupla: miBD.Select(sel)) {
            lista.add(new Laboratorio((int) tupla[0]));
        }

        return lista;
    }

    public Laboratorio(int idLaboratorio){
        BD miBD = new BD();
        String sel = "SELECT * FROM "+NAME_TABLE+" WHERE "+PK_TABLE+" ="+idLaboratorio+";";
        Object[] tupla = miBD.Select(sel).get(0);

        ID_LABORATORIO = (int)tupla[0];
        NOMBRE_LABORATORIO = (String)tupla[1];
        miBD.finalize();
    }


    public Laboratorio(int idLaboratorio, String nombreLaboratorio) {
        BD miBD = new BD();
        String ins = "INSERT INTO "+NAME_TABLE+" VALUES("
                      + idLaboratorio + ","
                + "'" + nombreLaboratorio + "');";
        miBD.Insert(ins);

        ID_LABORATORIO = idLaboratorio;
        NOMBRE_LABORATORIO = nombreLaboratorio;
        miBD.finalize();
    }

    public int getID_LABORATORIO() {
        return ID_LABORATORIO;
    }

    public String getNOMBRE_LABORATORIO() {
        return NOMBRE_LABORATORIO;
    }

    public void setID_LABORATORIO(int idLaboratorio) {
        BD miBD = new BD();
        miBD.Update("UPDATE "+NAME_TABLE+" SET ID_LABORATORIO = " + idLaboratorio + " WHERE ID_LABORATORIO = " + ID_LABORATORIO +"" );
        miBD.finalize();
        this.ID_LABORATORIO = idLaboratorio;
    }

    public void setNOMBRE_LABORATORIO(String nombreLaboratorio) {
        BD miBD = new BD();
        miBD.Update("UPDATE "+NAME_TABLE+" SET NOMBRE_LABORATORIO = '" + nombreLaboratorio + "' WHERE ID_LABORATORIO = " + ID_LABORATORIO + "");
        miBD.finalize();
        this.NOMBRE_LABORATORIO = nombreLaboratorio;
    }
}

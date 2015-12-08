package com.grupo8.gi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge on 07/12/2015.
 */
public class Medicamento implements Serializable {
    private static final String NAME_TABLE = "tMedicamento";
    private static final String PK_TABLE = "ID_MEDICAMENTO";

    private int idMedicamento;
    private String nombreMedicamento;
    private int cantidadDisponible;
    private int laboratorio;

    private static BD miBD = new BD();

    public static List<Medicamento> ListaMedicamentos() {
        List<Medicamento> lista = new ArrayList<>();

       // BD miBD = new BD();
        String sel = "SELECT "+PK_TABLE+" FROM "+NAME_TABLE+";";
        for(Object[] tupla: miBD.Select(sel)) {
            lista.add(new Medicamento((int) tupla[0]));
        }
       // miBD.finalize();
        return lista;
    }

    public Medicamento(int idMedicamento){
        BD miBD = new BD();
        String sel = "SELECT * FROM "+NAME_TABLE+" WHERE "+PK_TABLE+"="+idMedicamento+";";
        Object[] tupla = miBD.Select(sel).get(0);

        this.idMedicamento = (int)tupla[0];
        nombreMedicamento = (String)tupla[1];
        cantidadDisponible = (int)tupla[2];
        laboratorio = (int)tupla[3];

        miBD.finalize();
    }

    public Medicamento(int idMedicamento, String nombreMedicamento, int cantidadDisponible, int laboratorio) {
        BD miBD = new BD();
        String ins = "INSERT INTO "+NAME_TABLE+" VALUES("
                + idMedicamento + ","
                + "'" + nombreMedicamento + "',"
                + cantidadDisponible + ","
                + laboratorio + ");";
        miBD.Insert(ins);

        this.idMedicamento = idMedicamento;
        this.nombreMedicamento = nombreMedicamento;
        this.cantidadDisponible = cantidadDisponible;
        this.laboratorio = laboratorio;

        miBD.finalize();
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public int getLaboratorio() {
        return laboratorio;
    }

    public void setIdMedicamento(int idMedicamento) {
        BD miBD = new BD();
        miBD.Update("UPDATE "+NAME_TABLE+" SET ID_MEDICAMENTO = " + idMedicamento + " WHERE ID_MEDICAMENTO = " + this.idMedicamento + "");
        miBD.finalize();
        this.idMedicamento = idMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        BD miBD = new BD();
        miBD.Update("UPDATE "+NAME_TABLE+" SET NOMBRE_MEDICAMENTO = '" + nombreMedicamento + "' WHERE ID_MEDICAMENTO = " + idMedicamento + "");
        miBD.finalize();
        this.nombreMedicamento = nombreMedicamento;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        BD miBD = new BD();
        miBD.Update("UPDATE "+NAME_TABLE+" SET CANTIDAD_DISPONIBLE = " + cantidadDisponible + " WHERE ID_MEDICAMENTO = " + idMedicamento + "");
        miBD.finalize();
        this.cantidadDisponible = cantidadDisponible;
    }

    public void setLaboratorio(int laboratorio) {
        BD miBD = new BD();
        miBD.Update("UPDATE "+NAME_TABLE+" SET LABORATORIO = " + laboratorio + " WHERE ID_MEDICAMENTO = " + idMedicamento + "");
        miBD.finalize();
        this.laboratorio = laboratorio;
    }

    public void borrarMedicamento(){
            BD miBD = new BD();
            miBD.Delete("DELETE FROM "+NAME_TABLE+" WHERE ID_MEDICAMENTO='"+ idMedicamento + "';");
    }

}

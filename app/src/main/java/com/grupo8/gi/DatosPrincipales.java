package com.grupo8.gi;

import java.util.List;

public class DatosPrincipales {
    private static DatosPrincipales ourInstance = new DatosPrincipales();
    private List<Medicamento> medicamentosList = null;
    private List<Laboratorio> laboratorioList = null;

    public static DatosPrincipales getInstance() {
        return ourInstance;
    }

    private DatosPrincipales() {

    }

    public void cargarDatosPrincipales(){
        medicamentosList = Medicamento.ListaMedicamentos();
        laboratorioList = Laboratorio.ListaLaboratorios();
    }

    public List<Laboratorio> getLaboratorioList() {
        return laboratorioList;
    }

    public List<Medicamento> getMedicamentosList() {
        return medicamentosList;
    }
}

package com.grupo8.gi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.button_borrar)
	protected Button buttonBorra;
    @InjectView(R.id.button_insertar)
    protected Button buttonAñade;
    @InjectView(R.id.button_modificar)
    protected Button buttonModificar;
    @InjectView(R.id.button_limpiar)
    protected Button buttonLimpiar;

    @InjectView( R.id.edit_text_id_medicamento)
	protected EditText editTextidMedicamento;

    @InjectView(R.id.edit_text_nombre_medicamento)
    protected EditText editTextnombreMedicamento;

    @InjectView(R.id.edit_text_cantidad_disponible)
    protected EditText editTextCantidadDisponible;

    @InjectView(R.id.spinner)
    protected Spinner spinnerLaboratorio;

    @InjectView(R.id.list_View_Medicamentos)
    protected ListView listViewMedicamentos;

	private boolean acceso, modificacion;
	private List<Medicamento> medicamentosList;
    List<Laboratorio> laboratoriosList;
	private Medicamento medicamentoMostrado;
    private int positionItemMostrado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

		Intent myIntent = getIntent();
		acceso = myIntent.getBooleanExtra(LoginActivity.NAME_INTENT_ACCESS, false);
		modificacion = myIntent.getBooleanExtra(LoginActivity.NAME_INTENT_MODIFY, false);
        medicamentosList = DatosPrincipales.getInstance().getMedicamentosList();
        laboratoriosList = DatosPrincipales.getInstance().getLaboratorioList();
       /* medicamentosList = (ArrayList<Medicamento>) myIntent.getSerializableExtra(LoginActivity.NAME_INTENT_MEDICAMENTOS);
        laboratoriosList = (ArrayList<Laboratorio>)myIntent.getSerializableExtra(LoginActivity.NAME_INTENT_LABORATORIOS);*/
	}


    private void initMedicamentosListView() {
       // medicamentosList = Medicamento.ListaMedicamentos();
        ArrayAdapter<Medicamento> arrayAdapter = new MedicamentosAdapter(getApplicationContext(),medicamentosList);
        listViewMedicamentos.setAdapter(arrayAdapter);
    }

    private void initLaboratoriosSpinner(){
       // List<Laboratorio> laboratoriosList = Laboratorio.ListaLaboratorios();
        List<String> identificadores = new ArrayList<>();

        for(Laboratorio laboratorio : laboratoriosList){
            identificadores.add(laboratorio.getNOMBRE_LABORATORIO());
        }

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_laboratorio, identificadores);
        spinnerLaboratorio.setAdapter(arrayAdapter);
    }

    private void registerListenerMedicamentosListView(){
        listViewMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                positionItemMostrado = position;
                medicamentoMostrado = medicamentosList.get(position);
                editTextidMedicamento.setText(String.valueOf(medicamentoMostrado.getIdMedicamento()));
                editTextnombreMedicamento.setText(medicamentoMostrado.getNombreMedicamento());
                editTextCantidadDisponible.setText(String.valueOf(medicamentoMostrado.getCantidadDisponible()));
                spinnerLaboratorio.setSelection(medicamentoMostrado.getLaboratorio());
            }
        });
    }

    private void habilitarBotones(boolean habilitar){
        buttonAñade.setEnabled(habilitar);
        buttonBorra.setEnabled(habilitar);
        buttonModificar.setEnabled(habilitar);
        buttonLimpiar.setEnabled(habilitar);
    }

	@Override
	protected void onStart() {
		super.onStart();

		if (acceso) {
            initMedicamentosListView();
            registerListenerMedicamentosListView();
            initLaboratoriosSpinner();
			if (modificacion) {
                habilitarBotones(true);
			} else {
                habilitarBotones(false);
			}
        } else {


		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

    public void onClickErase(View v){
        medicamentoMostrado.borrarMedicamento();
        ArrayAdapter<Medicamento> arrayAdapter = (ArrayAdapter<Medicamento>) listViewMedicamentos.getAdapter();
        arrayAdapter.remove(medicamentoMostrado);
        arrayAdapter.notifyDataSetInvalidated();
    }

    public void onClickInsert(View v){
        int idMedicamento = Integer.parseInt(String.valueOf(editTextidMedicamento.getText().toString()));
        String nombreMedicamento = editTextnombreMedicamento.getText().toString();
        int cantidadDisponible = Integer.parseInt(editTextCantidadDisponible.getText().toString());
        int laboratorio = spinnerLaboratorio.getSelectedItemPosition();

        Medicamento medicamento = new Medicamento(idMedicamento,nombreMedicamento,cantidadDisponible,laboratorio);

        ArrayAdapter<Medicamento> arrayAdapter = (ArrayAdapter<Medicamento>) listViewMedicamentos.getAdapter();
        arrayAdapter.insert(medicamento,0);
        arrayAdapter.notifyDataSetInvalidated();
        //TODO GENERAR CATCH PARA ID REPETIDOS O LAB NO EXISTENTES
    }

    public void onClickModify(View v){
        int idMedicamento = Integer.parseInt(String.valueOf(editTextidMedicamento.getText().toString()));
        String nombreMedicamento = editTextnombreMedicamento.getText().toString();
        int cantidadDisponible = Integer.parseInt(editTextCantidadDisponible.getText().toString());
        int laboratorio = spinnerLaboratorio.getSelectedItemPosition();

        boolean modificado = false;

        if(idMedicamento != medicamentoMostrado.getIdMedicamento()){
            medicamentoMostrado.setIdMedicamento(idMedicamento);
            modificado = true;
        }

        if(!nombreMedicamento.equals(medicamentoMostrado.getNombreMedicamento())){
            medicamentoMostrado.setNombreMedicamento(nombreMedicamento);
            modificado = true;
        }

        if(cantidadDisponible != medicamentoMostrado.getCantidadDisponible()){
            medicamentoMostrado.setCantidadDisponible(cantidadDisponible);
            modificado = true;
        }

        if(laboratorio != medicamentoMostrado.getLaboratorio()){
            medicamentoMostrado.setLaboratorio(laboratorio);
            modificado = true;
        }

        if(modificado){
            ArrayAdapter<Medicamento> arrayAdapter = (ArrayAdapter<Medicamento>) listViewMedicamentos.getAdapter();
            arrayAdapter.notifyDataSetInvalidated();
        }
    }

    public void onClickClean(View v){
        editTextCantidadDisponible.setText("");
        editTextidMedicamento.setText("");
        //spinnerLaboratorio.setText(""); //TODO LAB va por spinner
        editTextnombreMedicamento.setText("");

        listViewMedicamentos.setSelection(0);


    }
}

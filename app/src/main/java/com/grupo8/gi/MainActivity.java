package com.grupo8.gi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {

	private Button buttonBorra, buttonAnade, buttonModifica;
	private EditText etNombre, etApellido, etTelefono, etEmail;
	private ListView contactsListView;
	private boolean acceso, modificacion;
	private List<Contacto> contactoList;
	private Campo campo;
	private Contacto contacto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent myIntent = getIntent();
		acceso = myIntent.getBooleanExtra("acceso", false);
		modificacion = myIntent.getBooleanExtra("modificacion", false);

		if (acceso) {
			contactsListView = (ListView) findViewById(R.id.main_lv_contacts);

			if (modificacion) {
				etNombre = (EditText) findViewById(R.id.main_et_nombre);
				etApellido = (EditText) findViewById(R.id.main_et_apellido);
				etEmail = (EditText) findViewById(R.id.main_et_email);
				etTelefono = (EditText) findViewById(R.id.main_et_telefono);

				buttonBorra = (Button) findViewById(R.id.main_btn_borra);
				buttonAnade = (Button) findViewById(R.id.main_btn_anade);
				buttonModifica = (Button) findViewById(R.id.main_btn_reseta);
			}
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

		if (acceso) {
			contactoList = Contacto.ListaContactos();
            ArrayAdapter<Contacto> arrayAdapter = new ArrayAdapter<Contacto>(getApplicationContext(), R.layout.list_item, contactoList);
            contactsListView.setAdapter(arrayAdapter);
			Toast toast = Toast.makeText(this, "Lista de contactos cargada", Toast.LENGTH_LONG);
			TextView vista = (TextView) toast.getView().findViewById(android.R.id.message);
			if (vista != null) vista.setGravity(Gravity.CENTER);
			toast.show();
			if (modificacion) {
				contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
						contacto = contactoList.get(position);
						etNombre.setText(contacto.getValor(campo.NOMBRE));
						etApellido.setText(contacto.getValor(campo.APELLIDO));
						etEmail.setText(contacto.getValor(campo.EMAIL));
						etTelefono.setText(contacto.getValor(campo.TELEFONO));
					}
				});
			} else {
				toast = Toast.makeText(this, "No tienes permiso para modificar", Toast.LENGTH_LONG);
				vista = (TextView) toast.getView().findViewById(android.R.id.message);
				if (vista != null) vista.setGravity(Gravity.CENTER);
				toast.show();
			}
        } else {
			Toast toast = Toast.makeText(this, "No tienes acceso a la lista", Toast.LENGTH_LONG);
			TextView vista = (TextView) toast.getView().findViewById(android.R.id.message);
			if (vista != null) vista.setGravity(Gravity.CENTER);
			toast.show();
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void onClick(View v) {
		if (v.equals(buttonBorra)) {
			try {
				contacto = new Contacto(etTelefono.getText().toString());

				for (Contacto c : contactoList) {
					if (c.compareTo(contacto) == 0) {
						contacto.borraContacto();
						contactoList.remove(c);
						ArrayAdapter<Contacto> arrayAdapter = new ArrayAdapter<Contacto>(this, R.layout.list_item, contactoList);
						contactsListView.setAdapter(arrayAdapter);
						etNombre.setText("");
						etApellido.setText("");
						etTelefono.setText("");
						etEmail.setText("");
						Toast toast = Toast.makeText(this, "Contacto borrado", Toast.LENGTH_LONG);
						TextView vista = (TextView) toast.getView().findViewById(android.R.id.message);
						if (vista != null) vista.setGravity(Gravity.CENTER);
						toast.show();
						break;
					}
				}
			} catch (Exception e) {
				Toast toast = Toast.makeText(this, "No existe tal contacto", Toast.LENGTH_LONG);
				TextView vista = (TextView) toast.getView().findViewById(android.R.id.message);
				if (vista != null) vista.setGravity(Gravity.CENTER);
				toast.show();
			}
		} else if (v.equals(buttonAnade)) {
			contacto = new Contacto(etNombre.getText().toString(), etApellido.getText().toString(), etEmail.getText().toString(), etTelefono.getText().toString());
			contactoList = Contacto.ListaContactos();
			ArrayAdapter<Contacto> arrayAdapter = new ArrayAdapter<Contacto>(this, R.layout.list_item, contactoList);
			contactsListView.setAdapter(arrayAdapter);

			etNombre.setText("");
			etApellido.setText("");
			etTelefono.setText("");
			etEmail.setText("");
		} else if (v.equals(buttonModifica)) {
			try {
				contacto = new Contacto(etTelefono.getText().toString());

				for (Contacto c : contactoList) {
					if (c.compareTo(contacto) == 0) {
						contactoList.remove(c);
						break;
					}
				}

				contacto.setValor(campo.NOMBRE, etNombre.getText().toString());
				contacto.setValor(campo.APELLIDO, etApellido.getText().toString());
				contacto.setValor(campo.EMAIL, etEmail.getText().toString());
				contacto.setValor(campo.TELEFONO, etTelefono.getText().toString());

				etNombre.setText("");
				etApellido.setText("");
				etTelefono.setText("");
				etEmail.setText("");

				contactoList = Contacto.ListaContactos();
				ArrayAdapter<Contacto> arrayAdapter = new ArrayAdapter<Contacto>(this, R.layout.list_item, contactoList);
				contactsListView.setAdapter(arrayAdapter);

				Toast toast = Toast.makeText(this, "Contacto modificado", Toast.LENGTH_LONG);
				TextView vista = (TextView) toast.getView().findViewById(android.R.id.message);
				if (vista != null) vista.setGravity(Gravity.CENTER);
				toast.show();

			} catch (Exception e) {
				Toast toast = Toast.makeText(this, "No existe tal contacto", Toast.LENGTH_LONG);
				TextView vista = (TextView) toast.getView().findViewById(android.R.id.message);
				if (vista != null) vista.setGravity(Gravity.CENTER);
				toast.show();
			}
		}
	}
}

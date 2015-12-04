package com.grupo8.gi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnClickListener {

	private Button buttonBusca, buttonBorra, buttonAnade, buttonReseta;
	private EditText etNombre, etApellido, etTelefono, etEmail;
	private ListView contactsListView;
	private Usuario usuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent myIntent = getIntent();
		usuario = new Usuario(myIntent.getStringExtra("userName"), myIntent.getStringExtra("userPwd"));
		Log.e("Acceso", String.valueOf(myIntent.getBooleanExtra("acceso", false)));
		Log.e("Modificacion", String.valueOf(myIntent.getBooleanExtra("modificacion", false)));
		Log.e("Usuario", usuario.getNombre() + " " + usuario.getPassword() + " " + usuario.getRol().getRolName());

		buttonBusca = (Button) findViewById(R.id.main_btn_busca);
		buttonBorra = (Button) findViewById(R.id.main_btn_borra);
		buttonAnade = (Button) findViewById(R.id.main_btn_anade);
		buttonReseta = (Button) findViewById(R.id.main_btn_reseta);
		etNombre = (EditText) findViewById(R.id.main_et_nombre);
		etApellido = (EditText) findViewById(R.id.main_et_apellido);
		etTelefono = (EditText) findViewById(R.id.main_et_telefono);
		etEmail = (EditText) findViewById(R.id.main_et_email);
		contactsListView = (ListView) findViewById(R.id.main_lv_contacts);
	}



	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	public void onClick(View v) {

	}
}

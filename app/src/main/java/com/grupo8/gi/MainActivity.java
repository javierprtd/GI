package com.grupo8.gi;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


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

package com.platauto.customadapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customadapter.R;
import com.platauto.adapter.PostAdapter;
import com.platauto.adapter.PostData;

public class MainActivity extends ListActivity {

	static final int DIALOG_CONFIRM = 0;
	protected static final int REQUEST_CODE = 10;

	private PostAdapter adapter;
	private ArrayList<PostData> data;
	private Button btMarkRead;
	
	//190415
	private final String NAMESPACE = "http://www.w3schools.com/webservices/";
	private final String URL = "http://www.w3schools.com/webservices/tempconvert.asmx";
	private final String METHOD_NAME = "CelsiusToFahrenheit";
	/*
	 * private String TAG = "PGGURU";
	*private static String celcius;
	*private static String fahren;
	*Button b;
	*TextView tv;
	*EditText et;
	 */
	
	//190415
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_post_list);
		
		btMarkRead = (Button) findViewById(R.id.btAnular);
		btMarkRead.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				if (adapter.haveSomethingSelected())
					showDialog(DIALOG_CONFIRM);
				else 
					Toast.makeText(getApplicationContext()
							,R.string.no_post_selected, Toast.LENGTH_LONG)
							.show();
			}
		});
		
		Calendar rightnow = Calendar.getInstance();
		String hora = String.valueOf(rightnow.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(rightnow.get(Calendar.MINUTE)) + ":" + String.valueOf(rightnow.get(Calendar.SECOND));
		
		
				
		if (savedInstanceState == null){
			data = new ArrayList<PostData>();
	
			data.add(new PostData("19/09/2012", "Combo 1 c/ refresco, Combo 4 c/ jugo de fresatnhnhtnhtnhtnht t nyttyrhrhrhyr  r hyrhyrhyrhrhyrhyr ryhrhyrhyrhyrytrbtgrgbtrgtrgrgtrhrbgrbtbhtrbhthbrhbrrtgrbhyrbhgrbgfrbgtrhyhrbgrg5hy5hyrgfrg4hy4bgtrbgt4 hy5 " , false));
			data.add(new PostData(hora , "Cmo crear shortcodes en WordPress que soporten parámetros" , false));
			data.add(new PostData("30/09/2012", "Todos los lugares donde deberías habilitar la Autenticación de Dos Factores ahora mismo" , false));
			data.add(new PostData("07/10/2012", "Ocultar archivos dentro de una imagen" , false));
			data.add(new PostData("21/10/2012", "Top 10 de las Mejores Herramientas en la Línea de Comandos" , false));
			data.add(new PostData("28/10/2012", "Enlaces de la semana ( II )" , false));
			data.add(new PostData("04/11/2012", "Nueva Guía: Your Guide to Google Analytics" , false));
			data.add(new PostData("11/11/2012", "Personalizar el Error 404 en wordpress" , false));
			data.add(new PostData("18/11/2012", "Humor gráfico – Informáticos, Programadores, geek… – 9GAG.COM Parte (III)" , false));
			data.add(new PostData("25/11/2012", "Basta con las Tablas Arcoiris: lo que necesitas saber sobre esquemas de contraseñas seguras" , false));
			
			adapter = new PostAdapter(MainActivity.this, data);
		} else{
			data = savedInstanceState.getParcelableArrayList("savedData");
			adapter = new PostAdapter(MainActivity.this, data);
		}
			setListAdapter(adapter);
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		adapter.setCheck(position);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_post_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.cancel_post:
			if (adapter.haveSomethingSelected())
				showDialog(DIALOG_CONFIRM);
			else 
				Toast.makeText(getApplicationContext()
						,R.string.no_post_selected, Toast.LENGTH_LONG)
						.show();
			break;
		case R.id.select_all:
			adapter.checkAll(true);
			adapter.notifyDataSetChanged();
			break;

		case R.id.unselect_all:
			adapter.checkAll(false);
			adapter.notifyDataSetChanged();
			break;
		default:
			break;
		}

		return true;
	}

	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch (id) {
		case DIALOG_CONFIRM:
			dialog = createConfirmDialog();
			break;
		default:
			dialog = null;
		}
		return dialog;
	}

	public Dialog createConfirmDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.dialog_confirm_cancel_post)
				.setCancelable(false)
				.setPositiveButton(R.string.dialog_confirm_cancel_post_yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								adapter.cancelSelectedPost();
							}
						})
				.setNegativeButton(R.string.dialog_confirm_cancel_post_no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		return builder.create();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelableArrayList("savedData", data);
		super.onSaveInstanceState(outState);
	}
}

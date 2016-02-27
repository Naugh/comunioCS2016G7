package grupo6.comunio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends Activity {
    //Atributos
    private Button btnAceptar;
    private Button btnRegistrar;
    private EditText txtUsuario;
    private EditText txtContraseña;
    private ArrayList<Usuario> usuarios;
    private Usuario actual;

    //Metodos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAceptar = (Button)findViewById(R.id.BtnAceptar);
        btnRegistrar = (Button)findViewById(R.id.BtnRegistrar);
        txtUsuario = (EditText)findViewById(R.id.Usuario);
        txtContraseña = (EditText)findViewById(R.id.Contraseña);
        getGlobal();
        //Implementacion del botón de login
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUsuario.getText().toString();
                String password = txtContraseña.getText().toString();
                ListIterator it = usuarios.listIterator();
                while (it.hasNext()) {
                    Usuario usuario = (Usuario) it.next();
                    if ((user.equals(usuario.getNombre())) && (password.equals(usuario.getContraseña()))) {
                        actual = usuario;
                        setGlobal();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        //Iniciar la nueva actividad
                        startActivity(intent);
                    }
                    else {
                            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //Implementacion del botón RegistrarActivity
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
                Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                //Iniciar la nueva actividad
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setTitle("Salir")
                    .setMessage("¿Estás seguro?")
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }
                    })
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //Get Global
    public void getGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();
        // Obtenemos valores desde la clase global
        final ArrayList<Usuario> usuariosG = globalVariable.getUsuarios();
        if(globalVariable.getMercado().isEmpty()){
                globalVariable.initMercado();
        }
        if(usuariosG.isEmpty()){
            globalVariable.initUsuarios();
        }
        //Asignamos las correspondientes variables globales a utilizar en la actividad
        usuarios = usuariosG;
    }
    public void setGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();
        globalVariable.setUsuarioActual(actual);
    }
}

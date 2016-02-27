package grupo6.comunio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrarActivity extends Activity {
    //Atributos
    private Button btnCancelar;
    private Button btnConfirmar;
    private EditText txtUsuario;
    private EditText txtContraseña;
    private EditText txtValida;
    private ArrayList<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        btnCancelar = (Button)findViewById(R.id.BtnCancelar);
        btnConfirmar = (Button)findViewById(R.id.BtnConfirmacion);
        txtUsuario = (EditText)findViewById(R.id.Usuario);
        txtContraseña = (EditText)findViewById(R.id.Contraseña);
        txtValida = (EditText)findViewById(R.id.Validacion);
        getGlobal();
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
                Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                //Iniciar la nueva actividad
                startActivity(intent);
            }
        });
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUsuario.getText().toString();
                String pass = txtContraseña.getText().toString();
                String correct = txtValida.getText().toString();
                if ((!((user.equals(""))||(pass.equals(""))||(correct.equals("")))) && (pass.equals(correct))){
                    Usuario nuevo = new Usuario(user, pass);
                    usuarios.add(nuevo);
                    setGlobal();
                    //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
                    Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                    //Iniciar la nueva actividad
                    Toast.makeText(getApplicationContext(), "Registro con exito", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Campos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar, menu);
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
    //Get & Set Globales
    public void setGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();

        globalVariable.setUsuarios(usuarios);
    }
    public void getGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();

        // Obtenemos valores desde la clase global
        final ArrayList<Usuario> usuariosG = globalVariable.getUsuarios();

        // Asignar los valores obtenidos
        usuarios = usuariosG;
    }
}

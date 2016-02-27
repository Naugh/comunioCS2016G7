package grupo6.comunio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends Activity {
    private TextView txtNombre;
    private TextView txtPuntos;
    private TextView txtDinero;
    private ImageButton btnMercado;
    private ImageButton btnClasificacion;
    private ImageButton btnNoticias;
    private ImageButton btnPlantilla;
    private ImageButton btnPlatilla;
    private Usuario actual;
    private Button btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Localizar los controles
        txtNombre= (TextView)findViewById(R.id.nombre);
        txtPuntos= (TextView)findViewById(R.id.puntos);
        txtDinero= (TextView)findViewById(R.id.dinero);
        btnCerrar = (Button)findViewById(R.id.salir);
        getGlobal();
        //Construimos el mensaje a mostrar
        txtNombre.setText(actual.getNombre());
        txtPuntos.setText(Integer.toString(actual.getPuntos()) + " Puntos");
        txtDinero.setText(Integer.toString(actual.getDinero()) + " Euros");
        btnMercado = (ImageButton)findViewById(R.id.BtnMercado);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                //Iniciar la nueva actividad
                startActivity(intent);
                finish();
            }
        });
        btnMercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
                Intent intent = new Intent(HomeActivity.this, MercadoActivity.class);
                //Iniciar la nueva actividad
                startActivity(intent);
            }
        });
        btnClasificacion = (ImageButton)findViewById(R.id.BtnClasificacion);
        btnClasificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
                Intent intent = new Intent(HomeActivity.this, ClasificacionActivity.class);
                //Iniciar la nueva actividad
                startActivity(intent);
            }
        });
        btnNoticias = (ImageButton)findViewById(R.id.BtnNoticias);
        btnNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
                Intent intent = new Intent(HomeActivity.this, NoticiasActivity.class);
                //Iniciar la nueva actividad
                startActivity(intent);
            }
        });
        btnPlantilla = (ImageButton)findViewById(R.id.BtnPlantilla);

        btnPlatilla = (ImageButton)findViewById(R.id.BtnPlantilla);
        btnPlatilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
                Intent intent = new Intent(HomeActivity.this, PlantillaActivity.class);
                //Iniciar la nueva actividad
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        return false;
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
    public void getGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();

        // Obtenemos valores desde la clase global
        final Usuario actualG = globalVariable.getUsuarioActual();

        //Asignamos las correspondientes variables globales a utilizar en la actividad
        actual = actualG;
    }
}

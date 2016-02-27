package grupo6.comunio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MercadoActivity extends Activity {
    private ListView lstJugadores;
    private TextView txtElegida;
    private ArrayList<Jugador> mercado;
    private Button btnFichar;
    private Usuario actual;
    private Jugador fichaje;
    private int dineroJugador;
    private boolean seleccionado;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado);
        getGlobal();
        btnFichar = (Button)findViewById(R.id.btnFichar);
        AdaptadorJugadores adaptador = new AdaptadorJugadores(this, mercado);
        lstJugadores = (ListView) findViewById(R.id.lstJugadores);
        View header = getLayoutInflater().inflate(R.layout.list_mercado_header, null);
        lstJugadores.addHeaderView(header);
        lstJugadores.setAdapter(adaptador);
        btnFichar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dineroUsuario = actual.getDinero();
                if(dineroUsuario >= dineroJugador){
                    if(seleccionado){
                        mercado.remove(fichaje);
                        if(actual.getPlantilla().getJugadoresTitulares().size() == 11){
                            actual.getPlantilla().getJugadoresSuplentes().add(fichaje);
                        }
                        else{
                            actual.getPlantilla().getJugadoresTitulares().add(fichaje);
                        }
                        actual.setDinero(dineroUsuario-dineroJugador);
                        setGlobal();
                        finish();
                        startActivity(getIntent());
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "No tienes suficientes fondos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lstJugadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adaptador, View v, int position, long id) {
                txtElegida = (TextView)findViewById(R.id.elegir);
                String opcionSeleccionada = ((Jugador) adaptador.getItemAtPosition(position)).getNombre();
                if(!(opcionSeleccionada.isEmpty())){
                    seleccionado = true;
                }
                else{
                    seleccionado = false;
                }
                fichaje = ((Jugador) adaptador.getItemAtPosition(position));
                dineroJugador = ((Jugador) adaptador.getItemAtPosition(position)).getValor();
                txtElegida.setText(opcionSeleccionada);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mercado, menu);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
        Intent intent = new Intent(MercadoActivity.this, HomeActivity.class);
        //Iniciar la nueva actividad
        startActivity(intent);
        return false;
    }
    @Override
    public void onPause(){
        super.onPause();
        finish();
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
    class AdaptadorJugadores extends ArrayAdapter<Jugador> {
        public AdaptadorJugadores(Context context, ArrayList<Jugador> datos) {
            super(context, R.layout.listitem_jugador, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_jugador, null);
            TextView Nombre = (TextView)item.findViewById(R.id.nombre);
            Nombre.setText(mercado.get(position).getNombre());
            TextView Equipo = (TextView)item.findViewById(R.id.equipo);
            Equipo.setText(mercado.get(position).getEquipo());
            TextView Posicion = (TextView)item.findViewById(R.id.posicion);
            Posicion.setText(mercado.get(position).getPosicion());
            TextView Valor = (TextView)item.findViewById(R.id.valor);
            Valor.setText(Integer.toString(mercado.get(position).getValor()) + " Euros");
            return(item);
        }
    }
    //Get Global
    public void getGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();
        // Obtenemos valores desde la clase global
        final ArrayList<Jugador> mercadoG = globalVariable.getMercado();
        final Usuario actualG = globalVariable.getUsuarioActual();
        //Asignamos las correspondientes variables globales a utilizar en la actividad
        mercado = mercadoG;
        actual = actualG;
    }
    public void setGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();
        globalVariable.setMercado(mercado);
        globalVariable.setUsuarioActual(actual);
    }
}


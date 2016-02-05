package grupo6.comunio;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
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

import java.util.ArrayList;
import java.util.List;

public class PlantillaActivity extends Activity {

    private ListView lstJugadores;
    private Equipo equipo;
    private ArrayList<Jugador> jugadores;
    private Button button_CambASupl;
    private Button button_VerBanq;
    private Button vender;
    private Jugador jugOficial;
    private TextView txtElegida;
    private Usuario actual;
    private ArrayList<Jugador> mercado;
    private boolean seleccionado;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantilla);
        getGlobal();
        button_VerBanq = (Button) findViewById(R.id.button_VerBanq);
        button_CambASupl = (Button) findViewById(R.id.button_CambSupl);
        vender = (Button) findViewById(R.id.vender);
        jugadores = equipo.getJugadoresTitulares();
        AdaptadorJugadores adaptador = new AdaptadorJugadores(this, jugadores);
        lstJugadores = (ListView)findViewById(R.id.lstJugadores);
        lstJugadores.setAdapter(adaptador);

        button_CambASupl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seleccionado) {
                    actual.getPlantilla().getJugadoresSuplentes().add(jugOficial);
                    actual.getPlantilla().getJugadoresTitulares().remove(jugOficial);
                    setGlobal();
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seleccionado) {
                    mercado.add(jugOficial);
                    actual.getPlantilla().getJugadoresTitulares().remove(jugOficial);
                    setGlobal();
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        lstJugadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adaptador, View v, int position, long id) {
                txtElegida = (TextView) findViewById(R.id.elegir);
                String opcionSeleccionada = ((Jugador) adaptador.getItemAtPosition(position)).getNombre();
                if(!(opcionSeleccionada.isEmpty())){
                    seleccionado = true;
                }
                else {
                    seleccionado = false;
                }
                jugOficial = ((Jugador) adaptador.getItemAtPosition(position));
                txtElegida.setText(opcionSeleccionada);
            }
        });

        button_VerBanq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantillaActivity.this, Suplentes.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onPause(){
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plantilla, menu);
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
    class AdaptadorJugadores extends ArrayAdapter<Jugador> {
        public AdaptadorJugadores (Context context, ArrayList<Jugador> jugadores) {
            super(context, R.layout.listitem_jugador, jugadores);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_jugador, null);
            TextView Nombre = (TextView)item.findViewById(R.id.nombre);
            Nombre.setText(jugadores.get(position).getNombre());
            TextView Equipo = (TextView)item.findViewById(R.id.equipo);//Identifica la etiqueta que cojo del listitem
            Equipo.setText((jugadores.get(position).getEquipo())); //Cambiar etiqueta por Club
            TextView Posicion = (TextView)item.findViewById(R.id.posicion);//Identifica la etiqueta que cojo del listitem
            Posicion.setText(jugadores.get(position).getPosicion());
            TextView Dinero = (TextView)item.findViewById(R.id.valor);//Identifica la etiqueta que cojo del listitem
            Dinero.setText(Integer.toString(jugadores.get(position).getValor()));
            return(item);
        }
    }



    public void getGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();

        // Obtenemos valores desde la clase global
        final Equipo actualPl = globalVariable.getUsuarioActual().getPlantilla();
        final Usuario actualG = globalVariable.getUsuarioActual();
        final ArrayList<Jugador> mercadoG = globalVariable.getMercado();
        //Asignamos las correspondientes variables globales a utilizar en la actividad
        equipo = actualPl;
        actual = actualG;
        mercado = mercadoG;
    }
    public void setGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();
        globalVariable.setUsuarioActual(actual);
        globalVariable.setMercado(mercado);
    }


}




package grupo6.comunio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClasificacionActivity extends Activity {
    private ListView lstUsuarios;
    private ArrayList<Usuario> usuarios;
    private Button button_actClas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);
        getGlobal();
        button_actClas = (Button) findViewById(R.id.btn_actClas);
        AdaptadorUsuarios adaptador = new AdaptadorUsuarios(this, usuarios);
        lstUsuarios = (ListView)findViewById(R.id.lstUsuarios);
        lstUsuarios.setAdapter(adaptador);

        button_actClas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<usuarios.size();i++) {
                    usuarios.get(i).setPuntos(usuarios.get(i).getPuntos() + (20 - i));
                }
                setGlobal();
                finish();
                startActivity(getIntent());
            }
        });
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

    class AdaptadorUsuarios extends ArrayAdapter<Usuario> {
        public AdaptadorUsuarios (Context context, ArrayList<Usuario> usuarios) {
            super(context, R.layout.listitem_usuario, usuarios);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_usuario, null);
            TextView Nombre = (TextView)item.findViewById(R.id.nombre);
            Nombre.setText(usuarios.get(position).getNombre());
            TextView Puntos = (TextView)item.findViewById(R.id.puntos);//Identifica la etiqueta que cojo del listitem
            Puntos.setText(Integer.toString(usuarios.get(position).getPuntos()) + " puntos");
            TextView Dinero = (TextView)item.findViewById(R.id.dinero);//Identifica la etiqueta que cojo del listitem
            Dinero.setText(Integer.toString(usuarios.get(position).getDinero()) + "  euros");
            return(item);
        }
    }

    public void getGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();

        // Obtenemos valores desde la clase global
        final ArrayList<Usuario> actualG = globalVariable.getUsuarios();

        //Asignamos las correspondientes variables globales a utilizar en la actividad
        usuarios = actualG;
    }
    public void setGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();
        globalVariable.setUsuarios(usuarios);
    }
}
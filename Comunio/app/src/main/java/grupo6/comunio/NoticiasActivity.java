package grupo6.comunio;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NoticiasActivity extends Activity {

    private EditText etNoticia;
    private TextView tvNoticias;
    private Button bPublicar;
    private Button bNoticias2;
    private String porDefecto = "Escribe aquí tu comentario...";
    private Usuario actual;
    private String comentarios, dia, mes, año, hora, minutos;
    private Calendar fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        getGlobal();
        bNoticias2 = (Button) findViewById(R.id.btOficial);
        tvNoticias = (TextView) findViewById(R.id.tvNoticias);
        if(comentarios.equals("")){
            comentarios = "Se el primero en comentar";
            tvNoticias.setText(comentarios);
            comentarios = "";
        }
        else {
            tvNoticias.setText(comentarios);
        }
        etNoticia = (EditText) findViewById(R.id.etNoticia);
        bPublicar = (Button) findViewById(R.id.btPublicar);
        tvNoticias.setMovementMethod(new ScrollingMovementMethod());
        bPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Los if son debidos a la conversion extraña que hace la funcion Integer.toString
                fecha = new GregorianCalendar();
                dia = Integer.toString(fecha.get(Calendar.DATE));
                mes = Integer.toString(fecha.get(Calendar.MONTH) + 1);
                if (mes.equals(13)) {
                    mes = "1";
                }
                año = Integer.toString(fecha.get(Calendar.YEAR));
                hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY) + 1);
                if (hora.equals("24")) {
                    hora = "00";
                }
                minutos = Integer.toString(fecha.get(Calendar.MINUTE));
                if (minutos.equals("1") || minutos.equals("2") || minutos.equals("3") || minutos.equals("4") || minutos.equals("5") || minutos.equals("6")
                        || minutos.equals("7") || minutos.equals("8") || minutos.equals("9")) {
                    minutos = "0" + minutos;
                }
                String noticia = etNoticia.getText().toString() + "\n- " + actual.getNombre() + " el "
                        + dia + "/" + mes + "/" + año + " a las " + hora + ":" + minutos + "\n\n";
                comentarios = noticia + comentarios;
                tvNoticias.setText(comentarios);
                etNoticia.setText(porDefecto);
                setGlobal();
            }
        });
        etNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNoticia.setText("");
            }
        });
        bNoticias2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticiasActivity.this, Noticias2Activity.class);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction()== KeyEvent.ACTION_DOWN) {
            //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
            Intent intent = new Intent(NoticiasActivity.this, HomeActivity.class);
            //Iniciar la nueva actividad
            startActivity(intent);
        }
        return false;
    }
    public void getGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();

        // Obtenemos valores desde la clase global
        final Usuario actualG = globalVariable.getUsuarioActual();
        final String comentariosG = globalVariable.getComentarios();

        //Asignamos las correspondientes variables globales a utilizar en la actividad
        actual = actualG;
        comentarios = comentariosG;
    }
    public void setGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();
        globalVariable.setComentarios(comentarios);
    }
}

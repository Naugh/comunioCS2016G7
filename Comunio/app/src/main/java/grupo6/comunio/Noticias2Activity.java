package grupo6.comunio;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Noticias2Activity extends Activity {

    private TextView tvNoticias2;
    private String noticiasOficiales;
    private Button bNoticias1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias2);
        getGlobal();
        tvNoticias2 = (TextView) findViewById(R.id.tvNoticias2);
        tvNoticias2.setText(noticiasOficiales);
        bNoticias1 = (Button) findViewById(R.id.btNoticias1);
        bNoticias1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Noticias2Activity.this, NoticiasActivity.class);
                startActivity(intent);
            }
        });

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction()== KeyEvent.ACTION_DOWN) {
            //Creamos el Intent, registrar_Activity es el nombre de la ventana a abrir
            Intent intent = new Intent(Noticias2Activity.this,NoticiasActivity.class);
            //Iniciar la nueva actividad
            startActivity(intent);
        }
        return false;
    }

    public void getGlobal() {
        final ValoresGlobales globalVariable = (ValoresGlobales) getApplicationContext();

        // Obtenemos valores desde la clase global
        final Usuario actualG = globalVariable.getUsuarioActual();
        final String noticiasOficiales = globalVariable.getNoticiasOficiales();

        //Asignamos las correspondientes variables globales a utilizar en la actividad
       this.noticiasOficiales = noticiasOficiales;
    }
    public void onPause(){
        super.onPause();
        finish();
    }

}

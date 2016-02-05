package grupo6.comunio;

import android.app.Application;
import java.util.ArrayList;

public class Equipo extends Application {

    //Atributos
    private ArrayList<Jugador> titulares;
    private ArrayList<Jugador> suplentes;

    //Constructores
    //Crear equipo inicial por defecto
    public Equipo(){
        titulares = new ArrayList<Jugador>();
        suplentes = new ArrayList<Jugador>();
    }

    //Get & Set
    public ArrayList<Jugador> getJugadoresTitulares() {
        return titulares;
    }
    public void setJugadoresTitulares(ArrayList<Jugador> jugadores) {
        this.titulares = jugadores;
    }
    public ArrayList<Jugador> getJugadoresSuplentes() {
        return suplentes;
    }
    public void setJugadoresSuplentes(ArrayList<Jugador> jugadores) {
        this.suplentes = jugadores;
    }
}

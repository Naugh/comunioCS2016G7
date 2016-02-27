package grupo6.comunio;

import android.app.Application;

public  class Jugador extends Application {

    //Atributos
    private String nombre;
    private String posicion;
    private String equipo;
    private int valor;
    private int ultimaPuntuacion;
    private int mediap;

    //Constructores
    public Jugador(String nombre, String posicion, String equipo, int valor, int ultimaPuntuacion, int mediap){
        this.nombre = nombre;
        this.posicion = posicion;
        this.equipo = equipo;
        this.valor = valor;
        this.ultimaPuntuacion = ultimaPuntuacion;
        this.mediap = mediap;
    }
    public Jugador(){
        this.nombre ="";
        this.posicion = "";
        this.equipo = "";
        this.valor = 0;
        this.ultimaPuntuacion = 0;
        this.mediap = 0;
    }

    //Get & Set
    public String getNombre(){
        return this.nombre;
    }
    public String getPosicion(){
        return this.posicion;
    }
    public String getEquipo(){
        return this.equipo;
    }
    public int getValor(){
        return this.valor;
    }
    public int getPuntuacion(){
        return this.ultimaPuntuacion;
    }
    public int getMediap(){
        return this.mediap;
    }
    public void setEquipo(String equipo){
        this.equipo = equipo;
    }
    public void setultimaPuntuacion(int ultimaPuntuacion){
        this.ultimaPuntuacion = ultimaPuntuacion;
        actMediap(ultimaPuntuacion);
    }

    //Metodos
    public void actMediap(int ultimaPuntuacion){
        this.mediap = (getMediap()+ultimaPuntuacion)/2;
    }
}


package grupo6.comunio;

import android.app.Application;

public  class Usuario extends Application {

    //Atributos
    private String nombre;
    private String pass;
    private int dinero;
    private int puntos;
    private Equipo plantilla;

    //Constructores
    public Usuario(String name, int dinero, int puntos,String pass, Equipo plantilla){  //Usuario prueba plantilla
        this.nombre = name;
        this.pass = pass;
        this.dinero = dinero;
        this.puntos = puntos;
        this.plantilla = plantilla;
    }
    public Usuario(String name, String pass){  //Nuevo usuario por defecto
        this.nombre = name;
        this.pass = pass;
        this.dinero = 300000000; //300 Millones de dinero inicial
        this.puntos = 0; //0 Puntos en un inicio
        this.plantilla = new Equipo();
    }

    //Get & Set
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContraseña(){
        return pass;
    }
    public void setContraseña(String pass){
        this.pass = pass;
    }
    public int getDinero() {
        return dinero;
    }
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public Equipo getPlantilla() {
        return plantilla;
    }
    public void setPlantilla(Equipo plantilla) {
        this.plantilla = plantilla;
    }
}

package grupo6.comunio;


import android.app.Application;
import java.util.ArrayList;

public class ValoresGlobales extends Application {

    //Atributos
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private ArrayList<Jugador> mercado = new ArrayList<Jugador>();
    private Usuario usuarioActual = new Usuario("admin",1000000000, 100, "123", new Equipo());
    private String comentarios = "";
    private String noticiasOficiales = "La temporada empieza en breves, ¡preparad vuestros equipos y suerte! \n\n Hemos añadido nuevos jugadores en el mercado.";
    //Inicializar mercado
    public void initUsuarios(){
        usuarios.add(usuarioActual);
    }
    public void initMercado(){
        Jugador jug1 = new Jugador("C.Ronaldo", "Delantero", "Real Madrid", 96000000, 0, 0);
        Jugador jug2 = new Jugador("Messi", "Delantero", "Barcelona", 90000000, 0, 0);
        Jugador jug3 = new Jugador("Sergio Ramos", "Defensa", "Real Madrid", 60000000, 0, 0);
        Jugador jug4 = new Jugador("Never", "Portero", "Bayern de Munich", 30000000, 0, 0);
        Jugador jug5 = new Jugador("Claudio Bravo", "Portero", "Barcelona", 15000000, 0, 0);
        Jugador jug6 = new Jugador("Isco", "Mediocentro", "Real Madrid", 50000000, 0, 0);
        Jugador jug7 = new Jugador("Iniesta", "Mediocentro", "Barcelona", 75000000, 0, 0);
        Jugador jug8 = new Jugador("Griezman", "Delantero", "Atletico de Madrid", 70000000, 0, 0);
        Jugador jug9 = new Jugador("Benzema", "Delantero", "Real Madrid", 60000000, 0, 0);
        Jugador jug10 = new Jugador("Hazard", "Mediocentro", "Chelsea", 80000000, 0, 0);
        Jugador jug11 = new Jugador("Negredo", "Delantero", "Valencia", 20000000, 0, 0);
        Jugador jug12 = new Jugador("Valeron", "Mediocentro", "Las Palmas", 40000000, 0, 0);
        Jugador jug13 = new Jugador("James", "Mediocentro", "Real Madrid", 70000000, 0, 0);
        Jugador jug14 = new Jugador("Lucas Perez", "Delantero", "Deportivo", 10000000, 0, 0);
        Jugador jug15 = new Jugador("Llorente", "Delantero", "Sevilla", 30000000, 0, 0);
        Jugador jug16 = new Jugador("Vitolo", "Mediocentro", "Sevilla", 15000000, 0, 0);
        Jugador jug17 = new Jugador("Gabi", "Mediocentro", "Atletico de Madrid", 10000000, 0, 0);
        Jugador jug18 = new Jugador("Nolito", "Delantero", "Celta", 40000000, 0, 0);
        Jugador jug19 = new Jugador("Danilo", "Defensa", "Real Madrid", 35000000, 0, 0);
        Jugador jug20 = new Jugador("De Gea", "Portero", "Manchester United", 50000000, 0, 0);
        mercado.add(jug1);
        mercado.add(jug2);
        mercado.add(jug3);
        mercado.add(jug4);
        mercado.add(jug5);
        mercado.add(jug6);
        mercado.add(jug7);
        mercado.add(jug8);
        mercado.add(jug9);
        mercado.add(jug10);
        mercado.add(jug11);
        mercado.add(jug12);
        mercado.add(jug13);
        mercado.add(jug14);
        mercado.add(jug15);
        mercado.add(jug16);
        mercado.add(jug17);
        mercado.add(jug18);
        mercado.add(jug19);
        mercado.add(jug20);
    }
    //Get & Set
    public ArrayList<Usuario> getUsuarios(){
        return this.usuarios;
    }
    public void setUsuarios(ArrayList<Usuario> user){
        this.usuarios = user;
    }
    public ArrayList<Jugador> getMercado(){
        return this.mercado;
    }
    public void setMercado(ArrayList<Jugador> mercado){
        this.mercado = mercado;
    }
    public Usuario getUsuarioActual(){
        return this.usuarioActual;
    }
    public void setUsuarioActual(Usuario actual){
        this.usuarioActual = actual;
    }
    public String getComentarios(){
        return this.comentarios;
    }
    public void setComentarios(String com){
        this.comentarios = com;
    }
    public String getNoticiasOficiales (){return noticiasOficiales;}
    public void setNoticiasOficiales (String noticiasOficiales){this.noticiasOficiales =noticiasOficiales;}
}

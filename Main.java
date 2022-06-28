import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        catalogo.obtenerDatos();
        System.out.println(catalogo.getMasBuscado( "viajes", 2)); //Servicio 1
        System.out.println(catalogo.getBuscados("tecnología")); // Servicio 2
        GrafoGeneros grafo = catalogo.getCicloGenero("viaje"); // Servicio 3
    }
}

//Obtener el grafo únicamente con los géneros afines a un género A; es decir que,
//partiendo del género A, se consiguió una vinculación cerrada entre uno o más
//géneros que permitió volver al género de inicio.//

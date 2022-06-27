import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        catalogo.obtenerDatos();
        System.out.println(catalogo.getMasBuscado( "viajes", 3)); //Servicio 1
     //   catalogo.getBuscados("genero"); // Servicio 2
     //   GrafoGeneros grafo = catalogo.getCicloGenero("genero"); // Servicio 3
    }
}

//Obtener los N géneros más buscados luego de buscar por el género A.

//Obtener todos los géneros que fueron buscados luego de buscar por el género A.

//Obtener el grafo únicamente con los géneros afines a un género A; es decir que,
//partiendo del género A, se consiguió una vinculación cerrada entre uno o más
//géneros que permitió volver al género de inicio.//

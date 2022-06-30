import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        //Timer t = new Timer();
        //t.start();
        catalogo.obtenerDatos();
        //System.out.println(catalogo);
        System.out.println(catalogo.getMasBuscado( "cine", 4)); //Servicio 1
        //System.out.println(catalogo.caminoMayorPeso("cine"));
        //GrafoGeneros grafo = catalogo.getCicloGenero("viajes"); // Servicio 3
        //System.out.println(grafo);
        //System.out.println("Tiempo de demora: " + t.stop());
        System.out.println(catalogo.getCantidad_iteraciones());
    }
}

//Obtener el grafo únicamente con los géneros afines a un género A; es decir que,
//partiendo del género A, se consiguió una vinculación cerrada entre uno o más
//géneros que permitió volver al género de inicio.//

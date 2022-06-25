import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoGeneros {

    private HashMap<String, ArrayList<Genero>> generos;

    public GrafoGeneros() {
        generos = new HashMap<>();
    }

    public void addVertice(String nombre){
        if(!generos.containsKey(nombre)){
            generos.put(nombre, new ArrayList<Genero>());
        }
    }

    public void borrarVertice(String nombre) {
        for(String i : generos.keySet()){
            for(int j=0 ; j< generos.get(i).size(); j++){
                if(generos.get(i).get(j).getNombre() == nombre)
                    generos.get(i).remove(j);
            }
        }
        generos.remove(nombre);
    }

    public void agregarArco(String nombreOrigen, String nombreDestino) {
        if(generos.containsKey(nombreOrigen) && generos.containsKey(nombreDestino))
            generos.get(nombreOrigen).add(new Genero(nombreOrigen,1));
    }

    public void borrarArco(String nombreOrigen, String nombreDestino) {
        if(generos.containsKey(nombreOrigen)){
            for(int i =0; i < generos.get(nombreOrigen).size(); i++){
                if(generos.get(nombreOrigen).get(i).getNombre() == nombreDestino)
                    generos.get(nombreOrigen).remove(i);
            }
        }
    }

    public boolean contieneVertice(String nombre) {
        if(! generos.isEmpty())
            return generos.containsKey(nombre);
        else return false;
    }

    public boolean existeArco(String nombreOrigen, String nombreDestino) {
        if ((!generos.isEmpty()) && (generos.containsKey(nombreOrigen))) {
            for (int i = 0; i < generos.get(nombreOrigen).size(); i++) {
                if (generos.get(nombreOrigen).get(i).getNombre() == nombreDestino)
                    return true;
            }
            return false;
        } else return false;
    }

    public int cantidadVertices() {
        return generos.size();
    }

    public int cantidadArcos() {
        int contador = 0;
        for (String i : generos.keySet()) {
            contador += generos.get(i).size();
        }
        return contador;
    }

    public Iterator<String> obtenerVertices() {
        Iterator<String> it = generos.keySet().iterator();
        return it;
    }

    public Iterator<String> obtenerAdyacentes(String nombre) {
        ArrayList<String>lista_ady = new ArrayList<>();
        for (int i = 0; i < generos.get(nombre).size(); i++) {
            lista_ady.add(generos.get(nombre).get(i).getNombre());
        }
        Iterator<String> it = lista_ady.iterator();
        return it;
    }

//   public Iterator<Arco<T>> obtenerArcos() {
//        ArrayList<Arco<T>> lista_arcos = new ArrayList<>();
//        for (int i : grafo.keySet()) {
//            for(int j=0; j < grafo.get(i).size(); j++)
//                lista_arcos.add(grafo.get(i).get(j));
//        }
//        Iterator<Arco<T>> it = lista_arcos.iterator();
//        return it;
//    }
//
//
//    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
//        ArrayList<Arco<T>> lista_arcos = new ArrayList<>();
//        for(int j=0; j < grafo.get(verticeId).size(); j++)
//            lista_arcos.add(grafo.get(verticeId).get(j));
//        Iterator<Arco<T>> it = lista_arcos.iterator();
//        return it;
//    }

}

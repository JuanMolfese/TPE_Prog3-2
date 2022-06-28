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

    //Se implementa existe arco con una modificacion, donde retorna (en caso de existir) la posicion del elemento.
    public void agregarArco(String nombreOrigen, String nombreDestino) {
        int indice = 0;
        //if(generos.containsKey(nombreOrigen) && generos.containsKey(nombreDestino)){
            indice= existeArco(nombreOrigen,nombreDestino);
            if (indice != -1){
                generos.get(nombreOrigen).get(indice).setValorBusqueda();
            }else{
                generos.get(nombreOrigen).add(new Genero(nombreDestino,1));
            }
      //  }
    }

    public boolean contieneVertice(String nombre) {
        if(! generos.isEmpty())
            return generos.containsKey(nombre);
        else return false;
    }

    public int existeArco(String nombreOrigen, String nombreDestino) {
        if ((!this.generos.isEmpty()) && (this.generos.containsKey(nombreOrigen))) {
            for (int i = 0; i < this.generos.get(nombreOrigen).size(); i++) {
                if (this.generos.get(nombreOrigen).get(i).getNombre().equals(nombreDestino))
                    return i;
            }
            return -1;
        } else return -1;
    }

    public int cantidadVertices() {
        return this.generos.size();
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

    public Iterator<Genero> itobtenerAdyacentes(String nombre) {
        ArrayList<Genero>lista_ady = new ArrayList<>();
        for (int i = 0; i < generos.get(nombre).size(); i++) {
            lista_ady.add(generos.get(nombre).get(i));
        }
        Iterator<Genero> it = lista_ady.iterator();
        return it;
    }

    public ArrayList<Genero> obtenerAdyacentes(String nombre) {
        ArrayList<Genero>lista_ady = new ArrayList<>();
        for (int i = 0; i < this.generos.get(nombre).size(); i++) {
            lista_ady.add(this.generos.get(nombre).get(i));
        }
        return lista_ady;
    }

    //TODO
    public GrafoGeneros buscarCiclos(String generos) {
        GrafoGeneros respuesta = new GrafoGeneros();

        if(){
            //buscar el genero en el grafo
            //comprobar si es inicio y fin en un recorrido (ciclo)
            //si encuentra uno lo retorno
            //Si esta mas veces en el archivo ... seguir recorriendo buscando mas ciclos,
            //donde en genero es el incio y fin ???
        }else{
        return respuesta;
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


    @Override
    public String toString() {
        String item="";
        for(String e : generos.keySet()){
          item += e;
          item += generos.get(e).toString();
          item += " // ";
        }
        return item;
    }
}

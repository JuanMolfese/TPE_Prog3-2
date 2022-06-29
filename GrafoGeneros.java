import java.lang.reflect.Array;
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

    public void setArco(String nombreOrigen, Genero destino) {
        int indice = 0;
        //if(generos.containsKey(nombreOrigen) && generos.containsKey(nombreDestino)){
        indice= existeArco(nombreOrigen, destino.getNombre());
        if (indice != -1){
            generos.get(nombreOrigen).get(indice).setValorBusqueda(destino.getValorBusqueda());
        }
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

    /*
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
    }

    DFS(Grafo G)
        por cada vertice en G
            V.color = blanco
        Por cada V en G
            si v.color = blanco
                DFS_VISIT(blanco)
   DFS_VISIT(Vertice v)
        v.color = amarillo
        por cada A de V
            if (color = blanco)
                DFS_VISIT(ady)
            if color = amarillo
                ciclo
        v.color negro


    */

    public GrafoGeneros buscarCiclos(String genero){
        if (!this.generos.containsKey(genero)) return null;
        GrafoGeneros ciclo = new GrafoGeneros();
        HashMap<String, String> colores = new HashMap<>();
        ArrayList<Genero> caminoParcial = new ArrayList<>();
        for (String keyGenero: this.generos.keySet()) {
            colores.put(keyGenero, "blanco");
        }
        colores.replace(genero, "amarillo");
        for (Genero g: this.generos.get(genero)) {
            if (colores.get(g.getNombre()).equals("blanco")) {
                caminoParcial.addAll(buscarCiclos_visit(g, colores, caminoParcial));
            }
        }
        if (!caminoParcial.isEmpty()) {
            ciclo.addVertice(genero);
            for (Genero g: caminoParcial) {
                ciclo.agregarArco(genero, g.getNombre());
                ciclo.setArco(genero, g);
            }
        }
        return ciclo;
    }

    private ArrayList<Genero> buscarCiclos_visit(Genero g, HashMap<String, String> colores, ArrayList<Genero> caminoParcial){
        colores.replace(g.getNombre(), "amarillo");
        caminoParcial.add(g);
        boolean encontro = false;
        int i = 0;
        //se agrega
        Genero ady;
        while (!encontro && i < this.generos.get(g.getNombre()).size()) {
            ady = this.generos.get(g.getNombre()).get(i);
            i++;
        //for (Genero ady: this.generos.get(g.getNombre())) {
            if (colores.get(ady.getNombre()).equals("blanco")) {
                caminoParcial.addAll(buscarCiclos_visit(ady, colores, caminoParcial));
            } else if (colores.get(ady.getNombre()).equals("amarillo")){
                encontro = true;
            }
        }
        if (!encontro) caminoParcial.remove(g);
        return caminoParcial;
    }

    public ArrayList<String> caminoMayorPeso(String genero){
        ArrayList<String> solucion = new ArrayList<>();
        String tmp = genero;
        solucion.clear();
        solucion.add(tmp);
        while (!this.generos.get(tmp).isEmpty() && notNewAyacente(tmp, solucion)) {
            tmp = generoMayorPeso(this.generos.get(tmp)); // se queda con el genero de mayor peso
            solucion.add(tmp);
        }
        return solucion;
    }

    private boolean notNewAyacente (String genero, ArrayList<String> solucion){
        for (Genero g: this.generos.get(genero)) {
            if (!solucion.contains(g.getNombre())){
                return true;
            }
        }
        return false;
    }

    private String generoMayorPeso(ArrayList<Genero> adyacentes){
        Genero g = new Genero("tmp",0);
        for (Genero i: adyacentes) {
            if (g.getValorBusqueda() < i.getValorBusqueda()){
                g = i;
            }
        }
        return g.getNombre();
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
          item += " \n ";
        }
        return item;
    }
}

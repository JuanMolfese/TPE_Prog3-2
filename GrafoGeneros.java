import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoGeneros {

    private HashMap<String, ArrayList<Genero>> generos;

    private HashMap<String, String> colores;
    private ArrayList<Genero> caminoParcial;
    private ArrayList<String> solucion;
    private Genero origen;
    private int iteraciones;

    public GrafoGeneros() {
        generos = new HashMap<>();
        colores = new HashMap<>();
        caminoParcial = new ArrayList<>();
        solucion = new ArrayList<>();
        iteraciones = 0;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public void addVertice(String nombre){
        if(!generos.containsKey(nombre)){
            generos.put(nombre, new ArrayList<Genero>());
        }
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

    public ArrayList<Genero> obtenerAdyacentes(String nombre) {
        ArrayList<Genero>lista_ady = new ArrayList<>();
        for (int i = 0; i < this.generos.get(nombre).size(); i++) {
            lista_ady.add(this.generos.get(nombre).get(i));
        }
        return lista_ady;
    }

    public GrafoGeneros buscarCiclos(String genero){
        this.iteraciones = 0;
        if (!this.generos.containsKey(genero)) return null;
        for (String keyGenero: this.generos.keySet()) {
            colores.put(keyGenero, "blanco");
        }
        GrafoGeneros ciclo = new GrafoGeneros();
        origen = new Genero(genero, 0);
        buscarCiclos_visit(origen, ciclo);
        for (String g: ciclo.generos.keySet()) {
            ArrayList<Genero> ady = generos.get(g);
            for (Genero a: ady){
                if (ciclo.contieneVertice(a.getNombre())){
                    ciclo.agregarArco(g, a.getNombre());
                    ciclo.setArco(g, a);
                }
            }
        }
        return ciclo;
    }

    private void buscarCiclos_visit(Genero g, GrafoGeneros ciclo){
        colores.replace(g.getNombre(), "amarillo");
        caminoParcial.add(g);
        for (Genero ady: this.generos.get(g.getNombre())) {
            if (colores.get(ady.getNombre()).equals("blanco")) {
                iteraciones++;
                buscarCiclos_visit(ady, ciclo);
            } else if (colores.get(ady.getNombre()).equals("amarillo") && ady.getNombre().equals(origen.getNombre())){
                caminoParcial.add(ady);
                if (!ciclo.contieneVertice(ady.getNombre())){
                    //ciclo.addVertice(g.getNombre());
                    for (Genero act: caminoParcial) {
                        ciclo.addVertice(act.getNombre());
                    }

                }
            }
        }
        colores.replace(g.getNombre(), "negro");
        caminoParcial.remove(g);
    }

    public ArrayList<String> caminoMayorPeso(String genero){
        //ArrayList<String> solucion = new ArrayList<>();
        iteraciones = 0;
        String tmp = genero;
        solucion.clear();
        solucion.add(tmp);
        while (!this.generos.get(tmp).isEmpty() && notNewAyacente(tmp) && tmp != null) {
            tmp = generoMayorPeso(this.generos.get(tmp)); // se queda con el genero de mayor peso
            solucion.add(tmp);
            iteraciones++;
        }
        return solucion;
    }

    private boolean notNewAyacente (String genero){
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
            if (!solucion.contains(i.getNombre())) {
                if (g.getValorBusqueda() < i.getValorBusqueda()) {
                    g = i;
                    iteraciones++;
                }
            }
        }
        if (g.getNombre().equals("tmp")) return null;
        return g.getNombre();
    }

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

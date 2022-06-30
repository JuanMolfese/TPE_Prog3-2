import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Catalogo {

    private GrafoGeneros grafo_generos;
    private int cantidad_iteraciones;

    public Catalogo(){
        this.grafo_generos = new GrafoGeneros();
        this.cantidad_iteraciones = 0;
    }

    public int getCantidad_iteraciones(){
        return this.cantidad_iteraciones;
    }
     /**
     * Genera un grafo de generos extraidos de un archivo
     * .csv donde cada vertice tiene el nombre del genero y
     * una referencia a la lista de los generos que fueron buscados
     * luego de este vertice. Ademas contiene la cantidad de busquedas
     * repetidas.
     * */

    public void obtenerDatos(){

        String csvFile = "dataset2/dataset1.csv";
        String line = "";
        String cvsSplitBy = ",";
        String catSplitBy = " ";
        GrafoGeneros generos = new GrafoGeneros();

        try (
        BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] items = line.split(cvsSplitBy);

                for(int i=0; i< items.length;i++){
                    grafo_generos.addVertice(items[i]);
                    if(i+1 < items.length)
                        grafo_generos.agregarArco(items[i], items[i+1]);
                }
            }
        } catch (
        IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMasBuscado(String genero, int cant){
        ArrayList<Genero> tmp = new ArrayList<>();
        ArrayList<String> respuesta = new ArrayList<>();
        tmp = this.grafo_generos.obtenerAdyacentes(genero);
        Collections.sort(tmp);

        for (int i = 0; i< cant; i++){
            if(tmp.size()>i) {
                respuesta.add(tmp.get(i).getNombre());
                cantidad_iteraciones++;
            }else{
                cantidad_iteraciones++;
                return respuesta;
            }
        }
        return respuesta;
    }

    public GrafoGeneros getCicloGenero(String genero){

        cantidad_iteraciones = 0;
        GrafoGeneros grafoConCiclo = new GrafoGeneros();
        grafoConCiclo = grafo_generos.buscarCiclos(genero);
        cantidad_iteraciones = grafo_generos.getIteraciones();
        return grafoConCiclo;
    }


    public ArrayList<String> caminoMayorPeso(String genero){
        cantidad_iteraciones = 0;
        ArrayList<String> camino = this.grafo_generos.caminoMayorPeso(genero);
        cantidad_iteraciones = this.grafo_generos.getIteraciones();
        return camino;
    }



    @Override
    public String toString() {
        return grafo_generos.toString();
    }
}

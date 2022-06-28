import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Catalogo {

    private GrafoGeneros grafo_generos;

    public Catalogo(){
        this.grafo_generos = new GrafoGeneros();
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

    /**
     * Dado un arreglo de string recibido por parametro
     * genera un archivo .cvs donde cada linea de este
     * es un item del arreglo
     * */
    private void generarSalida(ArrayList<String> titulosLibros) {
        BufferedWriter bw = null;
        try {
            File file = new File("dataset/salida1.csv");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            for(String s : titulosLibros){
                String contenidoLinea = s;
                bw.write(contenidoLinea);
                bw.newLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
            }else{
                return respuesta;
            }
        }
        return respuesta;
    }

    public ArrayList<String> getBuscados(String genero){
        ArrayList<Genero> tmp = new ArrayList<>();
        ArrayList<String> respuesta = new ArrayList<>();
        tmp = this.grafo_generos.obtenerAdyacentes(genero);
        for (int i = 0; i < tmp.size() ; i++){
            respuesta.add(tmp.get(i).getNombre());
        }
        return respuesta;
    }

    //TODO
    public GrafoGeneros getCicloGenero(String genero){
       // if(this.grafo_generos.contieneCiclo()) {//Para no crear un hasmap sin antes saber si al menos hay un ciclo en el grafo
       GrafoGeneros grafoConCiclo = new GrafoGeneros();
       return grafoConCiclo = grafo_generos.buscarCiclos(genero);
    }

    @Override
    public String toString() {
        return grafo_generos.toString();
    }
}

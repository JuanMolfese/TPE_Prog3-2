import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class gene {
    public static void main(String[] args) {
        obtenerDatos();
    }

    public static void obtenerDatos(){

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
                int a = 1;
                for (String i : items
                     ) {
                    System.out.print(i);
                    if (a == 2) {
                        System.out.print("[label = 1]");
                        System.out.println(" ");
                        System.out.print(i);
                        System.out.print(" -> ");
                        //a = 1;
                    } else  if (a == 1) {
                        System.out.print(" -> ");
                        a++;
                    }

                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}

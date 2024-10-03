import java.io.*;
import java.util.ArrayList;

/**
 * Clase FiltroPeliculas
 * Incopora el método filtrarPorGenero y el método main
 */
public class FiltroPeliculas {
    /**
     * Método main donde se inicializa la clase y se llama al método
     */
    public static void main(String[] args) {
        FiltroPeliculas fp = new FiltroPeliculas();

        fp.filtrarPorGenero("Ciencia ficción");
    }

    /**
     * Método filtrarPorGenero
     * @param genero String con el que se va a filtrar el listado de películas
     */
    public void filtrarPorGenero(String genero){
        //Se crea el archivo csv
        File f = new File(genero+".csv");

        /*
        Se crea un arrayList de películas, posteriormente se lee el archivo y se trocea
        la cadena con el método split para añadir cada elemento de la película al listado
         */
        var listaPeli = new ArrayList<Pelicula>();
        try(BufferedReader br = new BufferedReader(new FileReader("peliculas.csv"))){
            String cadena;
            while((cadena = br.readLine()) != null){
                String[]trozo = cadena.split(",");
                var peli = new Pelicula();
                peli.setId(Integer.parseInt(trozo[0]));
                peli.setTitulo(trozo[1]);
                peli.setAño(Integer.parseInt(trozo[2]));
                peli.setDirector(trozo[3]);
                peli.setGenero(trozo[4]);
                listaPeli.add(peli);
            }

            /*
            * Se inicia el BufferedWriter para escribir en el archivo csv creado
            * Se hace un recorrido de películas y si el género de la peli contiene genero
            * se escribe en el documento
            * */
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
            for(Pelicula p : listaPeli){
                    if (p.getGenero().contains(genero)){
                        String peliGenero = p.toString();
                        bw.write(peliGenero);
                        bw.newLine();
                    }

                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

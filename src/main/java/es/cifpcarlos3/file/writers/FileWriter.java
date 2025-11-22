package es.cifpcarlos3.file.writers;

import java.nio.file.Path;

//Contrato que define cómo se van a escribir los ficheros,
//T es el genérico para saber qué objeto tienen que devolver al leer el fichero
public interface FileWriter<T> {

    //Escribe los datos en un fichero
    void write (T data, Path filePath);
}

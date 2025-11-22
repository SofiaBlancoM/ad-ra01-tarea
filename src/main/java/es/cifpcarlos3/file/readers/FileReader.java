package es.cifpcarlos3.file.readers;

import java.nio.file.Path;

//Contrato que define cómo se van a leer los ficheros,
//T es el genérico para saber qué objeto tienen que devolver al leer el fichero
public interface FileReader<T> {

    /**
     * @param filePath ruta del fichero a leer
     * @return contenido del fichero deserializado en un objeto
     */
    T read (Path filePath);

}

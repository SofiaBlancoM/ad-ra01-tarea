package es.cifpcarlos3.file.readers;

import java.io.*;
import java.nio.file.Path;

//Lee ficheros binarios
public class BinaryFileReader<T> implements FileReader<T> {

    //Le pasamos el tipo del objeto que tiene que devolver.
    //Lo hacemos de esta manera porque no podemos hacer el cast directo al tipo T
    private final Class<T> type;

    public BinaryFileReader(Class<T> type) {
        this.type = type;
    }

    @Override
    public T read(Path filePath) {

        System.out.println("Leyendo el fichero " + filePath.getFileName() + "...");

        //Crea un ObjectInputStream para abrir un fichero y leerlo
        try (FileInputStream fileInputStream = new FileInputStream(filePath.toFile());
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){

            //Lee el contenido del fichero
            var data = objectInputStream.readObject();
            //Devuelve el contenido casteado al tipo correspondiente
            return type.cast(data);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

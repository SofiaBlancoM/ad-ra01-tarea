package es.cifpcarlos3.file.writers;

import es.cifpcarlos3.file.helpers.FileHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

//Escribe datos en un fichero en binario
public class BinaryFileWriter<T> implements FileWriter<T> {

    @Override
    public void write(T data, Path filePath) {

        //Crea el fichero si no existe
        FileHelper.createIfNotExists(filePath);
        System.out.println("Escribiendo en el fichero " + filePath.getFileName() + "...");

        //Crea un ObjectOutputStream para abrir y escribir en el fichero
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){

            //Escribe en el fichero
            objectOutputStream.writeObject(data);

            System.out.println("Se ha terminado de escribir en el fichero " + filePath.getFileName());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

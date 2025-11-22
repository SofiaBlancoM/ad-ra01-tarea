package es.cifpcarlos3.file.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHelper {

    //Comprueba si existe el directorio y el fichero de la ruta que se le pasa, y si no existen los crea
    public static void createIfNotExists(Path filePath) {

        try {

            Path parentDirectory = filePath.getParent();

            //Crea las carpetas necesarias si no existen
            if(parentDirectory != null && !parentDirectory.toFile().exists()) {
                Files.createDirectories(parentDirectory);
                System.out.println("Se ha creado la carpeta " + parentDirectory.getFileName() + " porque no existía");
            }

            //Crea el fichero necesario si no existe
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Se ha creado el fichero " + filePath.getFileName() + " porque no existía");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

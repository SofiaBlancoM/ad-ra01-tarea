package es.cifpcarlos3.file.writers;

import es.cifpcarlos3.file.helpers.FileHelper;
import tools.jackson.databind.json.JsonMapper;
import java.nio.file.Path;

//Escribe datos en un fichero en json
public class JsonFileWriter<T> implements FileWriter<T>{

    //Clase que utilizaremos para escribir en el fichero
    private final JsonMapper mapper;

    public JsonFileWriter(JsonMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void write(T data, Path filePath) {

        //Crea el fichero si no existe
        FileHelper.createIfNotExists(filePath);
        System.out.println("Escribiendo en el fichero " + filePath.getFileName() + "...");

        //Escribe los datos en el fichero
        mapper.writeValue(filePath, data);
        System.out.println("Se ha terminado de escribir en el fichero " + filePath.getFileName());

    }

}

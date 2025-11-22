package es.cifpcarlos3.file.readers;

import tools.jackson.databind.json.JsonMapper;
import java.nio.file.Path;

//Lee ficheros json
public class JsonFileReader<T> implements FileReader<T> {

    //Clase que utilizaremos para leer el fichero
    private final JsonMapper mapper;
    //Tipo del objeto que tiene que devolver
    private final Class<T> type;

    public JsonFileReader(JsonMapper mapper, Class<T> type) {
        this.mapper = mapper;
        this.type = type;
    }

    @Override
    public T read(Path filePath) {

        System.out.println("Leyendo el fichero " + filePath.getFileName() + "...");

        //Devuelve el contenido de fichero en el tipo que se le pasa
        return mapper.readValue(filePath.toFile(), type);

    }
}

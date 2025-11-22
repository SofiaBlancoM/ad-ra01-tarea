package es.cifpcarlos3.file.readers;

import tools.jackson.databind.json.JsonMapper;
import java.nio.file.Path;

public class JsonFileReader<T> implements FileReader<T> {

    private final JsonMapper mapper;
    private final Class<T> type;

    public JsonFileReader(JsonMapper mapper, Class<T> type) {
        this.mapper = mapper;
        this.type = type;
    }

    @Override
    public T read(Path filePath) {

        System.out.println("Leyendo el fichero " + filePath.getFileName() + "...");

        return mapper.readValue(filePath.toFile(), type);

    }
}

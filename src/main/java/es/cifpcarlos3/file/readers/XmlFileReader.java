package es.cifpcarlos3.file.readers;

import tools.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

//Lee ficheros xml
public class XmlFileReader<T> implements FileReader<T> {

    //Clase que utilizaremos para leer el fichero
    private final XmlMapper xmlMapper;
    //Tipo del objeto que tiene que devolver
    private final Class<T> type;

    public XmlFileReader(XmlMapper xmlMapper, Class<T> type) {
        this.xmlMapper = xmlMapper;
        this.type = type;
    }

    @Override
    public T read(Path filePath) {

        System.out.println("Leyendo el fichero " + filePath.getFileName() + "...");

        //Crea el inputStream para abrir y leer el fichero
        try (InputStream inputStream = Files.newInputStream(filePath)) {

            //Devuelve el contenido del fichero casteado al tipo correspondiente
            return xmlMapper.readValue(inputStream, type);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

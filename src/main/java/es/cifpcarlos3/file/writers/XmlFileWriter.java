package es.cifpcarlos3.file.writers;

import es.cifpcarlos3.file.helpers.FileHelper;
import tools.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlFileWriter<T> implements FileWriter<T> {

    private final XmlMapper xmlMapper;

    public XmlFileWriter(XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    @Override
    public void write(T data, Path filePath) {

        FileHelper.createIfNotExists(filePath);

        System.out.println("Escribiendo en el fichero " + filePath.getFileName() + "...");

        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            xmlMapper.writeValue(outputStream, data);

            System.out.println("Se ha terminado de escribir en el fichero " + filePath.getFileName());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}

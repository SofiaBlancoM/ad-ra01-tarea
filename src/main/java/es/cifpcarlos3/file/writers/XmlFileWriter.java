package es.cifpcarlos3.file.writers;

import tools.jackson.databind.SerializationFeature;
import tools.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlFileWriter<T> implements FileWriter<T> {
    @Override
    public void saveFile(T data, Path filePath) {

        createFile(filePath);

        var xmlMapper = XmlMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();

        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            xmlMapper.writeValue(outputStream, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void createFile(Path filePath) {

        try {
            Path parentDirectory = filePath.getParent();

            if(parentDirectory != null) {
                Files.createDirectories(parentDirectory);
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

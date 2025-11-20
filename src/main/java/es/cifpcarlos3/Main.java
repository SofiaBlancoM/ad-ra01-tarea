package es.cifpcarlos3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String DAW_FILE_NAME = "lista_alumnado_DAW1.csv";
    private static final String DAM_FILE_NAME = "lista_alumnado_DAM2.txt";

    private static final String DAM_COURSE_NAME = "DAM";

    private static final String DAM_FILE_SEPARATOR = ",";

    private static final String CITY_FILTER = "Cartagena";

    public static void main(String[] args) {

        Path rootProjectPath = Paths.get("").toAbsolutePath();
        Path damPath = rootProjectPath.resolve(DAM_FILE_NAME);

        try(var reader = Files.newBufferedReader(damPath, StandardCharsets.UTF_8)){

            String currentLine;

            while((currentLine = reader.readLine()) !=null){
                String cleanLine = currentLine.trim();

                String[] studentParts = cleanLine.split(DAM_FILE_SEPARATOR);

                int id = Integer.parseInt(studentParts[0].trim());
                String surnames = studentParts[1].trim();
                String name = studentParts[2].trim();
                String city = studentParts[3].trim();
                int age = Integer.parseInt(studentParts[4].trim());

                Course course = new Course();
                course.setName(DAM_COURSE_NAME);

                Student student = new Student(id, surnames, name, city, age, LocalDate.now());

                if(city.equalsIgnoreCase(CITY_FILTER)){
                    course.addStudent(student);
                    System.out.println("El Alumno " + student + " ha sido añadido");
                } else {
                    System.out.println("El Alumno " + student + " no se ha añadido porque es de " + city);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
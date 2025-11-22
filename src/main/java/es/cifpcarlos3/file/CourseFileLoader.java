package es.cifpcarlos3.file;

import es.cifpcarlos3.models.Course;
import es.cifpcarlos3.models.Student;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class CourseFileLoader {

    //Lee ficheros txt o csv y devuelve el curso
    public static Course fromFile(Path filePath, String courseName, String fileSeparator, String filter) {

        System.out.println("Leyendo fichero: " + filePath.getFileName() + "...");

        Course course = new Course();
        course.setName(courseName);

        //Abre el fichero para leerlo
        try(var reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){

            String currentLine;

            //Mientras haya texto
            while((currentLine = reader.readLine()) !=null){

                //Coge el contenido de la línea y la limpia de caracteres extraños
                String cleanLine = cleanLine(currentLine);

                //Separa la línea
                String[] studentParts = cleanLine.split(fileSeparator);

                //Obtiene todos los datos por separado de la línea para crear un alumno
                int id = Integer.parseInt(studentParts[0].trim());
                String surnames = studentParts[1].trim();
                String name = studentParts[2].trim();
                String city = studentParts[3].trim();
                int age = Integer.parseInt(studentParts[4].trim());

                //Crea el alumno
                Student student = new Student(id, surnames, name, city, age, LocalDateTime.now());

                //Añade el estudiante al curso si es de Cartagena
                if(city.equalsIgnoreCase(filter)){
                    course.addStudent(student);
                    System.out.println("El Alumno " + student + " ha sido añadido");
                } else {
                    System.out.println("El Alumno " + student + " no se ha añadido porque es de " + city);
                }

            }

            //Devuelve el curso
            return course;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Limpia un String de caracteres invisibles
    private static String cleanLine (String currentLine) {

        if (!currentLine.isEmpty() && currentLine.charAt(0) == '\uFEFF') {
            return currentLine.substring(1).trim();
        }
        return currentLine.trim();
    }

}

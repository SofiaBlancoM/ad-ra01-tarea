package es.cifpcarlos3;

import es.cifpcarlos3.file.dtos.CreateCourseFileDto;
import es.cifpcarlos3.file.readers.FileReader;
import es.cifpcarlos3.file.writers.BinaryFileWriter;
import es.cifpcarlos3.file.writers.FileWriter;
import es.cifpcarlos3.file.writers.JsonFileWriter;
import es.cifpcarlos3.file.writers.XmlFileWriter;
import es.cifpcarlos3.models.Course;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final String DAW_FILE_NAME = "lista_alumnado_DAW1.csv";
    private static final String DAM_FILE_NAME = "lista_alumnado_DAM2.txt";

    private static final String DAM_COURSE_NAME = "DAM";
    private static final String DAW_COURSE_NAME = "DAW";

    private static final String DAM_FILE_SEPARATOR = ",";
    private static final String DAW_FILE_SEPARATOR = ";";

    private static final String CITY_FILTER = "Cartagena";

    private static final String OUTPUT_FOLDER = "salida";

    private static final String STUDENTS_DAT = "cursos.dat";
    private static final String STUDENTS_JSON = "cursos.json";
    private static final String DAM_JSON = "dam2.json";
    private static final String DAW_JSON = "daw1.json";
    private static final String STUDENTS_XML = "cursos.xml";
    private static final String DAM_XML = "dam2.xml";
    private static final String DAW_XML = "daw1.xml";

    public static void main(String[] args) {

        Path rootProjectPath = Paths.get("").toAbsolutePath();

        Path outputPath = rootProjectPath.resolve(OUTPUT_FOLDER);

        FileWriter<List<Course>> coursesBinaryFileWriter = new BinaryFileWriter<>();

        FileWriter<CreateCourseFileDto> coursesJsonFileWriter = new JsonFileWriter<>();
        FileWriter<Course> courseJsonFileWriter = new JsonFileWriter<>();

        FileWriter<CreateCourseFileDto> coursesXmlFileWriter = new XmlFileWriter<>();
        FileWriter<Course> courseXmlFileWriter = new XmlFileWriter<>();

        System.out.println("---------------------DAM---------------------------");
        Course damCourse = FileReader.createCourseFromFile(
                rootProjectPath.resolve(DAM_FILE_NAME),
                DAM_COURSE_NAME,
                DAM_FILE_SEPARATOR,
                CITY_FILTER
        );

        System.out.println("---------------------DAW---------------------------");
        Course dawCourse = FileReader.createCourseFromFile(
                rootProjectPath.resolve(DAW_FILE_NAME),
                DAW_COURSE_NAME,
                DAW_FILE_SEPARATOR,
                CITY_FILTER
        );

        List<Course> courses = List.of(
                damCourse,
                dawCourse);

        coursesBinaryFileWriter.saveFile(courses, outputPath.resolve(STUDENTS_DAT));

        CreateCourseFileDto dto = new CreateCourseFileDto();
        dto.courses = courses;

        coursesJsonFileWriter.saveFile(dto, outputPath.resolve(STUDENTS_JSON));
        courseJsonFileWriter.saveFile(damCourse, outputPath.resolve(DAM_JSON));
        courseJsonFileWriter.saveFile(dawCourse, outputPath.resolve(DAW_JSON));

        coursesXmlFileWriter.saveFile(dto, outputPath.resolve(STUDENTS_XML));
        courseXmlFileWriter.saveFile(damCourse, outputPath.resolve(DAM_XML));
        courseXmlFileWriter.saveFile(dawCourse, outputPath.resolve(DAW_XML));

    }


}
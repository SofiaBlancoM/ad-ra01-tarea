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

    //Rutas de los ficheros originales
    private static final String DAW_FILE_NAME = "lista_alumnado_DAW1.csv";
    private static final String DAM_FILE_NAME = "lista_alumnado_DAM2.txt";

    //Nombre de los cursos
    private static final String DAM_COURSE_NAME = "DAM";
    private static final String DAW_COURSE_NAME = "DAW";

    //Separadores utilizados en los ficheros originales
    private static final String DAM_FILE_SEPARATOR = ",";
    private static final String DAW_FILE_SEPARATOR = ";";

    //Filtro de los alumnos
    private static final String CITY_FILTER = "Cartagena";

    //Ruta de la carpeta de los ficheros de salida
    private static final String OUTPUT_FOLDER = "salida";

    //Ruta de los ficheros de salida
    private static final String COURSES_DAT = "cursos.dat";
    private static final String COURSES_JSON = "cursos.json";
    private static final String DAM_JSON = "dam2.json";
    private static final String DAW_JSON = "daw1.json";
    private static final String COURSES_XML = "cursos.xml";
    private static final String DAM_XML = "dam2.xml";
    private static final String DAW_XML = "daw1.xml";

    public static void main(String[] args) {

        //Ruta por defecto del projecto
        Path rootProjectPath = Paths.get("").toAbsolutePath();

        //Ruta de la carpeta de salida
        Path outputPath = rootProjectPath.resolve(OUTPUT_FOLDER);

        //Binario
        FileWriter<List<Course>> coursesBinaryFileWriter = new BinaryFileWriter<>();

        //JSON
        FileWriter<CreateCourseFileDto> coursesJsonFileWriter = new JsonFileWriter<>();
        FileWriter<Course> courseJsonFileWriter = new JsonFileWriter<>();

        //XML
        FileWriter<CreateCourseFileDto> coursesXmlFileWriter = new XmlFileWriter<>();
        FileWriter<Course> courseXmlFileWriter = new XmlFileWriter<>();

        //Leer fichero de DAM
        System.out.println("---------------------DAM---------------------------");
        Course damCourse = FileReader.createCourseFromFile(
                rootProjectPath.resolve(DAM_FILE_NAME),
                DAM_COURSE_NAME,
                DAM_FILE_SEPARATOR,
                CITY_FILTER);
        System.out.println("---------------------DAM---------------------------");

        //Leer fichero de DAW
        System.out.println("---------------------DAW---------------------------");
        Course dawCourse = FileReader.createCourseFromFile(
                rootProjectPath.resolve(DAW_FILE_NAME),
                DAW_COURSE_NAME,
                DAW_FILE_SEPARATOR,
                CITY_FILTER);
        System.out.println("---------------------DAW---------------------------");

        //Lista de cursos unificada
        List<Course> courses = List.of(
                damCourse,
                dawCourse);

        //Binario conjunto
        coursesBinaryFileWriter.saveFile(courses, outputPath.resolve(COURSES_DAT));

        //JSON conjunto
        CreateCourseFileDto dto = new CreateCourseFileDto();
        dto.courses = courses;

        coursesJsonFileWriter.saveFile(dto, outputPath.resolve(COURSES_JSON));

        //JSON separados
        courseJsonFileWriter.saveFile(damCourse, outputPath.resolve(DAM_JSON));
        courseJsonFileWriter.saveFile(dawCourse, outputPath.resolve(DAW_JSON));

        //XML conjunto
        coursesXmlFileWriter.saveFile(dto, outputPath.resolve(COURSES_XML));

        //XML separados
        courseXmlFileWriter.saveFile(damCourse, outputPath.resolve(DAM_XML));
        courseXmlFileWriter.saveFile(dawCourse, outputPath.resolve(DAW_XML));

    }


}
package es.cifpcarlos3;

import es.cifpcarlos3.file.dtos.CreateCourseFileDto;
import es.cifpcarlos3.file.CourseFileLoader;
import es.cifpcarlos3.file.readers.BinaryFileReader;
import es.cifpcarlos3.file.readers.FileReader;
import es.cifpcarlos3.file.readers.JsonFileReader;
import es.cifpcarlos3.file.readers.XmlFileReader;
import es.cifpcarlos3.file.writers.BinaryFileWriter;
import es.cifpcarlos3.file.writers.FileWriter;
import es.cifpcarlos3.file.writers.JsonFileWriter;
import es.cifpcarlos3.file.writers.XmlFileWriter;
import es.cifpcarlos3.models.Course;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.xml.XmlMapper;

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

        FileReader<List<Course>> coursesBinaryFileReader = new BinaryFileReader<>();

        //JSON
        var jsonMapper = JsonMapper.builder()
                .enable(SerializationFeature.WRAP_ROOT_VALUE)
                .enable(DeserializationFeature.UNWRAP_ROOT_VALUE)
                .build();

        FileWriter<CreateCourseFileDto> coursesJsonFileWriter = new JsonFileWriter<>(jsonMapper);
        FileWriter<Course> courseJsonFileWriter = new JsonFileWriter<>(jsonMapper);

        FileReader<CreateCourseFileDto> coursesJsonFileReader = new JsonFileReader<>(jsonMapper, CreateCourseFileDto.class);
        FileReader<Course> courseJsonFileReader = new JsonFileReader<>(jsonMapper, Course.class);

        //XML
        var xmlMapper = XmlMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();

        FileWriter<CreateCourseFileDto> coursesXmlFileWriter = new XmlFileWriter<>(xmlMapper);
        FileWriter<Course> courseXmlFileWriter = new XmlFileWriter<>(xmlMapper);

        FileReader<CreateCourseFileDto> coursesXmlFileReader = new XmlFileReader<>(xmlMapper, CreateCourseFileDto.class);
        FileReader<Course> courseXmlFileReader = new XmlFileReader<>(xmlMapper, Course.class);

        //Leer fichero de DAM
        System.out.println("---------------------DAM---------------------------");
        Course damCourse = CourseFileLoader.fromFile(
                rootProjectPath.resolve(DAM_FILE_NAME),
                DAM_COURSE_NAME,
                DAM_FILE_SEPARATOR,
                CITY_FILTER);
        System.out.println("---------------------DAM---------------------------");

        //Leer fichero de DAW
        System.out.println("---------------------DAW---------------------------");
        Course dawCourse = CourseFileLoader.fromFile(
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
        coursesBinaryFileWriter.write(courses, outputPath.resolve(COURSES_DAT));

        //JSON conjunto
        CreateCourseFileDto dto = new CreateCourseFileDto();
        dto.courses = courses;

        coursesJsonFileWriter.write(dto, outputPath.resolve(COURSES_JSON));

        //JSON separados
        courseJsonFileWriter.write(dawCourse, outputPath.resolve(DAW_JSON));
        courseJsonFileWriter.write(damCourse, outputPath.resolve(DAM_JSON));

        //XML conjunto
        coursesXmlFileWriter.write(dto, outputPath.resolve(COURSES_XML));

        //XML separados
        courseXmlFileWriter.write(dawCourse, outputPath.resolve(DAW_XML));
        courseXmlFileWriter.write(damCourse, outputPath.resolve(DAM_XML));

        //Lectura de ficheros
        System.out.println(coursesBinaryFileReader.read(outputPath.resolve(COURSES_DAT)));
        System.out.println(coursesJsonFileReader.read(outputPath.resolve(COURSES_JSON)));
        System.out.println(coursesXmlFileReader.read(outputPath.resolve(COURSES_XML)));

        System.out.println(courseJsonFileReader.read(outputPath.resolve(DAW_JSON)));
        System.out.println(courseJsonFileReader.read(outputPath.resolve(DAM_JSON)));

        System.out.println(courseXmlFileReader.read(outputPath.resolve(DAW_XML)));
        System.out.println(courseXmlFileReader.read(outputPath.resolve(DAM_XML)));

    }

}
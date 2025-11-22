# Proyecto de Gestión de Cursos y Alumnos

Este repositorio contiene una pequeña aplicación en Java diseñada para practicar conceptos de Acceso a datos, manejo de ficheros y trabajo con diferentes formatos de almacenamiento.

---

## 📘 Descripción General

La aplicación permite gestionar **alumnos** y **cursos**, además de guardar y leer esta información desde distintos tipos de archivos:

- **JSON**
- **XML**
- **Binario**
---

## 🧱 Estructura del Proyecto

El proyecto está organizado en múltiples clases responsables de funciones específicas, favoreciendo la separación de responsabilidades y facilitando la lectura del código.

La idea principal es crear una interfaz para Readers y otra para Writers para así encapsular la lógica de cada tipo distinto de fichero. 

---

## 🚀 Cómo Ejecutar el Proyecto
Aunque siempre está la opción de clonarse el repositorio y ejectuarlo con tu IDE favorito, puedes ejecutarlo en local de la siguiente manera:

1. Asegúrate de tener **Java 21+** instalado.
2. Descarga el zip desde [aquí](https://github.com/SofiaBlancoM/ad-ra01-tarea/releases/tag/1.0.0) y descomprímelo.
3. Abre una terminal en la carpeta correspondiente y ejecuta el siguiente comando:

    ```bash
    java -jar tarea1.jar
    ```
---

## 🗂️ Objetivo principal

### ✔ Leer ficheros principales  
Lee y procesa un fichero csv y otro txt para recuperar los alumnos de cada curso.

### ✔ Guardar la información  
En función de los ficheros leídos, guarda la información en tres ficheros distintos:
- XML  
- JSON  
- Binario

### ✔ Leer información desde ficheros  
Lee los ficheros generados para validar que todo funciona correctamente.

---

## 🧑💻 Autor

Proyecto realizado por Sofía Blanco Méndez.

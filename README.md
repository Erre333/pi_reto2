# Reto 2 — Web Scraping con Java y Jsoup

**Proyecto Intermodular — 2.º DAM**

## Descripción

Aplicación desarrollada en Java que realiza web scraping de noticias tecnológicas publicadas en Hacker News, utilizando la librería Jsoup.

El objetivo es conectar con una página web real, extraer información de sus elementos HTML y almacenarla en un fichero de texto para su posterior lectura.

Se ha elegido Hacker News como alternativa original al ejemplo de FilmAffinity propuesto en el enunciado.

## Tecnologías utilizadas

- Java 25
- Maven
- Jsoup 1.24.1
- IntelliJ IDEA

## Página web utilizada

https://news.ycombinator.com/

## Datos extraídos

Para cada noticia se obtiene:

- Título
- Autor
- Puntuación
- Número de comentarios
- Enlace a la noticia

## Funcionamiento

El programa realiza las siguientes operaciones:

1. Establece una conexión HTTP con Hacker News mediante Jsoup.
2. Descarga y analiza el documento HTML.
3. Localiza las noticias mediante selectores CSS.
4. Extrae la información correspondiente a cada noticia.
5. Guarda los resultados en `noticias_tecnologia.txt`, utilizando codificación UTF-8.
6. Lee el fichero generado y muestra su contenido en consola.

## Ejecución

1. Clonar o descargar este repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Comprobar que Maven ha descargado la dependencia Jsoup.
4. Ejecutar el método `main()` de la clase `App`.
5. Consultar el fichero `noticias_tecnologia.txt` generado en la raíz del proyecto.

Se necesita conexión a Internet para obtener las noticias.

## Consideraciones

La información extraída depende del contenido actual de Hacker News. Los titulares, puntuaciones y comentarios pueden cambiar entre ejecuciones.

El programa contempla la posibilidad de que algunos datos no estén disponibles y utiliza valores predeterminados para esos casos.

Si Hacker News modifica su estructura HTML, podría ser necesario actualizar los selectores CSS del programa.

## Resultado

La aplicación ha conseguido extraer 30 noticias, guardarlas correctamente en un fichero de texto y volver a leerlas para mostrarlas por consola.

El proyecto demuestra el uso de Java, Maven, Jsoup, selectores CSS y operaciones de lectura y escritura de archivos.
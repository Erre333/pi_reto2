
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {

    public static void main(String[] args) {

        String url = "https://news.ycombinator.com/";

        try {
            // 1. Conectamos con Hacker News.
            Document documento = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();

            System.out.println("Conexión realizada correctamente.");

            // 2. Obtenemos las noticias de la página.
            Elements noticias = documento.select("tr.athing");

            System.out.println("Noticias encontradas: " + noticias.size());

            StringBuilder contenido = new StringBuilder();
            int contador = 0;

            for (Element noticia : noticias) {

                Element enlace = noticia.selectFirst("span.titleline > a");

                if (enlace == null) {
                    continue;
                }

                contador++;

                String titulo = enlace.text();
                String enlaceNoticia = enlace.absUrl("href");

                String autor = "No disponible";
                String puntos = "Sin puntuación";
                String comentarios = "Sin comentarios";

                // 3. Extraemos los datos adicionales.
                Element detalles = noticia.nextElementSibling();

                if (detalles != null) {

                    Element elementoAutor = detalles.selectFirst(
                            "a.hnuser, a[href^='user?id=']"
                    );

                    Element elementoPuntos =
                            detalles.selectFirst("span.score");

                    if (elementoAutor != null) {
                        autor = elementoAutor.text();
                    }

                    if (elementoPuntos != null) {
                        puntos = elementoPuntos.text();
                    }

                    Elements enlacesComentarios =
                            detalles.select("a[href*='item?id=']");

                    for (Element enlaceComentarios : enlacesComentarios) {
                        String texto = enlaceComentarios.text();

                        if (texto.contains("comment")
                                || texto.equals("discuss")) {
                            comentarios = texto;
                            break;
                        }
                    }
                }

                // 4. Preparamos el contenido del archivo.
                contenido.append("NOTICIA ").append(contador).append("\n");
                contenido.append("Título: ").append(titulo).append("\n");
                contenido.append("Autor: ").append(autor).append("\n");
                contenido.append("Puntuación: ").append(puntos).append("\n");
                contenido.append("Comentarios: ").append(comentarios).append("\n");
                contenido.append("Enlace: ").append(enlaceNoticia).append("\n");
                contenido.append("----------------------------------------\n");
            }

            if (contador == 0) {
                System.out.println("No se encontraron noticias para guardar.");
                return;
            }

            // 5. Guardamos las noticias en un fichero de texto.
            Path archivo = Path.of("noticias_tecnologia.txt");

            Files.writeString(
                    archivo,
                    contenido.toString(),
                    StandardCharsets.UTF_8
            );

            System.out.println("Se han guardado " + contador + " noticias.");
            System.out.println("Archivo: " + archivo.toAbsolutePath());

            // 6. Leemos el fichero y mostramos su contenido.
            String contenidoLeido = Files.readString(
                    archivo,
                    StandardCharsets.UTF_8
            );

            System.out.println("\nCONTENIDO DEL ARCHIVO:");
            System.out.println("========================================");
            System.out.print(contenidoLeido);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

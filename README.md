# TALLER DISEÑO Y ESTRUCTURACIÓN DE APLICACIONES DISTRIBUIDAS EN INTERNET

Un servidor web que soporte múlltiples solicitudes seguidas (no concurrentes). El servidor debe leer los archivos del disco local y retornar todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes. Construya una aplicación web con  javascript, css, e imágenes para probar su servidor. Incluya en la aplicación la comunicación asíncrona con unos servicios REST en el backend.

### Prerrequisitos

- Java
- Maven
- Git


### Instalación

Para hacer uso del proyecto clone el repositorio usando el siguiente comando

```
git clone https://github.com/lgar000/Taller2-Arep.git
```

Ubiquese en la carpeta en la cual clono el repositorio. A continuación
acceda a la carpeta principal del proyecto mediante el siguiente comando

```
cd Taller2-Arep
```

Para empaquetar el proyecto ejecute

```
mvn package
```



## Pruebas

Para probar el funcionamiento, una vez tenga descargado el proyecto y ejecutado correctamente proceda a ingresar a un navergador, donde debera ingresar http://localhost:35000/archivoSolicitado.extension, donde las extensiones pueden ser (js, html, css, png, jpg). Los archivos a solicitar se encuentra dentro del proyecto.

### Prueba html

![html](https://github.com/lgar000/Taller2-Arep/blob/main/imagenes/pruebaHtml.png)

### Prueba js

![js](https://github.com/lgar000/Taller2-Arep/blob/main/imagenes/pruebaJs.png)

### Prueba css

![css](https://github.com/lgar000/Taller2-Arep/blob/main/imagenes/pruebaCss.png)

### Prueba imagen png

![png](https://github.com/lgar000/Taller2-Arep/blob/main/imagenes/pruebaImagenPNG.png)

### Prueba imagen jpg

![jpg]()

## Diseño

El cliente es el navegador que hace solicitudes http de tipo Get, mediante estas se solicitan archivos estáticos que pueden ser  HTML, JavaScript, CSS, y archivos de imagen (JPG y PNG). Estas solicitudes son respondidas por un servidor, que escucha el puerto 35000 y gestiona las peticiones para mostrar el contenido de los archivos.
En la clase HttpServer se establece un ServerSocket para escuchar en el puerto 35000. Luego en un bucle while, se aceptan conexiones de clientes y se crea un nuevo Socket para manejar cada conexión. A continuación se lee  la solicitud HTTP para obtener la URI (Uniform Resource Identifier). Para lograr determinar el tipo de archivo  y retornar el contenido de los archivos se  hace uso del método httpRequestClient que recibe como parámetros el path y el socket del cliente, aquí se evalúa cual es la extensión del archivos(ja, css, html, jpg o png), dependiendo del tipo Construye y envía una respuesta HTTP al cliente, en caso de no encontrar el archivo envía un mensaje de error.


## Construido Con

* [Java 11](https://www.oracle.com/co/java/technologies/javase/jdk11-archive-downloads.html) - Lenguaje de programación y desarrollo
* [Html](https://developer.mozilla.org/es/docs/Web/HTML) - Lenguaje de marcado para la elaboración de páginas web
* [JavaScript](https://developer.mozilla.org/es/docs/Web/CSS) -JavaScript es un lenguaje de programación interpretado
* [Maven](https://maven.apache.org/) - Gestión de dependencias
* [Intellij](https://www.jetbrains.com/es-es/idea/) - Entorno de desarrollo integrado para el desarrollo de programas informáticos
* [Git](https://rometools.github.io/rome/) - Sistema de control de versiones distribuido


## Autor

* **Laura García** - [lgar000](https://github.com/lgar000)

## Licencia

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

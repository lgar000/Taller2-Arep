package edu.escuelaing.arep.ASE.app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class HttpServer {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean firstLine = true;
            String uriStr = "";

            while ((inputLine = in.readLine()) != null) {
                if (firstLine) {
                    uriStr = inputLine.split(" ")[1];
                    System.out.println("uriStr"+uriStr);
                    firstLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            URI file = new URI(uriStr);
            System.out.println("file"+ file);
            System.out.println("Find URI: " + file.getPath());

            try {
                outputLine = httpRequestClient(file.getPath(), clientSocket);
            } catch (IOException e) {
                outputLine = httpError();
            }

            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static String httpError() {
        String outputLine = "HTTP/1.1 400 Not Found\r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Error Not found</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>Error</h1>\n"
                + "    </body>\n";
        return outputLine;

    }

    /**
     *
     * @param path
     * @param clientSocket
     * @return
     * @throws IOException
     */
    public static String httpRequestClient(String path, Socket clientSocket ) throws IOException {

        String extension = "";

        if (path.endsWith(".html")) {
            extension = "text/html";
        } else if (path.endsWith(".js")) {
            extension = "text/javascript";
        } else if (path.endsWith(".css")) {
            extension = "text/css";
        } else if (path.endsWith(".png")) {
            extension = "image/png";
        }else if (path.endsWith(".jpg")) {
            extension = "image/jpg";
        }

        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:" + extension + "\r\n"
                + "\r\n";
        Path page = Paths.get("target/classes/public" + path);
        if ( extension.startsWith("text/")) {
            Charset charset = Charset.forName("UTF-8");
            try (BufferedReader reader = Files.newBufferedReader(page, charset)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    outputLine += line;
                }
            }
        } else if  (extension.startsWith("image/")) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + extension);
            out.println();
            BufferedImage image = ImageIO.read(new File("target/classes/public" + path));
            ImageIO.write(image, "PNG", clientSocket.getOutputStream());
        }else {
            outputLine = httpError(); // Not Found
        }


        return outputLine;
    }
}
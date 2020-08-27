package edu.escuelaing.arep.app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.io.File;

public class HttpServer {
    private String root = "src/main/resources";
    private PrintWriter out = null;

    public void start() throws IOException {
        int port = getPort();
        while(true) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 35000.");
                System.exit(1);
            }
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int type;
            String inputLine, outputLine, file = "";
            while ((inputLine = in.readLine()) != null) {
                //System.out.println("Recibí: " + inputLine);
                if (inputLine.contains("GET")) {
                    file = inputLine.split(" ")[1];
                    if (file.equals("/")) {
                        file = "/index.html";
                    }
                }
                if (!in.ready()) {
                    break;
                }
            }
            type = getType(file);
            if (type == 0) {
                outputLine = getFile(file,"html");
                out.println(outputLine);
            }else if(type == 1){
                outputLine = getFile(file,"json");
                out.println(outputLine);
            }else {
                getImage(file, clientSocket.getOutputStream());
            }

            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }
    }

    public String getFile(String ruta,String type){
        String outputLine = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/"+type+"\r\n" + "\r\n",path = root + ruta;
        File file = new File(path);
        if(file.exists()){
            String content;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((content = br.readLine()) != null) {
                    //System.out.println(content);
                    outputLine += content;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            outputLine = "HTTP/1.1 404 Not Found \r\nContent-Type: text/html \r\n\r\n <!DOCTYPE html> <html>"
                    + "<head><title>404</title></head>" + "<body> <h1>404 Not Found " + file.getName()
                    + "</h1></body></html>";
        }
        return outputLine;
    }

    public void getImage(String type, OutputStream outClient){
        String path = root + type;
        System.out.println(path);
        File file = new File(path);
        if (file.exists()) {
            try {
                BufferedImage image = ImageIO.read(file);
                ByteArrayOutputStream ArrBytes = new ByteArrayOutputStream();
                DataOutputStream writeimg = new DataOutputStream(outClient);
                ImageIO.write(image, "PNG", ArrBytes);
                writeimg.writeBytes("HTTP/1.1 200 OK \r\n" + "Content-Type: image/png \r\n" + "\r\n");
                writeimg.write(ArrBytes.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            out.println("HTTP/1.1 404 Not Found \r\nContent-Type: text/html \r\n\r\n <!DOCTYPE html> <html>"
                    + "<head><title>404</title></head>" + "<body> <h1>404 Not Found " + file.getName()
                    + "</h1></body></html>");
        }
    }

    public int getType(String type){
        if(type.contains("html")){
            return 0;
        }else if(type.contains("js")){
            return 1;
        }else{
            return 2;
        }
    }

    /**
     * Calcula el puerto que se va a utilizar
     * @return int puerto
     */
    public int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000; // returns default port if heroku-port isn't set(i.e. on localhost)
    }
}
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class InetAnswer {
    private final static String defaultDirectory = FileW.getParam(2, "Default directory:") + "/";
    public final static String defaultFile = FileW.getParam(1, "Default file:");

    public static void putGet(String request, OutputStream outputStream, byte[] buffer){
        String path = FileW.getPath(request, 0, "GET /", " HTTP/1.1");
        File file = new File(defaultDirectory + path);

        if (file.exists()) {
            PrintS.addPrint("Put file: " + path);
            String charset = "";
            if(getMimeType(path)=="text/html"){charset = "; charset=UTF-8";}

            String response =
                    "HTTP/1.1 200 Ok\n" +
                            "Connection: open\n"+
                            "Content-Length: "+ file.length()+"\n"+
                            "Content-Type: "+   getMimeType(path)+charset+"\n"+
                            "Last-Modified: " + new Date(file.lastModified())+"\n"+
                            "Server: Server\n\n";

               try {
                   outputStream.write(response.getBytes(StandardCharsets.UTF_8));
               } catch (IOException e) { e.printStackTrace(); }
               try {
                   FileInputStream fileInputStream = new FileInputStream(file);
                   int write = 1;
                   try {
                       while (write > 0) {
                           write = fileInputStream.read(buffer);
                           if (write > 0) outputStream.write(buffer, 0, write);
                       }
                       fileInputStream.close();

                   }catch (IOException e){ e.printStackTrace(); }
               } catch (FileNotFoundException e) { e.printStackTrace(); }

               PrintS.addPrint("___Completed");

       }else {
           PrintS.addPrint("False file: " + path + "_Not Found___");
           PrintS.addPrint("Put_404_completed");
       }
   }

    static String getMimeType(String path){
        int ex = path.lastIndexOf(".");
        if(ex>0){
            String[] words = path.split("\\.");
            switch (words[1]){
                case ("html"):{
                    path = "text/html";
                    break;
                }
                case ("css"):{
                    path = "text/css";
                    break;
                }case ("gif"):{
                    path = "image/gif";
                    break;
                }
                case ("txt"):{
                    path = "text/txt";
                    break;
                }
                case ("png"):{
                    path = "image/png";
                    break;
                }
                case ("ico"):{
                    path = "image/ico";
                    break;
                }
                case ("jpg"):{
                    path = "image/jpg";
                    break;
                }
                case ("swg"):{
                    path = "image/svg+xml";
                    break;
                }
                case  ("woff2"):{
                    path  = "font/woff2";
                    break;
                }
            }
        }
        return path;
    }
}

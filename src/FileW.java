import java.io.*;
import java.util.Scanner;

public class FileW {
    public static String getParam(int str, String word) {
        //------------
        final  File file = new File("Data/parameters.txt");
        //------------
        String string = null;
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (IOException ioException) { ioException.printStackTrace(); }
        }

        try {
            for (int i = 0; i <= str; i++) {
                string = bufferedReader.readLine();
            }
        }catch (IOException e) { e.printStackTrace(); }

        try {
            String[] words = string.split(word);
            string = words[1];
        }catch (NullPointerException e){
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(file);

                Scanner scanner = new Scanner(System.in);System.out.println();
                PrintS.addPrint("Port:");
                fileWriter.write("Port:"+scanner.nextLine()+"\n");
                PrintS.addPrint("Default file:");
                fileWriter.write("Default file:"+scanner.nextLine()+"\n");
                PrintS.addPrint("Default directory:");
                fileWriter.write("Default directory:"+scanner.nextLine()+"\n");

                fileWriter.close();
            } catch (IOException ioException) { ioException.printStackTrace(); }
        }
        return string;
    }
    static String getPath(String req, int strNum, String lKey, String rKey) {
        String pathString;
        String[] path = req.split("\n");
        pathString = path[strNum];
        path = pathString.split(rKey);
        pathString = path[0];
        path = pathString.split(lKey);
        try {
            pathString = path[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //------------
            pathString = InetAnswer.defaultFile;
            //------------
        }
        return pathString;
    }
    public static  String getMethod(String req){
        String[] lineString = req.split("\n");
        String ret = lineString[0];
        lineString = req.split(" /");
        return lineString[0];
    }
}

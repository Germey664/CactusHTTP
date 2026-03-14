
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ClientWork {
    //-------------------------------------------------------------------------------
    public static void Work(OutputStream outputStream, byte[] byffer) {
        String request = new String(byffer, StandardCharsets.UTF_8);
        //PrintS.addPrint(request);
        switch (FileW.getMethod(request)){
            case ("GET"):{
                InetAnswer.putGet(request,outputStream,byffer);
                break;
            }
            case ("POST"):{

                break;
            }
            case ("HEAD"):{

                break;
            }
            case ("OPTIONS"):{

                break;
            }
            case ("DELETE"):{

                break;
            }
            case ("TRACE"):{

                break;
            }
            case ("CONNECT"):{

                break;
            }
            default:{
                PrintS.addPrint("Error client");
                break;
            }
        }
    }
}

package BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by user-hfc on 2017/10/29.
 */
public class TimeServer
{
    public static void main(String[] args) throws IOException
    {
        int port = 22233;
        if (null != args && args.length > 0)
        {
            try
            {
                port = Integer.parseInt(args[0]);
            }
            catch (Exception e)
            {
                port = 22233;
            }
        }

        ServerSocket server = null;
        try
        {
            server = new ServerSocket(port);
            System.out.println("the time server is start in port: " + port);
            Socket socket = null;
            while (true)
            {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }
        finally
        {
            if (null != server)
            {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * created by Jay on 2019/6/10
 */
public class RPCPublisher
{
    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public void publish(Object service, int port)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true)
            {
//                //等待客户端请求
                System.out.println(service.getClass().getName() + " is published on port:" + port);
                Socket socket = serverSocket.accept();
                poolExecutor.submit(new ProcessHandler(socket, service));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

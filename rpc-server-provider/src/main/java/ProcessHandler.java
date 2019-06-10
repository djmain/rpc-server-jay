import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * created by Jay on 2019/6/10
 */
public class ProcessHandler implements Runnable
{

    private Socket socket;
    private Object service;

    public ProcessHandler(Socket socket, Object service)
    {
        this.socket = socket;
        this.service = service;
    }

    public void run()
    {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        //执行请求
        try
        {
            //从输入流中反序列化RPCRequest
            inputStream = new ObjectInputStream(socket.getInputStream());
            RPCRequest request = (RPCRequest) inputStream.readObject();
//            System.out.println("get RPCRequest:" + request);
            // 注意使用完输入流反序列化完毕后，不能立即关闭inputStream
            Object result = invoke(request);
            if (result != null)
            {
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(result);
                outputStream.flush();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }

            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }


        }

    }

    private Object invoke(RPCRequest request) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Class clazz = Class.forName(request.getClassName());
        Class[] parameterTypes = new Class[request.getParameters().length];
        for (int i = 0; i < request.getParameters().length; i++)
        {
            parameterTypes[i] = request.getParameters()[i].getClass();
        }
        Method method = clazz.getMethod(request.getMethodName(), parameterTypes);
        Object result = null;
        try
        {
            result = method.invoke(service, request.getParameters());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            AddUserException addUserException = (AddUserException) e;
            return addUserException;
        }
        return result;
    }
}

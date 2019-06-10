import java.util.concurrent.TimeUnit;

/**
 * created by Jay on 2019/6/10
 */
public class UserServiceImpl implements UserService
{
    public String sayHello(String name)
    {
        System.out.println("request ... " + name);
//        try
//        {
//            TimeUnit.SECONDS.sleep(10);
//        }
//        catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }

        return "hello, " + name;
    }

    public void addUser(User user) throws AddUserException
    {
//        System.out.println("request ... " + user);
        System.out.println("add user:" + user);
        throw new AddUserException("add user exception");
    }
}

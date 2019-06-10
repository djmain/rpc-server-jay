/**
 * created by Jay on 2019/6/10
 */
public class RPCServerMain
{
    public static void main(String[] args)
    {
        UserService userService =new UserServiceImpl();
        RPCPublisher publisher = new RPCPublisher();
        publisher.publish(userService, 1000);
    }
}

/**
 * created by Jay on 2019/6/10
 */
public interface UserService
{
    String sayHello(String name);

    void addUser(User user) throws AddUserException;
}

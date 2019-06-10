import java.io.Serializable;

/**
 * created by Jay on 2019/6/10
 */
public class AddUserException extends Exception implements Serializable
{

    private static final long serialVersionUID = -4426660669639228010L;

    public AddUserException(String message)
    {
        super(message);
    }
}

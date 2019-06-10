import lombok.Data;

import java.io.Serializable;

/**
 * created by Jay on 2019/6/10
 */
@Data
public class RPCRequest implements Serializable
{
    private static final long serialVersionUID = -7588999297354596218L;
    private String className;
    private String methodName;
    private Object[] parameters;
}

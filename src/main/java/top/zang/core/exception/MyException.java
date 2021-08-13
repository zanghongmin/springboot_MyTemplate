package top.zang.core.exception;

import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class MyException extends RuntimeException {

    private ReturnTEnum returnTEnum;

    public MyException(String message,ReturnTEnum returnTEnum) {
        super(message);
        this.returnTEnum = returnTEnum;
    }
//    public MyException(String message) {
//        super(message);
//    }

}

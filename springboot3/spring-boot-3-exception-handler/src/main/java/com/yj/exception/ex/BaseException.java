/**
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:25
 * @version 1.0.0
 */
package com.yj.exception.ex;

import com.yj.exception.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:25
 * @ve
rsion 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException{
    private Integer code;
    private String message;

    public BaseException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}

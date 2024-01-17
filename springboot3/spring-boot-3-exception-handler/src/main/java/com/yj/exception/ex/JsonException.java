package com.yj.exception.ex;

import com.yj.exception.constant.Status;
import lombok.Getter;

/**
 * <p>
 * JSON异常
 * </p>
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:25
 */
@Getter
public class JsonException extends BaseException {

    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}

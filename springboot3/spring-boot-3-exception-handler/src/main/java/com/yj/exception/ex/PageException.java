package com.yj.exception.ex;

import com.yj.exception.constant.Status;
import lombok.Getter;

/**
 * <p>
 * 页面异常
 * </p>
 *
 * @author yangjian
 * @email 2628168756@qq.com
 * @date 2024/1/17 20:25
 */
@Getter
public class PageException extends BaseException {

    public PageException(Status status) {
        super(status);
    }

    public PageException(Integer code, String message) {
        super(code, message);
    }
}

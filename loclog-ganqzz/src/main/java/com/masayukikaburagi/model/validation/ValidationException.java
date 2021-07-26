package com.masayukikaburagi.model.validation;

/**
 * ValidationException
 * Validation関連の例外を包む。
 *
 * @author 鏑木雅之
 */
public class ValidationException extends Exception {

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}

package com.masayukikaburagi.model;

/**
 * DataAcessException
 * DataAccess関連の例外を包む。
 *
 * @author 鏑木雅之
 */
public class DataAccessException extends Exception {

    public DataAccessException(Throwable cause) {
        super(cause);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}

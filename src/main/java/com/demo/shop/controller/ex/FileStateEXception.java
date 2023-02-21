package com.demo.shop.controller.ex;

/**
 *  文件状态的异常类，要求文件必须关闭
 */
public class FileStateEXception extends FileUploadException{
    public FileStateEXception() {
        super();
    }

    public FileStateEXception(String message) {
        super(message);
    }

    public FileStateEXception(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateEXception(Throwable cause) {
        super(cause);
    }

    protected FileStateEXception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

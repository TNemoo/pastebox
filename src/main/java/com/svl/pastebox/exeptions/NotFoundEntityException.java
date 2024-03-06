package com.svl.pastebox.exeptions;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String s) {
        super(s);
    }
}

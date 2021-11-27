package com.mammadli.phonebookapp.util;

@FunctionalInterface
public interface GenerateResponse<Code,Message,Body,ResponseType> {
    ResponseType generate(Code code, Message message, Body body);
}

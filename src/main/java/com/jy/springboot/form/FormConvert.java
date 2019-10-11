package com.jy.springboot.form;

public interface FormConvert<S,T> {
    T convert(S s);
}

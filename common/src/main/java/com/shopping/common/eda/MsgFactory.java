package com.shopping.common.eda;

@FunctionalInterface
public interface MsgFactory<T> {
    T getMsg(Object origin);
}

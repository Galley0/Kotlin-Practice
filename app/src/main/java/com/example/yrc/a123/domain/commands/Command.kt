package com.example.yrc.a123.domain.commands

/**
 * Created by YRC on 2017/9/7.
 */
public interface Command<T> {
    fun execute(): T
}

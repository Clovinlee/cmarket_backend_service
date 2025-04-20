package com.chris.cmarket.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerGetter {

    public static Logger getLogger(Object obj) {
        return LoggerFactory.getLogger(obj.getClass());
    }
}
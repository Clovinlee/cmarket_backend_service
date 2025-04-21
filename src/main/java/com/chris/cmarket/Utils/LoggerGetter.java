package com.chris.cmarket.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerGetter {
    /**
     * Returns an SLF4J {@link Logger} for the class of the provided object.
     *
     * @param obj the object whose class will be used to retrieve the logger
     * @return a logger for the object's class
     */
    public static Logger getLogger(Object obj) {
        return LoggerFactory.getLogger(obj.getClass());
    }
}
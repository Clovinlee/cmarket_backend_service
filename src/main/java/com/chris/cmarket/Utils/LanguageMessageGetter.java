package com.chris.cmarket.Utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LanguageMessageGetter {

    private final MessageSource messageSource;

    public LanguageMessageGetter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Get message by key and locale.
     *
     * @param key    the message key
     * @param args   optional arguments for message formatting
     * @param locale the locale (e.g., Locale.ENGLISH)
     * @return the localized message
     */
    public String getMessage(String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, locale);
    }

    /**
     * Overloaded method with default locale (English).
     */
    public String getMessage(String key) {
        return getMessage(key, null, Locale.ENGLISH);
    }
}
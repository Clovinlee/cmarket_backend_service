package com.chris.cmarket.Infrastructure.Config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class LanguageConfig {
    /**
     * Configures a LocaleResolver that resolves the locale based on the
     * Accept-Language header.
     *
     * @return a LocaleResolver bean for handling locale resolution.
     */
    @Bean
    LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);

        return resolver;
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");

        return lci;
    }

    /**
     * Already declared in application.properties
     * spring.messages.basename=languages/messages
     * 
     */

    // @Bean
    // MessageSource messageSource() {
    // ReloadableResourceBundleMessageSource messageSource = new
    // ReloadableResourceBundleMessageSource();
    // messageSource.setBasenames("languages/messages");
    // messageSource.setDefaultEncoding("UTF-8");

    // return messageSource;
    // }
}

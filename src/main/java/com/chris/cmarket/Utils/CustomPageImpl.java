package com.chris.cmarket.Utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class CustomPageImpl<T> {
    protected List<T> content;
    protected int totalElements;
}

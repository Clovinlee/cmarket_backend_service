package com.chris.cmarket.Common.Dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class CustomPageImplDto<T> {
    protected List<T> content;
    protected int totalElements;
}

package com.chris.cmarket.Common.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPageImplDto<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    protected List<T> content;
    protected long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;

    /**
     * Create custom pagination implementation for stable JSON & test
     *
     * @param page Pagination result
     */
    public CustomPageImplDto(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }

    /**
     * @return true if empty
     */
    public boolean isEmpty() {
        return this.getTotalElements() == 0;
    }
}

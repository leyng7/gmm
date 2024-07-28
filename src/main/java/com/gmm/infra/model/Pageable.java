package com.gmm.infra.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Objects.isNull;

@Getter
public class Pageable {

    private static final int MAX_SIZE = 50;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 20;

    private final int page;
    private final int size;
    private final Sort.Direction direction;

    @Builder
    private Pageable(Integer page, Integer size, Sort.Direction direction) {
        page = isNull(page) ? DEFAULT_PAGE : page;
        size = isNull(size) ? DEFAULT_SIZE : size;

        this.page = max(page, 1);
        this.size = min(size, MAX_SIZE);
        this.direction = direction;
    }

    public PageRequest of() {
        return PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");
    }

}

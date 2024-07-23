package com.gmm.infra.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static java.lang.Math.min;

@Getter
public class Pageable {

    private static final int MAX_SIZE = 50;

    private final int page;
    private final int size;
    private final Sort.Direction direction;

    @Builder
    private Pageable(int page, int size, Sort.Direction direction) {
        this.page = page <= 0 ? 1 : page;
        this.size = min(size, MAX_SIZE);
        this.direction = direction;
    }

    public PageRequest of() {
        return PageRequest.of(page - 1, size);
    }

}

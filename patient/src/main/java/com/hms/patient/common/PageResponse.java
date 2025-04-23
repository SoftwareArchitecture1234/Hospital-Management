package com.hms.patient.common;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T> {
    private final List<T> data;
    private final Paging paging;

    @Getter
    public static class Paging {
        private final int page;
        private final int limit;
        private final long total;
        private final boolean hasNext;

        public Paging(int page, int limit, long total) {
            this.page = page;
            this.limit = limit;
            this.total = total;
            this.hasNext = (long) (page + 1) * limit < total;
        }

    }

    public PageResponse(Page<T> springPage) {
        this.data = springPage.getContent();
        this.paging = new Paging(
                springPage.getNumber(),
                springPage.getSize(),
                springPage.getTotalElements()
        );
    }

}
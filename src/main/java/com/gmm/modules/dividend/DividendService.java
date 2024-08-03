package com.gmm.modules.dividend;

import com.gmm.infra.model.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DividendService {

    private final DividendReader dividendReader;
    private final DividendAppender dividendAppender;

    @Transactional
    public Long addDividend(Dividend dividend) {
        return dividendAppender.add(dividend);
    }

    public DividendWithTicker getDividendWithTicker(Long dividendId) {
        return dividendReader.read(dividendId);
    }

    public Page<Dividend> getPageOfDividend(Pageable pageable) {
        return dividendReader.page(pageable.of());
    }


}

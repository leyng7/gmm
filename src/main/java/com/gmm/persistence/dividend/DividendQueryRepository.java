package com.gmm.persistence.dividend;

import com.gmm.modules.dividend.Dividend;
import com.gmm.modules.dividend.DividendEntity;
import com.gmm.modules.dividend.DividendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DividendQueryRepository implements DividendRepository {

    private final DividendJpaRepository dividendJpaRepository;

    @Override
    public Long save(Dividend dividend) {
        var entity = new DividendEntity(
                dividend.getTickerSymbol(),
                dividend.getAmount()
        );
        return dividendJpaRepository.save(entity).getId();
    }
}

package com.gmm.modules.ticker;

import com.gmm.infra.model.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TickerService {

    private final TickerRepository tickerRepository;

    @Transactional
    public Long addTicker(TickerAdd request) {
        Ticker ticker = Ticker.builder()
                .symbol(request.symbol())
                .type(request.type())
                .build();

        return tickerRepository.save(ticker).getId();
    }

    public Page<Ticker> getPageOfTicker(Pageable pageable) {
        return tickerRepository.findAll(pageable.of());
    }

    public Ticker getTicker(Long tickerId) {
        return tickerRepository.getTicker(tickerId);
    }

    @Transactional
    public void editTicker(Long tickerId, TickerEdit tickerEdit) {
        Ticker ticker = tickerRepository.getTicker(tickerId);
        ticker.edit(tickerEdit.symbol(), tickerEdit.type());
    }

    @Transactional
    public void deleteTicker(Long tickerId) {
        tickerRepository.deleteById(tickerId);
    }


}

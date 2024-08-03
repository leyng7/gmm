package com.gmm.persistence.dividend;

import com.gmm.modules.dividend.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividendJpaRepository extends JpaRepository<DividendEntity, Long> {

}

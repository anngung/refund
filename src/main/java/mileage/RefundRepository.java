package mileage;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RefundRepository extends PagingAndSortingRepository<Refund, Long>{
    Optional<Refund> findByMemberId(Long memberId);


}
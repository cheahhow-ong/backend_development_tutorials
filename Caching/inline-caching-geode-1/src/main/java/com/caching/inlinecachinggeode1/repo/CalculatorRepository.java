package com.caching.inlinecachinggeode1.repo;

import com.caching.inlinecachinggeode1.model.Operator;
import com.caching.inlinecachinggeode1.model.ResultHolder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CalculatorRepository
        extends CrudRepository<ResultHolder, ResultHolder.ResultKey> {

    Optional<ResultHolder> findByOperandEqualsAndOperatorEquals(Number operand, Operator operator);

}

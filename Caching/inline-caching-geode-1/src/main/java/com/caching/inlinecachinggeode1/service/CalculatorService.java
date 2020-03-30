package com.caching.inlinecachinggeode1.service;

import com.caching.inlinecachinggeode1.model.Operator;
import com.caching.inlinecachinggeode1.model.ResultHolder;
import com.caching.inlinecachinggeode1.service.support.AbstractCacheableService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CalculatorService extends AbstractCacheableService {
    @Cacheable(value = "Factorials", keyGenerator = "resultKeyGenerator")
    public ResultHolder factorial(int number) {

        this.cacheMiss.set(true);

        Assert.isTrue(number >= 0L,
                String.format("Number [%d] must be greater than equal to 0", number));

        simulateLatency();

        if (number <= 2) {
            return ResultHolder.of(number, Operator.FACTORIAL, number == 2 ? 2 : 1);
        }

        int operand = number;
        int result = number;

        while (--number > 1) {
            result *= number;
        }

        return ResultHolder.of(operand, Operator.FACTORIAL, result);
    }

    @Cacheable(value = "SquareRoots", keyGenerator = "resultKeyGenerator")
    public ResultHolder sqrt(int number) {

        this.cacheMiss.set(true);

        Assert.isTrue(number >= 0,
                String.format("Number [%d] must be greater than equal to 0", number));

        simulateLatency();

        int result = Double.valueOf(Math.sqrt(number)).intValue();

        return ResultHolder.of(number, Operator.SQUARE_ROOT, result);
    }
}

package com.caching.inlinecachinggeode1.service.support;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractCacheableService {

    protected final AtomicBoolean cacheMiss = new AtomicBoolean(false);

    public boolean isCacheHit() {
        return !isCacheMiss();
    }

    public boolean isCacheMiss() {
        return this.cacheMiss.compareAndSet(true,false);
    }

    protected long delayInMilliseconds() {
        return 3000L;
    }

    protected boolean simulateLatency() {

        try {
            Thread.sleep(delayInMilliseconds());
            return true;
        }
        catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}

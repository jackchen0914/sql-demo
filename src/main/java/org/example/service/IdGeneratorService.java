package org.example.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class IdGeneratorService {

    private final AtomicLong mainIdCounter = new AtomicLong(System.currentTimeMillis());
    private final AtomicLong detailIdCounter = new AtomicLong(System.currentTimeMillis() + 100000000L);

    /**
     * 生成主表ID
     * @return
     */
    public Long generateMainId(){
        return mainIdCounter.incrementAndGet();
    }

    /**
     * 生成明细表ID 副ID
     * @return
     */
    public Long generateDetailId(){
        return detailIdCounter.incrementAndGet();
    }

    /**
     * 重置计数器(用于测试)
     * @param mainStart
     * @param detailStart
     */
    public void reset(long mainStart,long detailStart){
        mainIdCounter.set(mainStart);
        detailIdCounter.set(detailStart);
    }
}

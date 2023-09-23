package com.study.cache.caffeine.cache;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CaffeineCacheType {
    USER("userCache", 10, 10000),
    ;

    private final String cacheName;
    private final int expireTime;
    private final int maximumSize;
}

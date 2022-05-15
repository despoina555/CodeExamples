package org.despina;

import redis.clients.jedis.Jedis;

public class JedisCacheService {


    /**
     * Jedis, a client library in Java for Redis –
     * Redis is in-memory data structure store that can persist on disk as well
     * is a keystore-based data structure to persist data and can be used as a database, cache, message broker,
     */
    private Jedis jedis;
    /**
     * Initialize the cache (default: port 6379 , localhost)
     * if you have started the service on a non-default port or a remote machine,
     * you should configure it by passing the correct values as parameters into the constructor.*/
    JedisCacheService(){
        jedis = new Jedis();
    }

/** expirationTimeinSeconds =  timeout for the specified key. */
    void set(String key, String value, int expirationTimeinSeconds) {
        jedis.setex(key, expirationTimeinSeconds, value);
    }

    String get(String key) {
        String data = jedis.get(key);
        if (data != null)
            return data;

        return null;
    }

    void delKey(String keyName) {
        jedis.del(keyName);
    }

    void flushAllCachedKeys() {
        jedis.flushAll();
    }

}
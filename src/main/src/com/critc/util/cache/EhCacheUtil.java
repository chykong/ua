package com.critc.util.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;

/**
 * Ehcache配置
 *
 * @author 孔垂云
 */
public class EhCacheUtil {
    private static volatile EhCacheUtil ehCacheUtil;
    private static volatile CacheManager cacheManager;
    private static Logger log = LoggerFactory.getLogger(EhCacheUtil.class);
    private static String confPath = "/conf/ehcache.xml";

    public static EhCacheUtil getInstance() {
        if (null == ehCacheUtil) {
            synchronized (EhCacheUtil.class) {
                if (null == ehCacheUtil) {
                    ehCacheUtil = new EhCacheUtil();
                }
            }
        }
        return ehCacheUtil;
    }

    static {
        try {
            URL url = EhCacheUtil.class.getResource(confPath);
            cacheManager = CacheManager.create(url);
            log.info("ehcache初始化");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("ehcache初始化失败");
        }
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    static Cache getOrAddCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            synchronized (cacheManager) {
                cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    log.warn("Could not find cache config [" + cacheName + "], using default.");
                    cacheManager.addCacheIfAbsent(cacheName);
                    cache = cacheManager.getCache(cacheName);
                    log.info("Cache [" + cacheName + "] started.");
                }
            }
        }
        return cache;
    }

    public static void put(String cacheName, Object key, Object value) {
        getOrAddCache(cacheName).put(new Element(key, value));
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String cacheName, Object key) {
        Element element = getOrAddCache(cacheName).get(key);
        return element != null ? (T) element.getObjectValue() : null;
    }

    @SuppressWarnings("rawtypes")
    public static List getKeys(String cacheName) {
        return getOrAddCache(cacheName).getKeys();
    }

    public static void remove(String cacheName, Object key) {
        getOrAddCache(cacheName).remove(key);
    }

    public static void removeAll(String cacheName) {
        getOrAddCache(cacheName).removeAll();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            EhCacheUtil.put("sysCache", "ppa" + i, "wolegecha");
            String value = EhCacheUtil.get("sys", "ppa" + i);
            System.out.println(value);
        }
        List<String> list = EhCacheUtil.getKeys("sysCache");
        for (String str : list) {
            System.out.println(str);
        }
        Object object = EhCacheUtil.get("sysCache", "ppa1");
        System.out.println(object.toString());
    }

}

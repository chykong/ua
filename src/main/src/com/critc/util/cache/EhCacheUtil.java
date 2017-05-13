package com.critc.util.cache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ehcache配置
 * @author chykong
 *
 */
public class EhCacheUtil {
	private static volatile EhCacheUtil ehCacheUtil;
	private static volatile CacheManager cacheManager;
	private static Logger log = LoggerFactory.getLogger(EhCacheUtil.class);

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
			cacheManager = CacheManager.create();
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
		EhCacheUtil.put("sampleCache1", "ppa", "wolegecha");
		String value = EhCacheUtil.get("sampleCache1", "ppa");
		System.out.println(value);
	}

}

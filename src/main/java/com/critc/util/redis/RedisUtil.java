package com.critc.util.redis;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.critc.util.spring.SpringContextHolder;

/**
 * redis工具
 * 对象操作采用该类即可
 * @author chykong
 *
 */
//@Repository
public class RedisUtil {
	@SuppressWarnings("unchecked")
	private static RedisTemplate<Serializable, Serializable> redisTemplate = (RedisTemplate<Serializable, Serializable>) SpringContextHolder.getBean("redisTemplate");

	public static void set(final String key, Object value) {
		final byte[] vbytes = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
				return null;
			}
		});
	}

	public static void set(final String key, Object value, final long l) {
		final byte[] vbytes = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(redisTemplate.getStringSerializer().serialize(key), l, vbytes);
				return null;
			}
		});
	}

	public static <T> T get(final String key, Class<T> elementType) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
				if (connection.exists(keybytes)) {
					byte[] valuebytes = connection.get(keybytes);
					@SuppressWarnings("unchecked")
					T value = (T) SerializeUtil.unserialize(valuebytes);
					return value;
				}
				return null;
			}
		});
	}

	public static void del(final String key) {
		final byte[] keyBytes = redisTemplate.getStringSerializer().serialize(key);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.del(keyBytes);
				return null;
			}
		});
	}

	///清空所有
	public static void flushDb() {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return null;
			}
		});
	}
}

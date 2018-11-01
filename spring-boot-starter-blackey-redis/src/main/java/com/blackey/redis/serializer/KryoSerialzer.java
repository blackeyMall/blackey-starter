package com.blackey.redis.serializer;

import com.blackey.redis.util.KryoUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 *
 * Redis 使用kryo序列化
 * Created by Kaven
 * Date: 2018/3/15
 * Desc:
 */
public class KryoSerialzer implements RedisSerializer{
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return KryoUtil.serializationObject(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return KryoUtil.deserializationObject(bytes);
    }
}

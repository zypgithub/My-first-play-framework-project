package controllers;

import com.typesafe.plugin.RedisPlugin;

import redis.clients.jedis.*;

public class DatabaseOperation {
	
	public static Jedis getConnection()
	{
		return play.Play.application().plugin(RedisPlugin.class).jedisPool().getResource();
	}

}

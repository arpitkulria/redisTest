package app.redis

import java.util.concurrent.TimeUnit

import org.redisson.{RedissonClient, Config, Redisson}
import org.redisson.core.{RMap, RSet, RLock, RReadWriteLock}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RedisTest {
  val config = new Config
  config.useSingleServer().setAddress("127.0.0.1:6379")
  val redisson: RedissonClient = Redisson.create(config)

  def check(fingerprintKey: String) = {
    val lck: RLock = Redisson.create.getLock(fingerprintKey)
    lck.lock(10 , TimeUnit.SECONDS)
    val map: RMap[String, String] = redisson.getMap("testMAP")
    val putOP: String = map.put("testKey", "testValue")
    val getOP = map.get("testKey")
    println("Lock.isLocked = " + lck.isLocked)
    println("Map.put >>>>>> " + putOP)
    println("Map.get >>>>>" + getOP)
    val map2: RMap[String, String] = redisson.getMap("testMAP")
    val map3Get = map2.get("testKey")
    println("mp3 get ?>>>>> " + map3Get)
    println("mp3 set ?>>>>> " + map2.put("testKey", "xxxxxxxxxxxxxx"))
    println("mp3 get ?>>>>> " + map2.put("testKey", "xxxxxxxxxxxxxx"))
    println("map3 get ?>>>>> " + map2.get("testKey"))
    println("map get ?>>>>> " + map.get("testKey"))

    lck.unlock
    println("Lock.isLocked after unlock = " + lck.isLocked)
    }
}

//object RedisTest extends RedisTest
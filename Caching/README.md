## **Caching (using different ways: EhCache, Redis, Apache Geode)**

Tutorial:
- https://howtodoinjava.com/spring-boot2/spring-boot-cache-example/
- https://www.youtube.com/watch?v=ccfQ1j4suN0

Ehcache:

- https://www.baeldung.com/spring-boot-ehcache
- https://springframework.guru/using-ehcache-3-in-spring-boot/
- https://www.baeldung.com/spring-boot-evict-cache

Redis:

- https://dzone.com/articles/running-redis-on-windows-10 (redis installation)
- https://programmerfriend.com/ultimate-guide-to-redis-cache-with-spring-boot-2-and-spring-data-redis/

Apache Geode
- https://www.youtube.com/watch?v=qUs3ftvsEoU
- http://www.ynovytskyy.com/2018/03/15/apache-geode-pivotal-gemfire-with-spring-data.html

## **What are the difference between using EhCache v.s. Redis/Geode? When should use EhCache and when should use Redis/Geode?**

 
- You can think Redis as a shared data structure, while Ehcache is a memory block storing serialized data objects. This is the main difference.

- Redis as a shared data structure means you can put some predefined data structure (such as String, List, Set etc) in one language and retrieve it in another language. This is useful if your project is multilingual, for example: Java the backend side , and PHP the front side. You can use Redis for a shared cache. But it can only store predefined data structure, you cannot insert any Java objects you want.

- If your project is only Java, i.e. not multilingual, Ehcache is a convenient solution.

## **Redis v.s. Geode**

- Redis is primarily a standalone Key-Value store, driven by Salvatore Sanfilippo, HA and sharding options (Sentinel, Cluster) were built at a much later time. Redis is blazing fast, and if you favor performance over consistency, Redis is a good choice. Redis only security feature is password-based authentication, but you would rather secure it on transport-level which requires additional effort. There's no support besides the community or if you opt for a commercial Redis product (such as RedisLabs).

- Geode (Open-Sourced Pivotal GemFire about a year ago, nowadays GemFire is based on Geode) is a product driven by Pivotal for many years. It's built with consistency, HA, and data distribution in mind – call it an in-memory data grid. The security layer of Geode is extensible and pluggable so you can tailor it to your needs. If you want to get commercial support, then Pivotal can serve you with your needs.
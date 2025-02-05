package com.amazon.ata.inmemorycaching.prework.order;

import com.amazon.ata.inmemorycaching.prework.order.resources.OnlineOrdersDAO;
import com.amazon.ata.inmemorycaching.prework.order.resources.Order;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.List;

public class CachingOnlineOrderDAO {
    private final LoadingCache<String, List<Order>> ordersCache;

    /**
     * Constructor.
     * PARTICIPANTS: Instantiate a LoadingCache instance
     * @param ordersDAO OnlineOrdersDAO that will be used by the cache to retrieve a miss.
     */
    public CachingOnlineOrderDAO(OnlineOrdersDAO ordersDAO) {
        this.ordersCache = CacheBuilder.newBuilder()
                .build(CacheLoader.from(ordersDAO::getOrdersByUser));
    }

    /**
     * Gets all the orders associated with a particular user.
     * @param userId user to retrieve orders for
     * @return List of orders
     */
    public List<Order> getOrdersByUser(String userId) {

        return this.ordersCache.getUnchecked(userId);

    }
}

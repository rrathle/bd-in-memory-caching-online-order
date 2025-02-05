import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
import java.util.List;

public class CachingOnlineOrderDAO {

private final LoadingCache<String, List<Order>> orderCache;

    /**
     * Constructor.
     * PARTICIPANTS: Instantiate a LoadingCache instance
     * @param ordersDAO OnlineOrdersDAO that will be used by the cache to retrieve a miss.
     */
    public CachingOnlineOrderDAO(OnlineOrdersDAO ordersDAO) {
        orderCache = CacheBuilder.newBuilder()
                .build(CacheLoader.from(ordersDAO::getOrdersByUser));

    }

    /**
     * Gets all the orders associated with a particular user.
     * PARTICIPANTS: call the cache to get the list of orders
     * @param userId user to retrieve orders for
     * @return List of orders
     */
    public List<Order> getOrdersByUser(String userId) {

        return orderCache.getUnchecked(userId);
    }
}

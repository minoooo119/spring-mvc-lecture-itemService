package hello.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    //HashMap 여러개 접근시에 사용하면 안된다.
    //그떄는 ConcurrentHashMap 사용해야함
    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findByAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long id, Item updateParam) {
        Item findItem = findById(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}

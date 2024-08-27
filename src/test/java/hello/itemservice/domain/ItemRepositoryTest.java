package hello.itemservice.domain;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }
    @Test
    void save() {
        //given
        Item itemA = new Item("itemA", 1000, 3);

        //when
        Item saveItemA = itemRepository.save(itemA);

        //then

        Item findItemA = itemRepository.findById(saveItemA.getId());
        assertThat(findItemA).isEqualTo(saveItemA);

    }

    @Test
    void findByAll() {
        //given
        Item itemA = new Item("itemA", 1000, 3);
        Item itemB = new Item("itemB", 2000, 4);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        
        //when
        List<Item> result = itemRepository.findByAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);

    }

    @Test
    void update() {
        //given
        Item itemA = new Item("itemA", 1000, 3);
        Item saveItemA = itemRepository.save(itemA);
        Long ItemId= saveItemA.getId();

        //when
        Item updateParam = new Item("itemB", 2000, 4);
        itemRepository.update(ItemId, updateParam);

        //then
        Item result = itemRepository.findById(saveItemA.getId());

        assertThat(result.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(result.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(result.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    @Test
    void clearStore() {
        //given
        Item item1 = new Item("1", 10, 1);
        Item item2 = new Item("2", 20, 2);
        Item item3 = new Item("3", 30, 3);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        //when
        itemRepository.clearStore();
        List<Item> items = itemRepository.findByAll();

        //then
        assertThat(items.size()).isEqualTo(0);
    }
}
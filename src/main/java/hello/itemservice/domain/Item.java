package hello.itemservice.domain;

import lombok.Data;

@Data
//DTO 의 경우 Data 써도 괜찮다.
//Data Annotation 이 원하는대로 동작하지 않을 가능성이 존재한다.
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

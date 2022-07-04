package study.hellojpa.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;

@Entity
@Table( name = "ORDER_ITEM" )
@Getter
@Setter
public class OrderItem{

    @Id
    @GeneratedValue
    @Column( name = "ORDER_ITEM_ID" )
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ITEM_ID" )
    private Item item; //주문상품

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ORDER_ID" )
    private Order order;

    private int orderPrice;

    private int count;

    // 생성 메소드
    public static OrderItem createOrderItem( Item item , int orderPrice , int count ){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem( item );
        orderItem.setOrderPrice( orderPrice );
        orderItem.setCount( count );

        item.removeStock( count );
        return orderItem;
    }

    // 비즈니스 로직
    // 주문취소
    public void cancel(){
        getItem().addStock( count );
    }

    // 조회 로직
    // 주문상품 전체 가격조회
    public int getToTalPrice(){
        return getOrderPrice() * getCount();
    }
}

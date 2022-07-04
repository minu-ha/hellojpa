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
}

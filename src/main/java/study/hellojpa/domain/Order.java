package study.hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table( name = "ORDERS" )
@Getter
@Setter
public class Order{

    @Id
    @GeneratedValue
    @Column( name = "ORDER_ID" )
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "MEMBER_ID" )
    private Member member; //주문회원

    @OneToMany( mappedBy = "order", cascade = CascadeType.ALL )
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Delivery delivery; //배송정보

    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 연관관계 메소드
    public void setMember( Member member ){
        this.member = member;
        member.getOrders().add( this );
    }

    public void addOrderItem( OrderItem orderItem ){
        orderItems.add( orderItem );
        orderItem.setOrder( this );
    }

    public void setDelivery( Delivery delivery ){
        this.delivery = delivery;
        delivery.setOrder( this );
    }
}


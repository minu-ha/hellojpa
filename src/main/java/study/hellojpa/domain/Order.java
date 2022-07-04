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

    @Enumerated( EnumType.STRING )
    private OrderStatus status;

    // 생성 메소드
    public static Order createOrder( Member member , Delivery delivery , OrderItem... orderItems ){
        Order order = new Order();
        order.setMember( member );
        order.setDelivery( delivery );
        for ( OrderItem orderItem : orderItems ) {
            order.addOrderItem( orderItem );
        }
        order.setStatus( OrderStatus.ORDER );
        order.setOrderDate( new Date() );
        return order;
    }

    // 비즈니스 로직
    // 주문취소
    public void cancel(){
        if ( delivery.getStatus() == DeliveryStatus.COMP ) {
            throw new RuntimeException( "이미 배송완료된 상품입니다. 취소 불가능" );
        }
        this.setStatus( OrderStatus.CANCEL );
        for ( OrderItem orderItem : orderItems ) {
            orderItem.cancel();
        }
    }

    // 조회로직
    public int getTotalPrice(){
        int totalPrice = 0;
        for ( OrderItem orderItem : orderItems ) {
            totalPrice += orderItem.getToTalPrice();
        }
        return totalPrice;
    }

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


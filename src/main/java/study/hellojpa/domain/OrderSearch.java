package study.hellojpa.domain;

import lombok.Getter;
import lombok.Setter;


import static study.hellojpa.domain.OrderSpec.memberNameLike;
import static study.hellojpa.domain.OrderSpec.orderStatusEq;

import org.springframework.data.jpa.domain.Specification;


@Getter
@Setter
public class OrderSearch{

    private String memberName;
    private OrderStatus orderStatus;

    public Specification<Order> toSpecification(){
        return Specification.where( memberNameLike( memberName ) ).and(
                orderStatusEq( orderStatus ) );
    }


}

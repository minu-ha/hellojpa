package study.hellojpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.hellojpa.domain.Member;
import study.hellojpa.domain.Order;
import study.hellojpa.domain.OrderSearch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository{

    @PersistenceContext
    EntityManager em;

    public void save( Order order ){
        em.persist( order );
    }

    public Order findOne( Long id ){
        return em.find( Order.class , id );
    }

    public List<Order> findAll( OrderSearch orderSearch ){

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery( Order.class );
        Root<Order> o = query.from( Order.class );

        List<Predicate> criteria = new ArrayList<>();

        //주문 상태 검색
        if ( orderSearch.getOrderStatus() != null ) {
            Predicate status = cb.equal( o.get( "status" ) , orderSearch.getOrderStatus() );
            criteria.add( status );
        }

        //회원 이름 검색
        if ( StringUtils.hasText( orderSearch.getMemberName() ) ) {
            //회원과 조인
            Join<Order, Member> m = o.join( "member" , JoinType.INNER );
            Predicate name = cb.like( m.<String> get( "name" ) ,
                    "%" + orderSearch.getMemberName() + "%" );
            criteria.add( name );
        }

        query.where( cb.and( criteria.toArray( new Predicate[ criteria.size() ] ) ) );
        TypedQuery<Order> typedQuery = em.createQuery( query ).setMaxResults( 1000 );
        return typedQuery.getResultList();
    }

}



























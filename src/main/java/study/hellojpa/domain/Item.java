package study.hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "DTYPE" )
@Getter
@Setter
public class Item{

    @Id
    @GeneratedValue
    @Column( name = "ITEM_ID" )
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany( mappedBy = "items" )
    private List<Category> categories = new ArrayList<>();

    //비즈니스 로직
    public void addStock( int quantity ){
        this.stockQuantity += quantity;
    }

    public void removeStock( int quantity){
        int restStock = this.stockQuantity - quantity;
        if ( restStock < 0 ) {
            throw new RuntimeException( "need more stock" );
        }
        this.stockQuantity = restStock;
    }

}

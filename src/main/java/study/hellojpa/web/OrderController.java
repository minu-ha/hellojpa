package study.hellojpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.hellojpa.domain.Item;
import study.hellojpa.domain.Member;
import study.hellojpa.service.ItemService;
import study.hellojpa.service.MemberService;
import study.hellojpa.service.OrderService;

import java.util.List;

@Controller
public class OrderController{

    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @GetMapping( "/order" )
    public String createForm( Model model ){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute( "members" , members );
        model.addAttribute( "items" , items );
        return "order/orderForm";
    }

    @PostMapping( "/order" )
    public String order( @RequestParam Long memberId , @RequestParam Long itemId ,
                         @RequestParam int count ){
        orderService.order( memberId , itemId , count );
        return "redirect:/orders";
    }


}




























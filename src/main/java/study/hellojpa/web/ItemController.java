package study.hellojpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.hellojpa.domain.Book;
import study.hellojpa.domain.Item;
import study.hellojpa.service.ItemService;

import java.util.List;

@Controller
public class ItemController{

    @Autowired
    ItemService itemService;

    @GetMapping( "/items" )
    public String list( Model model ){
        List<Item> items = itemService.findItems();
        model.addAttribute( "items" , items );
        return "items/itemList";
    }

    @GetMapping( "/items/new" )
    public String createForm(){
        return "items/createItemForm";
    }

    @PostMapping( "/items/new" )
    public String create( Book item ){
        itemService.saveItem( item );
        return "redirect:/items";
    }

    @GetMapping( "/items/{itemId}/edit" )
    public String updateItemForm( @PathVariable Long itemId , Model model ){
        Item item = itemService.findOne( itemId );
        model.addAttribute( "item" , item );
        return "items/updateItemForm";
    }

    @PostMapping( "/items/{itemId}/edit" )
    public String updateItem( @ModelAttribute Book item ){
        itemService.saveItem( item );
        return "redirect:/items";
    }
}

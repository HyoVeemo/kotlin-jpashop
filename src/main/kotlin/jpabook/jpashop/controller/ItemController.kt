package jpabook.jpashop.controller

import jakarta.validation.Valid
import jpabook.jpashop.domain.Book
import jpabook.jpashop.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemController(
    private val itemService: ItemService
) {

    @GetMapping("/items/new")
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm())
        return "items/createItemForm"
    }

    @PostMapping("/items/new")
    fun create(@Valid bookForm: BookForm): String {
        val book = Book(
            name = bookForm.name,
            price = bookForm.price,
            stockQuantity = bookForm.stockQuantity,
            author = bookForm.author
        )

        itemService.saveItem(book)
        return "redirect:/"
    }

    @GetMapping("/items")
    fun list(model: Model): String {
        val items = itemService.findItems()
        model.addAttribute("items", items)
        return "items/itemList"
    }

    @GetMapping("items/{itemId}/edit")
    fun updateItemForm(@PathVariable("itemId") itemId: Long, model: Model): String {
        val book = itemService.findOne(itemId) as Book
        val form = BookForm(book.id, book.name, book.price, book.stockQuantity, book.author, book.isbn)
        model.addAttribute("form", form)

        return "items/updateItemForm"
    }

    @PostMapping("items/{itemId}/edit")
    fun updateItem(@ModelAttribute("form") bookForm: BookForm): String {
        // 준영속 엔티티
//        val book =
//            Book(bookForm.id, bookForm.name, bookForm.price, bookForm.stockQuantity, bookForm.author, bookForm.isbn)
//        itemService.saveItem(book)
        // todo: dto 사용
        itemService.updateItem(bookForm.id, bookForm.name, bookForm.price, bookForm.stockQuantity)
        return "redirect:/items"
    }
}
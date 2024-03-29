package jpabook.jpashop.service

import jpabook.jpashop.domain.Item
import jpabook.jpashop.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService(
    private val itemRepository: ItemRepository
) {

    @Transactional
    fun saveItem(item: Item) {
        itemRepository.save(item)
    }

    fun findItems(): List<Item> {
        return itemRepository.findAll()
    }

    fun findOne(id: Long): Item {
        return itemRepository.findOne(id)
    }

    fun updateItem(id: Long, name: String, price: Int, stockQuantity: Int) {
        val findItem = itemRepository.findOne(id)
        findItem.name = name
        findItem.price = price
        findItem.stockQuantity = stockQuantity
    }
}
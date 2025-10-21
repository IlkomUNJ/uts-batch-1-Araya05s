import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel

data class Item(val id: Int, val name: String)

class studentsViewModel : ViewModel() {
    companion object {
        // ✅ The observable state holder
        private val _dataList = MutableStateFlow<List<Item>>(emptyList())

        val dataList: StateFlow<List<Item>> = _dataList.asStateFlow()

        fun addItem(newItem: Item) {
            _dataList.update { currentList ->
                currentList + newItem
            }
        }

        // Another example: clearing the list
        fun clearList() {
            _dataList.value = emptyList()
        }
    }
}
package com.example.exam_cse224

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactViewModel : ViewModel() {

    private val allContacts = listOf(
        Contact(1, "Alice Smith", "+1 234 567 890"),
        Contact(2, "Bob Johnson", "+1 345 678 901"),
        Contact(3, "Charlie Brown", "+1 456 789 012"),
        Contact(4, "David Wilson", "+1 567 890 123"),
        Contact(5, "Eva Davis", "+1 678 901 234")
    )

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _contacts = MutableStateFlow(allContacts)
    val contacts: StateFlow<List<Contact>> = _contacts.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _contacts.value = if (query.isBlank()) {
            allContacts
        } else {
            allContacts.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.phone.contains(query, ignoreCase = true)
            }
        }
    }
}

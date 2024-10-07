package org.example.project.coffeecmp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.coffeecmp.domain.Coffee
import org.example.project.coffeecmp.domain.CoffeeDataSource

class CoffeeViewModel(
    private val coffeeDataSource: CoffeeDataSource
): ViewModel() {
    //DateTime Formatter
    private val currentMoment: Instant = Clock.System.now()
    private val datetimeInUtc: LocalDateTime = currentMoment.toLocalDateTime(TimeZone.UTC)
    private val datetimeInSystemZone: LocalDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
    val formattedDate: String = datetimeInUtc.date.toString()

    //Coffee values
    private val _coffees = MutableStateFlow<List<Coffee>>(emptyList())
    val coffees: StateFlow<List<Coffee>> get() = _coffees.asStateFlow()

    //Detail values
    private val _coffee = MutableStateFlow<Coffee?>(null)
    val coffee: StateFlow<Coffee?> = _coffee

    // State to hold date, location, description and rating
    private val _newDate = MutableStateFlow(formattedDate)
    val newDate: StateFlow<String> get() = _newDate

    private val _newLocation = MutableStateFlow("")
    val newLocation: StateFlow<String> get() = _newLocation

    private val _newDescription = MutableStateFlow("")
    val newDescription: StateFlow<String> get() = _newDescription

    private val _newRatingBar = MutableStateFlow(0)
    val newRatingBar: StateFlow<Int> get() = _newRatingBar

    // State to update date, location, description and rating
    private val _updateDate = MutableStateFlow(formattedDate)
    val updateDate: StateFlow<String> get() = _updateDate

    private val _updateLocation = MutableStateFlow("")
    val updateLocation: StateFlow<String> get() = _updateLocation

    private val _updateDescription = MutableStateFlow("")
    val updateDescription: StateFlow<String> get() = _updateDescription

    private val _updateRatingBar = MutableStateFlow(0)
    val updateRatingBar: StateFlow<Int> get() = _updateRatingBar

    // StateFlow for sort order
    private val _sortOrder = MutableStateFlow("None")
    val sortOrder: StateFlow<String> get() = _sortOrder

    // Functions to change sort order
    fun sortByTitle() {
        _sortOrder.value = "Title"
    }

    fun sortByDate() {
        _sortOrder.value = "Date"
    }

    fun sortByRating() {
        _sortOrder.value = "Rating"
    }

    //Error Message
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage.asStateFlow()

    fun clearError() {
        _errorMessage.value = null
    }

    // Functions to set the fields
    init {
        loadCoffees()
    }

    fun setDate(newDate: String) {
        _newDate.value = newDate
    }

    fun setLocation(newLocation: String) {
        _newLocation.value = newLocation
    }

    fun setDescription(newDescription: String) {
        _newDescription.value = newDescription
    }

    fun setRating(newRating: Int) {
        _newRatingBar.value = newRating
    }

    private fun loadCoffees() {
        viewModelScope.launch {
            try {
                _coffees.value = coffeeDataSource.getAllCoffees()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load database: ${e.message}"
            }
        }
    }

    // Functions to update the fields
    fun updateDate(updateDate: String) {
        _updateDate.value = updateDate
    }

    fun updateLocation(updateLocation: String) {
        _updateLocation.value = updateLocation
    }

    fun updateDescription(updateDescription: String) {
        _updateDescription.value = updateDescription
    }

    fun updateRatingBar(updateRating: Int) {
        _updateRatingBar.value = updateRating
    }

    // Function to save coffee
    fun saveCoffee(title: String) {
        viewModelScope.launch {
            try {
                coffeeDataSource.insertCoffee(
                    Coffee(
                        title = title,
                        date = _newDate.value,
                        location = _newLocation.value,
                        description = _newDescription.value,
                        ratingBar = _newRatingBar.value
                    )
                )
            } catch (e: Exception) {
                _errorMessage.value = "Failed to save data: ${e.message}"
            }
        }
    }

    fun getCoffeeById(id: Long) {
        viewModelScope.launch {
            val coffee = coffeeDataSource.getCoffeeById(id)
            _coffee.value = coffee
            coffee?.let {
                _updateDate.value = it.date
                _updateLocation.value = it.location
                _updateDescription.value = it.description
                _updateRatingBar.value = it.ratingBar
            }
        }
    }

    fun deleteCoffeeById(id: Long) {
        viewModelScope.launch {
            coffeeDataSource.deleteCoffeeById(id)
            loadCoffees()
        }
    }

    fun updateCoffee(id: Long, newDate: String, newLocation: String, newDescription: String, newRating: Long) {
        viewModelScope.launch {
            try {
                coffeeDataSource.updateCoffee(id, newDate, newLocation, newDescription, newRating)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to update data: ${e.message}"
            }
        }
    }
}
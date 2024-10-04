package org.example.project.coffeecmp

import androidx.compose.ui.platform.LocalFocusManager
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
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.byUnicodePattern
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

    private val _coffees = MutableStateFlow<List<Coffee>>(emptyList())
    val coffees: StateFlow<List<Coffee>> get() = _coffees.asStateFlow()

    // State to hold date, location, description and rating
    private val _newDate = MutableStateFlow(formattedDate)
    val newDate: StateFlow<String> get() = _newDate

    private val _newLocation = MutableStateFlow("")
    val newLocation: StateFlow<String> get() = _newLocation

    private val _newDescription = MutableStateFlow("")
    val newDescription: StateFlow<String> get() = _newDescription

    private val _newRatingBar = MutableStateFlow(0)
    val newRatingBar: StateFlow<Int> get() = _newRatingBar

    //Error Message
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage.asStateFlow()

    fun clearError() {
        _errorMessage.value = null
    }

    // Functions to update the fields
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

    fun deleteCoffeeById(id: Long) {
        viewModelScope.launch {
            coffeeDataSource.deleteCoffeeById(id)
            loadCoffees()
        }
    }

    fun updateCoffee() {

    }
}
package com.next.goldentime.ui.components.screenviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.next.goldentime.R
import kotlinx.coroutines.flow.*


// AMBULANCES
data class TopAmbulances(
    val ambImage: Int,
    val name: String,
    val contact: String,
    val location: String,
)

val topAmbulances =
    listOf(
        TopAmbulances(
            ambImage = R.drawable.ambulance_mbra,
            name = "Mbarara Regional Referral Ambulance",
            contact = "0701995356",
            location = "Mbarara Town Council",
        ),
        TopAmbulances(
            ambImage = R.drawable.ambulance_red,
            name = "Red Cross Ambulance",
            contact = "0414258701",
            location = "Maternal, Newborn, and Child Health Institute",
        ),
        TopAmbulances(
            ambImage = R.drawable.ambulance_ruharo,
            name = "Ruharo Mission Ambulance",
            contact = "0393248462",
            location = "Mbarara Bushenyi Road",
        ),
        TopAmbulances(
            ambImage = R.drawable.ambulance_maya,
            name = "Mayanja Memorial Ambulance",
            contact = "0392962665",
            location = "Kabale-Mbarara Road",
        ),
        TopAmbulances(
            ambImage = R.drawable.ambulance_plaza,
            name = "Mbarara Doctors' Plaza Ambulance",
            contact = "0702336110",
            location = "Opposite Bank of Uganda, Adit Mall",
        ),
        TopAmbulances(
            ambImage = R.drawable.ambulance_bash,
            name = "Divine Mercy Ambulance",
            contact = "0772618751",
            location = "Rwebikona",
        ),
    )

// AMBULANCES
class AmbulancesViewModel: ViewModel(){

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _ambulances = MutableStateFlow(allambulances)

    val ambulances = searchText
        .combine(_ambulances) { text, ambulances ->
            if (text.isBlank()) {
                ambulances
            } else {
                ambulances.filter {
                    it.doesMatchSearchuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _ambulances.value
        )

    fun onSearchTermChange(text: String) {
        _searchText.value = text
    }
}

data class Ambulances(
    val ambImage: Int,
    val name: String,
    val contact: String,
) {
    fun doesMatchSearchuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "${name}${contact}",
            "${name.first()} ${contact}",
            "${name} ${contact.first()}",
            "${name} ${contact}",
            "${name} ${contact.first()}",
            "${name.first()} ${contact}",
            "${name.first()} ${contact.first()}"
        )
        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}

val allambulances =
    listOf(
        Ambulances(
            ambImage = R.drawable.ambulance_mbra,
            name = "Mbarara Referral",
            contact = "0701995356",
        ),
        Ambulances(
            ambImage = R.drawable.ambulance_red,
            name = "Red Cross Ambulance",
            contact = "0414258701",
        ),
        Ambulances(
            ambImage = R.drawable.ambulance_ruharo,
            name = "Ruharo Mission",
            contact = "0393248462",
        ),
        Ambulances(
            ambImage = R.drawable.ambulance_plaza,
            name = "Mbarara Doctors' Plaza",
            contact = "0702336110",
        ),

        Ambulances(
            ambImage = R.drawable.ambulance_maya,
            name = "Mayanja Memorial",
            contact = "0392962665",
        ),
        Ambulances(
            ambImage = R.drawable.ambulance_bash,
            name = "Divine Mercy",
            contact = "0772618751",
        ),
        Ambulances(
            ambImage = R.drawable.ambulance_city,
            name = "City Medical",
            contact = "0772618751",
        ),
        Ambulances(
            ambImage = R.drawable.ambulance_holy,
            name = "Holy Innocents",
            contact = "0776164640",
        )
    )

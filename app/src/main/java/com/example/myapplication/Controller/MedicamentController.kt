package com.example.porjetmobile2025.Controller

import com.example.porjetmobile2025.Model.Medicament

class MedicamentController {

    fun getMedicaments(): List<Medicament> {
        return listOf(
            Medicament("Paracétamol", "Soulage la douleur", "500mg"),
            Medicament("Ibuprofène", "Anti-inflammatoire", "200mg")
        )
    }
}

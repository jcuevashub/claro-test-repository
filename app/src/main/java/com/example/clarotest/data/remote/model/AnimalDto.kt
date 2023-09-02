package com.example.clarotest.data.remote.model

import com.example.clarotest.domain.models.EntryDetails

data class AnimalDto(
    val count: Int,
    val entries: List<EntryDetails>
)

fun AnimalDto.toListCharacters(): List<EntryDetails> {
    val resultEntries = entries.mapIndexed { _, entries ->
        EntryDetails(
            API = entries.API,
            Category = entries.Category,
            Description = entries.Description,
            Auth = entries.Auth,
            HTTPS = entries.HTTPS,
            Link = entries.Link,
            Cors = entries.Cors
        )
    }
    return resultEntries
}


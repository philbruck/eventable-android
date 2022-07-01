package com.eventable.model

import com.google.firebase.firestore.PropertyName

data class Event (
    var description: String = "",
    @get: PropertyName("start_time") @set:PropertyName("start_time") var starttime: String = "",
    var creator: String = "",
    @get: PropertyName("event_id") @set:PropertyName("event_id")var eventId: String = "",
    var location: String = "",
    var name: String = "",
    var questions: List<String>? = null,
    var date: String = "",
    @get: PropertyName("creationTimeMs") @set:PropertyName("creationTimeMs")var creationTimeMs: Long = 0,
    var votes: List<String>? = null
)

package com.eventable.model

import com.google.firebase.firestore.PropertyName

data class Answer (
    var uid: String = "",
    var answer: String = "",
    @get: PropertyName("event_id") @set:PropertyName("event_id")var eventId: String = "",
    @get: PropertyName("question_index") @set:PropertyName("question_index")var questionIndex: String = ""
)

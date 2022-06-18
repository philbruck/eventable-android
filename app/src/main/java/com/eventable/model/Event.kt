package com.eventable.model

data class Event (
    var descripton: String = "",
    var beginTime: String = "",
    var creator: String = "",
    var eventsId: String = "",
    var location: String = "",
    var name: String = "",
    var questions: List<String>? = null,
    var user: User? = null
)
package org.fm.trackrecord.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue
    var id: Long? = null,
    var name: String,
)
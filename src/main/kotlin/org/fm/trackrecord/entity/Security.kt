package org.fm.trackrecord.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "securities")
class Security(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var ticker: String,

    var name: String,
    var currency: String,
    var currentPrice: Double? = null

) : Serializable

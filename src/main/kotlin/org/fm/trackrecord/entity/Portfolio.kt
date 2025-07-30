package org.fm.trackrecord.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "portfolios")
class Portfolio(
    @Id @GeneratedValue
    var id: Long? = null,

    var name: String,
    var currency: String,

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    var cashFlows: MutableList<CashFlow> = mutableListOf(),

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    var positions: MutableList<Position> = mutableListOf(),

    @OneToMany(mappedBy = "portfolio", cascade = [CascadeType.ALL], orphanRemoval = true)
    var journalEntries: MutableList<JournalEntry> = mutableListOf()

) : Serializable

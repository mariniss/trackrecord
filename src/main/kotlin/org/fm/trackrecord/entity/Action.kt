package org.fm.trackrecord.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

@Entity
class Action(
    @Id @GeneratedValue
    var id: Long? = null,

    var date: LocalDate,
    @Enumerated(EnumType.STRING)
    var type: ActionType,
    var quantity: BigDecimal? = null,
    var price: BigDecimal? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Journal_entry_id")
    var journalEntry: JournalEntry? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    var position: Position? = null

) : Serializable

enum class ActionType {
    BUY,
    SELL,
    NO_ACTION
}

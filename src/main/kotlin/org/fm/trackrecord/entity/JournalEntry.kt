package org.fm.trackrecord.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import java.io.Serializable
import java.time.LocalDate

@Entity
class JournalEntry(
    @Id @GeneratedValue
    var id: Long? = null,

    var entryDate: LocalDate,
    var title: String,
    @Column(length = 8000)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    var portfolio: Portfolio? = null,

    @OneToMany(mappedBy = "journalEntry", cascade = [CascadeType.ALL], orphanRemoval = true)
    var actions: MutableList<Action> = mutableListOf()

) : Serializable


package org.fm.trackrecord.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "cash_flows")
class CashFlow(
    @Id @GeneratedValue
    var id: Long? = null,

    var date: LocalDate,
    var amount: BigDecimal,

    @Enumerated(EnumType.STRING)
    var type: CashFlowType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    var portfolio: Portfolio

) : Serializable

enum class CashFlowType{
    INFLOW,
    OUTFLOW
}

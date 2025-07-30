package org.fm.trackrecord.dao

import org.fm.trackrecord.entity.Portfolio
import org.springframework.data.jpa.repository.JpaRepository

interface PortfolioDao : JpaRepository<Portfolio, Long>

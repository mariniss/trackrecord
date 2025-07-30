package org.fm.trackrecord.dao

import org.fm.trackrecord.entity.Position
import org.springframework.data.jpa.repository.JpaRepository

interface PositionDao : JpaRepository<Position, Long>

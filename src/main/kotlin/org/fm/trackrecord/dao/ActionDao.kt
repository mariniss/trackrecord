package org.fm.trackrecord.dao

import org.fm.trackrecord.entity.Action
import org.springframework.data.jpa.repository.JpaRepository

interface ActionDao : JpaRepository<Action, Long>

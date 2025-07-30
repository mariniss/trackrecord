package org.fm.trackrecord.dao

import org.fm.trackrecord.entity.Security
import org.springframework.data.jpa.repository.JpaRepository

interface SecurityDao : JpaRepository<Security, Long>

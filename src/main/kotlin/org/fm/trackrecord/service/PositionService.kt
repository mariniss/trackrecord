package org.fm.trackrecord.service

import org.fm.trackrecord.dao.PositionDao
import org.fm.trackrecord.entity.Position
import org.springframework.stereotype.Service

@Service
class PositionService(private val positionDao: PositionDao) {

    fun findAll(): MutableList<Position> = positionDao.findAll()
}

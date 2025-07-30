package org.fm.trackrecord.service

import org.fm.trackrecord.dao.ActionDao
import org.fm.trackrecord.entity.Action
import org.springframework.stereotype.Service

@Service
class ActionService(private val actionDao: ActionDao) {

    fun findAll(): MutableList<Action> = actionDao.findAll()
}

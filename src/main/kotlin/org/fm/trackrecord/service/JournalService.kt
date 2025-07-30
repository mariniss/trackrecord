package org.fm.trackrecord.service

import org.fm.trackrecord.dao.JournalDao
import org.fm.trackrecord.entity.JournalEntry
import org.springframework.stereotype.Service

@Service
class JournalService(private val journalDao: JournalDao) {

    fun findAll(): MutableList<JournalEntry> = journalDao.findAll()
}

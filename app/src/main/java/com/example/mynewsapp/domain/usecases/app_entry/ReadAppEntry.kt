package com.example.mynewsapp.domain.usecases.app_entry

import com.example.mynewsapp.domain.manager.LocalUserManager

class ReadAppEntry (
    private val localUserManger: LocalUserManager
) {

    suspend operator fun invoke(): kotlinx.coroutines.flow.Flow<Boolean>{
       return localUserManger.readAppEntry()
    }

}
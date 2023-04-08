package com.ugurinci.rickandmorty.util

object StringUtil {

    fun getLastWord(string: String): String {
        return string.substring(string.lastIndexOf("/") + 1)
    }

    fun getLastWordInt(string: String): Int {
        return getLastWord(string).toInt()
    }

    fun getLastWordList(stringList: List<String>): List<String> {
        val list = mutableListOf<String>()
        stringList.forEach {
            list.add(getLastWord(it))
        }
        return list
    }

    fun getLastWordIntList(stringList: List<String>): List<Int> {
        val list = mutableListOf<Int>()
        stringList.forEach {
            list.add(getLastWordInt(it))
        }
        return list
    }
}
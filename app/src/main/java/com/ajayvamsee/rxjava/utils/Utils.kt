package com.ajayvamsee.rxjava.utils

import com.ajayvamsee.rxjava.model.ApiUser
import com.ajayvamsee.rxjava.model.User

/**
 * Created by Ajay Vamsee on 8/12/2022.
 * Time : 20:23 HRS
 */
object Utils {

    fun getApiUserList(): List<ApiUser> {

        val apiUserList = ArrayList<ApiUser>()

        val apiUserOne = ApiUser(firstName = "Ajay", secondName = "vamsee")
        apiUserList.add(apiUserOne)

        val apiUserTwo = ApiUser(firstName = "Janishar", secondName = "Ali")
        apiUserList.add(apiUserTwo)

        val apiUserThree = ApiUser(firstName = "Anand", secondName = "Gaurav")
        apiUserList.add(apiUserThree)

        return apiUserList
    }

    fun getUserListWhoLovesCricket(): List<User> {

        val userList = ArrayList<User>()

        val userOne = User(id = 1, firstName = "Ajay", secondName = "Vamsee")
        userList.add(userOne)

        val userTwo = User(id = 2, firstName = "Ajay1", secondName = "Vamsee1")
        userList.add(userTwo)

        return userList
    }

    fun getUserListWhoLovesFootball(): List<User> {
        val userList = ArrayList<User>()

        val userOne = User(firstName = "Ajay", secondName = "Vamsee")
        userList.add(userOne)

        val userTwo = User(firstName = "Ajay1", secondName = "Vamsee1")
        userList.add(userTwo)

        return userList
    }

    fun convertApiUserListToUserList(apiUserList: List<ApiUser>): List<User> {
        val userList = ArrayList<User>()

        for (apiUser in apiUserList) {
            val user = User(
                apiUser.id,
                apiUser.firstName,
                apiUser.secondName
            )
            userList.add(user)
        }
        return userList
    }

    fun filterUserWhoLovesBoth(cricketFans: List<User>, footballFans: List<User>): List<User> {
        val userWhoLovesBoth = ArrayList<User>()

        for (footBallFan in footballFans) {
            if (cricketFans.contains(footBallFan)) {
                userWhoLovesBoth.add(footBallFan)
            }
        }
        return userWhoLovesBoth
    }
}
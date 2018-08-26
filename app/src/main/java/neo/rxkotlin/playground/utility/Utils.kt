package neo.rxkotlin.playground.utility

import neo.rxkotlin.playground.model.ApiUser
import neo.rxkotlin.playground.model.User

/**
 * @author Naveen T P
 * @since 25/08/18
 */

class Utils {
    fun getApiUsersList(): List<ApiUser> {
        val apiUser = ArrayList<ApiUser>()

        val user1 = ApiUser(1, "Naveen", "T P")
        apiUser.add(user1)

        val user2 = ApiUser(2, "Ram", "Kumar")
        apiUser.add(user2)
        return apiUser
    }

    fun convertApiUserToUser(apiUser: List<ApiUser>): List<User> {
        val user = ArrayList<User>()
        apiUser.forEach {
            user.add(User(it.id, "${it.firstName} ${it.lastName}"))
        }
        return user
    }

    fun getCricketLovers(): List<User> {
        val cricketLovers = ArrayList<User>()
        cricketLovers.add(User(1, "Naveen"))
        cricketLovers.add(User(2, "Raghav"))
        return cricketLovers
    }

    fun getFootballLovers(): List<User> {
        val footballLovers = ArrayList<User>()
        footballLovers.add(User(1, "Naveen"))
        footballLovers.add(User(3, "Chandan"))
        return footballLovers
    }

    fun filterUserWhoLovesBothGames(cricketLovers: List<User>, footballLovers: List<User>): List<User> {
        return cricketLovers.filterIndexed { index, user -> footballLovers.get(index).id == user.id }
    }
}

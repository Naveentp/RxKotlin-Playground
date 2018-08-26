package neo.rxkotlin.playground.model

/**
 * @author Naveen T P
 * @since 25/08/18
 */

data class User(val id: Long,
                val name: String,
                val isFollowing: Boolean = false)
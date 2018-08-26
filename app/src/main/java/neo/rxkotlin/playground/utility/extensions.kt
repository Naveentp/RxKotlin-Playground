package neo.rxkotlin.playground.utility

import android.app.Activity
import android.content.Intent
import android.widget.TextView
import kotlin.reflect.KClass

/**
 * @author Naveen T P
 * @since 25/08/18
 */

fun <T : Activity> KClass<T>.start(activity: Activity, finish: Boolean = false) {
    Intent(activity, this.java).apply {
        activity.startActivity(this)
    }
    if (finish) {
        activity.finish()
    }
}

fun TextView.appendText(value: String) {
    this.append(value)
    this.append("\n\n")
}
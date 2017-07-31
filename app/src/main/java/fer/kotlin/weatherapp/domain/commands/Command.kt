package fer.kotlin.weatherapp.domain.commands

/**
 * Created by Default on 30/07/2017.
 */
interface Command<out T> {
    fun execute(): T
}
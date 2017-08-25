package fer.kotlin.weatherapp.extensions

import org.jetbrains.anko.db.SelectQueryBuilder

/**
 * Created by Default on 10/08/2017.
 */
fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()

inline fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    for (element in this) {
        val result = predicate(element)
        if (result != null) return result
    }
    throw NoSuchElementException("No element matching predicate was found.")
}

fun SelectQueryBuilder.byId(id: Long) = whereSimple("_id = ?", id.toString())
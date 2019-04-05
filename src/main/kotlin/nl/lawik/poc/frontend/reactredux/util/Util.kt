package nl.lawik.poc.frontend.reactredux.util

import redux.Reducer
import redux.combineReducers
import kotlin.reflect.KProperty1


fun <S, A, R> combineReducers(reducers: Map<KProperty1<S, R>, Reducer<*, A>>): Reducer<S, A> {
    return combineReducers(reducers.mapKeys { it.key.name })
}
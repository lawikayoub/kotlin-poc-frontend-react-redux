package nl.lawik.poc.frontend.reactredux.reducers

import nl.lawik.poc.frontend.reactredux.actions.SetVisibilityFilter
import nl.lawik.poc.frontend.reactredux.enums.VisibilityFilter
import redux.RAction

fun visibilityFilterReducer(
    state: VisibilityFilter = VisibilityFilter.SHOW_ALL,
    action: RAction
): VisibilityFilter = when (action) {
    is SetVisibilityFilter -> action.filter
    else -> state
}
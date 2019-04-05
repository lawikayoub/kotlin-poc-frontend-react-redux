# Kotlin React Redux PoC
This project is a PoC of using Kotlin (JS) and React, React-Dom, React-Router, Redux and React-Redux. 
This project is an implementation/translation of the react-redux [Todo List example project](https://redux.js.org/basics/example) in Kotlin (with the addition of react-router).
The project showcases the following features:
* Using existing JavaScript libraries (react, react-dom, react-router, redux and react-redux) from npm with Kotlin.
* The implementation/translation of the react-redux [Todo List example project](https://redux.js.org/basics/example) in Kotlin (with the addition of react-router).
* Using Webpack to bundle (and minify)/run the project.
* Removing unused code to reduce file size.

NOTE: It is assumed that you have basic knowledge of Kotlin, npm, JavaScript, modules, Webpack, React(-Dom/Router), Redux, React-Redux.
It is highly recommended that you read and understand the react-redux [Todo List example project](https://redux.js.org/basics/example) and its [tutorial](https://redux.js.org/basics/usage-with-react) first.

# Installation
1. Clone this repository.

## Running the project
Run the following command in the root directory of the project `gradlew -t run`.
This will download gradle, download the required dependencies and run a webpack server for the project on port 8088 in continuous build mode (the browser will refresh automatically on code changes).

## Bundling for production
Run the following command in the root directory of the project: `gradlew clean bundle -Pprod`. 
This will download gradle, download the required dependencies, compile(transpile) Kotlin to JavaScript, bundle the JavaScript code into a single file, minify it and remove unused code to reduce file size.
The bundle and the HTML file in the `resources` folder will placed in a `web` folder in the root of the project.

## About

### Using existing JavaScript libraries from npm 
The kotlin-frontend-plugin allows you to add npm libraries from Gradle, after applying the plugin you can add npm dependencies in the following manner:
```groovy
kotlinFrontend {
    npm {
        dependency "react"
        // other (dev)dependencies here 
    }
    // ...
}
```
Similarly you can add dev dependencies in the same npm block using a devDependency tag instead of dependency. To use JavaScript libraries in a type-safe manner, external declarations for the libraries are required.
Thankfully there is a [repository](https://github.com/JetBrains/kotlin-wrappers) for kotlin wrappers for popular JavaScript, these are added via gradle in this project.

### Todo list example project
NOTE: import statements are left out in the example code below.

#### React
`RBuilder` allows you to create react components using type-safe builders.

A simple component in javascript is written as follows:

```jsx harmony
const HelloWorld = () => (
  <h1>Hello World!</h1>
)

export default HelloWorld
```
In Kotlin it is written as follows:
```kotlin
fun RBuilder.helloWorld() =
    h1 {
        + "Hello World!"
    }
```

This allows you to use the footer component in another component as follows:
```kotlin
fun RBuilder.app() = {
    helloWorld()
}
```

A more complex example (with props) will be shown in the [react-redux section](#react-redux)
#### Redux

##### Actions
Actions are defined using classes which implement the `RAction` interface.
In JavaScript the action which toggles the status of a todo is defined as follows:
```ecmascript 6
export const toggleTodo = id => ({
  type: 'TOGGLE_TODO',
  id
})
```
In Kotlin the action can written as follows:
```kotlin
class ToggleTodo(val id: Int): RAction
```
As you can see the Kotlin version doesn't have an action 'type', this is because the class (object type) itself *is* the action type (this will become more clear when you read the reducers section below).

(Under the hood, the Redux Kotlin wrapper turns the class into a JavaScript object which Redux is able to process.)

##### Reducers
Just like in JavaScript reducers in Kotlin are functions which take two parameters, a state and an action. 
In JavaScript the todos reducer is defined as follows:
```ecmascript 6
const todos = (state = [], action) => {
  switch (action.type) {
    case 'ADD_TODO':
      return [
        ...state,
        {
          id: action.id,
          text: action.text,
          completed: false
        }
      ]
    case 'TOGGLE_TODO':
      return state.map(todo =>
        (todo.id === action.id)
          ? {...todo, completed: !todo.completed}
          : todo
      )
    default:
      return state
  }
}

export default todos
```
In Kotlin the reducer is written as follows:
```kotlin
fun todos(state: Array<Todo> = emptyArray(), action: RAction): Array<Todo> = when (action) {
    is AddTodo -> state + Todo(action.id, action.text, false)
    is ToggleTodo -> state.map {
        if (it.id == action.id) {
            it.copy(completed = !it.completed)
        } else {
            it
        }
    }.toTypedArray()
    else -> state
}
```
As you can see the action type is checked by checking the object type of `action` using `is`.

Combining reducers in Kotlin (usually) goes as follows:
```kotlin
data class State(
    val todos: Array<Todo> = emptyArray(),
    val visibilityFilter: VisibilityFilter = VisibilityFilter.SHOW_ALL
)

fun combinedReducers() = combineReducers<State, RAction>(
    mapOf(
        "todos" to ::todos,
        "visibilityFilter" to ::visibilityFilter
    )
)
```
As you can see you provide a map where the key is the name of your state property and the value is the reducer function for said state property.
However, I have written the following function:
```kotlin
fun <S, A, R> combineReducers(reducers: Map<KProperty1<S, R>, Reducer<*, A>>): Reducer<S, A> {
    return combineReducers(reducers.mapKeys { it.key.name })
}
```
The key in this helper function is a property of your state class, this has 2 advantages:
1. It's less error-prone, when you change the name of your property without changing the corresponding key your code won't compile.
2. The compiler is now able to infer the `S` type parameter which means it is no longer needed to provide the 2 type parameters explicitly.

The `combinedReducers` function can now be written as follows (the `State` data class remained the same):
```kotlin
fun combinedReducers() = combineReducers(
    mapOf(
        State::todos to ::todos,
        State::visibilityFilter to ::visibilityFilter
    )
)
``` 

#### React-Redux
Connecting Redux state to React components' props is *somewhat* similar as in JavaScript.

In JavaScript the `FilterLink` container component is connected to the `Link` component in the following manner:

```jsx harmony
// Link.js

const Link = ({ active, children, onClick }) => (
    <button
       onClick={onClick}
       disabled={active}
       style={{
           marginLeft: '4px',
       }}
    >
      {children}
    </button>
)

Link.propTypes = {
  active: PropTypes.bool.isRequired,
  children: PropTypes.node.isRequired,
  onClick: PropTypes.func.isRequired
}

export default Link
```

```ecmascript 6
// FilterLink.js

const mapStateToProps = (state, ownProps) => ({
  active: ownProps.filter === state.visibilityFilter
})

const mapDispatchToProps = (dispatch, ownProps) => ({
  onClick: () => dispatch(setVisibilityFilter(ownProps.filter))
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Link)
```

In Kotlin this is done in the following manner:
```kotlin
//Link.kt

interface LinkProps : RProps {
    var active: Boolean
    var onClick: () -> Unit
}

class Link(props: LinkProps) : RComponent<LinkProps, RState>(props) {
    override fun RBuilder.render() {
        styledButton {
            attrs.onClickFunction = { props.onClick() }
            attrs.disabled = props.active
            css {
                marginLeft = 4.px
            }
            children()
        }
    }
}
```
```kotlin
// FilterLink.kt

interface FilterLinkProps : RProps {
    var filter: VisibilityFilter
}

private interface LinkStateProps : RProps {
    var active: Boolean
}

private interface LinkDispatchProps : RProps {
    var onClick: () -> Unit
}

val filterLink: RClass<FilterLinkProps> =
    rConnect<State, SetVisibilityFilter, WrapperAction, FilterLinkProps, LinkStateProps, LinkDispatchProps, LinkProps>(
        { state, ownProps ->
            active = state.visibilityFilter == props.filter
        },
        { dispatch, ownProps ->
            onClick = { dispatch(SetVisibilityFilter(props.filter)) }
        }

    )(Link::class.js.unsafeCast<RClass<LinkProps>>())
```
To map the state and dispatch to props in a type-safe manner, two interfaces are defined `LinkStateProps` and `LinkDispatchProps`.

As you can see, the rConnect function takes 7 type parameters:
* `State` this is your root state class
* `SetVisibilityFilter` this is the action that this component is able to dispatch, use inheritance to allow for more actions (or just put `RAction` here).
* `WrapperAction` Interface used to wrap your action class into an action object that Redux understands (just use the provided WrapperAction interface).
* `FilterLinkProps` Props of the *container* component, use `RProps` if the container has no props.
* `LinkStateProps` These are the props from the connected component (in this case `Link`) that should be mapped to state. *
* `LinkDispatchProps` These are the props from the connected component (in this case `Link`) that should be mapped to dispatch. *
* `LinkProps` The props from the connected component (in this case `Link`).

*These two combined usually form the props of the connected component. As you can see `LinkProps` has both the `active` and `onClick` props which are also in `LinkStateProps` and `LinkDispatchProps`

The rConnect function has 2 lambda parameters, the first one is the equivalent of mapStateToProps and the second one is the equivalent of mapDispatchToProps.

mapStateToProps:
```kotlin
 { state, ownProps ->
    active = state.visibilityFilter == ownProps.filter
 }
```
* `this` refers to `LinkStateProps`.
* `state` refers to the state, this was provided by `State`.
* `ownProps` refers to the props of the *container* component, in this case `FilterLinkProps`

mapDispatchToProps:
```kotlin
{ dispatch, ownProps ->
    onClick = { dispatch(SetVisibilityFilter(props.filter)) }
}
```
* `this` refers to `LinkDispatchProps`
* `dispatch` dispatcher which can be used to dispatch actions of the provided type, in this case `SetVisibilityFilter`.
* `ownProps` refers to the props of the *container* component, in this case `FilterLinkProps`

#### React-Router
The react-router Kotlin wrapper has support for `BrowserRouter` and `HashRouter`. 
The wrapper adds on to the type-safe builder used to create components which allow you to make use of the router in a similar manner as you would in JavaScript.

See `App.kt` to see how it is implemented in this project, it's also possible to map a component to a route using the following syntax:
```kotlin
fun RBuilder.render() = {
    hashRouter{ // or browserRouter
        switch {
            // ...
            route("/", Foo::class)
        }
    }
}
```

@file:JsModule("@jetbrains/ring-ui/components/button-group/button-group")
@file:JsNonModule

package nl.lawik.poc.frontend.reactredux.ringui

import react.ComponentClass
import react.dom.WithClassName

external interface ButtonGroupProps : WithClassName

@JsName("default")
external val ButtonGroup: ComponentClass<ButtonGroupProps>

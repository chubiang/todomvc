package com.minek.kotlin.everywhere.todomvc

import com.minek.kotlin.everywhere.keuix.browser.html.Html
import com.minek.kotlin.everywhere.keuix.browser.html.attribute
import com.minek.kotlin.everywhere.keuix.browser.html.class_
import com.minek.kotlin.everywhere.keuix.browser.html.href
import com.minek.kotlin.everywhere.keuix.browser.runBeginnerProgram
import org.w3c.dom.Element

@Suppress("unused")
@JsName("main")
fun main(container: Element) {
    runBeginnerProgram(container, Model(), update, view )
}

data class Model(val html: String = "")
sealed class Msg
data class NewHtml(val html: String) : Msg()

val update: (msg:Msg, model:Model) -> Model = {
    msg, model -> when(msg) {
        is NewHtml -> model.copy(html = msg.html)
    }
}

val view :(model:Model) -> Html<Msg> = { (html) ->
    Html.section(class_("todoapp")) {
        header(class_("header")) {
            h1(text = "todos")
            input(class_("new-todo"),
                    attribute("placeholder","What needs to be done?"),
                    attribute("autofocus",""))
        }
        section(class_("main")) {
            input(class_("toggle-all"),
                    attribute("id","toggle-all"),
                    attribute("type","checkbox"))
            label(attribute("for","toggle-all")) { +"Markl all as complete" }
            ul(class_("todo-list")) {
                li(class_("completed")) {
                    div(class_("view")) {
                        input(class_("toggle"),
                                attribute("type","checkbox"), attribute("checked",""))
                        label(text = "Taste JavaScript")
                        button(class_("destroy"))
                    }
                    input(class_("edit"),
                            attribute("value","Create a TodoMVC template"))
                }
                li {
                    div(class_("view")) {
                        input(class_("toggle"), attribute("type","checkbox"))
                        label(text = "Buy a unicorn")
                        button(class_("destroy"))
                    }
                    input(class_("edit"),
                            attribute("value","Rule the web"))
                }
            }
        }
        footer(class_("footer")) {
            span(class_("todo-count")) {
                strong(text = "0")
                + "item left"
            }
            ul(class_("filters")) {
                li {
                    a(class_("selected"), href("#/")) { +"All" }
                }
                li {
                    a(href("#/active")) { +"Active" }
                }
                li {
                    a(href("#/completed")) { +"Completed" }
                }
            }
            button(class_("clear-completed")) { +"Clear completed" }
        }
    }
}


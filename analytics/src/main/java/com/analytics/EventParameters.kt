package com.analytics

typealias ParameterName = String

class EventParameters(vararg parameters: Pair<ParameterName, Any>) : Map<String, Any> {
    private val parameters: Map<String, Any> =
        parameters.associate { it.first to it.second.processValue() }

    override val entries: Set<Map.Entry<String, Any>>
        get() = parameters.entries
    override val keys: Set<String>
        get() = parameters.keys
    override val size: Int
        get() = parameters.size
    override val values: Collection<Any>
        get() = parameters.values

    override fun isEmpty() = parameters.isEmpty()

    override fun get(key: String) = parameters[key]

    override fun containsValue(value: Any) = parameters.containsValue(value)

    override fun containsKey(key: String) = parameters.containsKey(key)
}

fun Any.processValue() = if (this is Boolean) {
    if (this) "true" else "false"
} else {
    this
}

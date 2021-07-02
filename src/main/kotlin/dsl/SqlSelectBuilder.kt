package dsl

class SqlSelectBuilder {
    private val columns = mutableListOf<String>()
    private lateinit var table: String

    fun select(vararg columns: String) {
        if (columns.isEmpty()) {
            throw IllegalArgumentException("At least one column should be defined")
        }
        if (this.columns.isNotEmpty()) {
            throw IllegalStateException("Detected an attemp to re-define columns to fetch. "
            + "Current columns list: "
            + "${this.columns}, new columns list: $columns")
        }
        print(columns)
        this.columns.addAll(columns)
    }

    fun from(table: String) {
        this.table = table
    }

    fun build(): String {
        if (!::table.isInitialized) {
            throw java.lang.IllegalStateException("Failed to build a sql select - target table is undefined")
        }
        return toString()
    }

    override fun toString(): String {
        val columnsToFetch =
            if (this.columns.isEmpty()) {
                "*"
            } else {
                this.columns.joinToString(", ")
            }
        return "select $columnsToFetch from $table"
    }
}

fun query(initializer: SqlSelectBuilder.() -> Unit): SqlSelectBuilder {
    return SqlSelectBuilder().apply(initializer)
}



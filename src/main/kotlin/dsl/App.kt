package dsl

fun main() {
    val sql = query {
        select("column1", "column2")
        from("myTable")
        where {
            "column1" eq 4
            or {
                "column2" eq null
                "column3" eq 42
            }
        }
    }.build()
    print(sql)
}

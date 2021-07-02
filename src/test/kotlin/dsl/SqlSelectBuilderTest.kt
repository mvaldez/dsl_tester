package dsl

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.Test


class SqlSelectBuilderTest {
    @Test
    fun `when no columns are specified then star is used`() {
        doTest("select * from table1") {
            from("table1")
        }
    }

    @Test
    fun `when no condition is specified then correct query is built`() {
        doTest("select column1, column2 from table1") {
            select("column1", "column2")
            from("table1")
        }
    }

    private fun doTest(expected: String, sql: SqlSelectBuilder.() -> Unit) {
        assertThat(query(sql).build()).isEqualTo(expected)
    }
}



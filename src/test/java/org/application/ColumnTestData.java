package org.application;

import org.application.entity.Column;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ColumnTestData {

    public static void assertMatch(Column actual, Column... expected) {
        assertMatch(Arrays.asList(actual), Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Column> actual, Column... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Column> actual, Iterable<Column> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id", "tickets").isEqualTo(expected);
    }
}

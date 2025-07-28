package pnet.data.api.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * A simple table
 *
 * @author ham
 */
public class Table {

    private final List<List<Object>> rows = new ArrayList<>();

    public <T> Table addRow(T object, Function<T, ?>[] columnProviders) {
        return addRow(
            Arrays.stream(columnProviders)
                .map(columnProvider -> columnProvider.apply(object))
                .toList()
        );
    }

    public Table addRow(Object... row) {
        return addRow(Arrays.asList(row));
    }

    public Table addRow(List<Object> row) {
        rows.add(row);

        return this;
    }

    private String toString(Object value, int width) {
        StringBuilder builder = new StringBuilder();

        if (value != null) {
            builder.append(value);
        }

        while (builder.length() < width) {
            builder.append(" ");
        }

        String result = builder.toString();

        result = result.replace("\n", " \\ ");

        return result;
    }

    @Override
    public String toString() {
        int size = rows.stream().mapToInt(List::size).max().orElse(0);
        int[] width = new int[size];

        rows.forEach(row -> {
            for (int i = 0; i < row.size(); i++) {
                width[i] = Math.max(width[i], toString(row.get(i), 0).length());
            }
        });

        StringBuilder builder = new StringBuilder();

        rows.forEach(row -> {
            if (!builder.isEmpty()) {
                builder.append("\n");
            }

            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    builder.append(" | ");
                }

                builder.append(toString(i < row.size() ? row.get(i) : null, width[i]));
            }
        });

        return builder.toString();
    }
}

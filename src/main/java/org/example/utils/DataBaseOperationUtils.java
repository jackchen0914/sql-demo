package org.example.utils;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataBaseOperationUtils {


    public static <T> void batchInsertFrom(List<T> dataList, Consumer<List<T>> insertConsumer, int batchSize) {
        if(CollectionUtils.isEmpty(dataList)){
            return;
        }

        for (int i = 0; i < dataList.size(); i+=batchSize) {
            int end = Math.min(i + batchSize, dataList.size());
            List<T> batchList = new ArrayList<>(dataList.subList(i, end));
            insertConsumer.accept(batchList);
        }
    }

    public static <T> void addIfNotNull(List<T> list, T item) {
        if (item != null) {
            list.add(item);
        }
    }


    public static <T> void batchInsertFromIterator(Iterator<T> it,
                                                    Consumer<List<T>> batchInserter,
                                                    int batchSize) {
        Objects.requireNonNull(batchInserter);
        if (it == null) return;

        List<T> batch = new ArrayList<>(batchSize);
        while (it.hasNext()) {
            batch.add(it.next());
            if (batch.size() >= batchSize) {
                // 复制一份提交，避免 mapper 改变原 batch
                batchInserter.accept(new ArrayList<>(batch));
                batch.clear();
            }
        }
        if (!batch.isEmpty()) {
            batchInserter.accept(new ArrayList<>(batch));
        }
    }


    public static <T, K> List<T> dedupeByKey(List<T> items, Function<T, K> keyExtractor) {
        if (items == null || items.isEmpty()) return items;
        return new ArrayList<>(items.stream()
                .collect(Collectors.toMap(
                        keyExtractor,
                        java.util.function.Function.identity(),
                        (first, second) -> first,
                        LinkedHashMap::new))
                .values());
    }

}

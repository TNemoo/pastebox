package com.svl.pastebox.repositories;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class RepositoryAMap implements IntRepositoryA{

    private Random random = new Random();
    private final Map<String, EntityA> map = new ConcurrentHashMap<>();

    @Override
    public EntityA getByHash(String hash) {
        EntityA o = map.get(hash);
//        if (o == null)
//            throw new NotFoundEntityException("The hash equals null");
        return o;
    }

    @Override
    public List<EntityA> getListOfPublicAndLive(int count) {
        LocalDateTime now = LocalDateTime.now();

        return map.values().stream()
                .filter(EntityA::isPublic)
                .filter(EntityA->EntityA.getLifetime().isAfter(now)) //true, если дата элемента > now
                .sorted(Comparator.comparing(EntityA::getId).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public String add(EntityA o) {
        Integer id = getLastId() + 1;
        o.setId(id);

        String newHash;
        do {
            newHash = getNewHash();
        } while (map.containsKey(newHash));

        o.setHash(newHash);

        map.put(o.getHash(), o);
        return o.getHash();
    }

    public Integer getLastId() {
        if (map.isEmpty())
            return 1;
        return map.values().stream().sorted(Comparator.comparing(EntityA::getId).reversed()).findFirst().get().getId();
    }

    private String getNewHash() {
        return Integer.toHexString(multiRandom(7483647,2147483647));
    }

    private int multiRandom(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}

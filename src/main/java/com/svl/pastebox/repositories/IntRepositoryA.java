package com.svl.pastebox.repositories;

import java.util.List;

public interface IntRepositoryA {
    EntityA getByHash(String hash);
    List<EntityA> getListOfPublicAndLive(int count);
    String add(EntityA entityA);
}

package com.svl.pastebox.repositories;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntityA {
    private Integer id;
    private String data;
    private String hash;
    private LocalDateTime lifetime;
    private boolean isPublic;

    @Override
    public String toString() {
        return "EntityA{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", hash='" + hash + '\'' +
                ", lifetime=" + lifetime +
                ", isPublic=" + isPublic +
                '}';
    }
}

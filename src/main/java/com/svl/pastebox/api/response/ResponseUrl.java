package com.svl.pastebox.api.response;

import lombok.Data;

@Data
public class ResponseUrl {
    private final String url;

    public ResponseUrl(String url) {
        this.url = url;
    }
}

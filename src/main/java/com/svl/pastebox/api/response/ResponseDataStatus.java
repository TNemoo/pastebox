package com.svl.pastebox.api.response;

import com.svl.pastebox.api.request.PublicStatus;
import lombok.Data;

@Data
public class ResponseDataStatus {
    private final String data;
    private final boolean isPublic;

    public ResponseDataStatus(String data, boolean isPublic) {
        this.data = data;
        this.isPublic = isPublic;
    }
}

package com.svl.pastebox.api.request;

import lombok.Data;

@Data
public class RequestDataTimeStatus {
    private String data;
    private long timeSecond;
    private PublicStatus publicStatus;
}

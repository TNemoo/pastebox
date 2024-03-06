package com.svl.pastebox.services;

import com.svl.pastebox.api.request.RequestDataTimeStatus;
import com.svl.pastebox.api.response.ResponseDataStatus;
import com.svl.pastebox.api.response.ResponseUrl;

import java.util.List;

public interface ServiceA {
    ResponseDataStatus getByHash(String hash);
    List<ResponseDataStatus> getAllPublic();
    ResponseUrl create(RequestDataTimeStatus o);
}

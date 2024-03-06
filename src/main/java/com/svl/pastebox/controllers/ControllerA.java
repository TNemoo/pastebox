package com.svl.pastebox.controllers;

import com.svl.pastebox.api.request.RequestDataTimeStatus;
import com.svl.pastebox.api.response.ResponseDataStatus;
import com.svl.pastebox.api.response.ResponseUrl;
import com.svl.pastebox.services.ServiceA;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerA {

    private final ServiceA serviceA;

    public ControllerA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    @GetMapping("/")
    public List<ResponseDataStatus> getAllPublic() {
        return serviceA.getAllPublic();
    }

    @GetMapping("/{hash}")
    public ResponseDataStatus getByHash(@PathVariable String hash) {
        return serviceA.getByHash(hash);
    }

    @PostMapping("/")
    public ResponseUrl add(@RequestBody RequestDataTimeStatus requestDataTimeStatus) {
        return serviceA.create(requestDataTimeStatus);
    }
}

package com.svl.pastebox.services;

import com.svl.pastebox.api.request.PublicStatus;
import com.svl.pastebox.api.request.RequestDataTimeStatus;
import com.svl.pastebox.api.response.ResponseDataStatus;
import com.svl.pastebox.api.response.ResponseUrl;
import com.svl.pastebox.repositories.EntityA;
import com.svl.pastebox.repositories.RepositoryAMap;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Setter
@ConfigurationProperties(prefix = "app") //берет префикс адреса из application.yml в массиве app, требует dependence
public class ServiceImp implements ServiceA{

    private String host; //@ConfigurationProperties берет значение из application.yml
    private int publicListSize; //тоже, с подкапотным преобразованием public_list_size в publicListSize

    private final RepositoryAMap repository;

    public ServiceImp(RepositoryAMap repository) {
        this.repository = repository;
    }

    @Override
    public ResponseDataStatus getByHash(String hash) {
        EntityA o = repository.getByHash(hash);
        if (o != null)
            return new ResponseDataStatus(o.getData(),o.isPublic());
        return new ResponseDataStatus("no response", true);
    }

    @Override
    public List<ResponseDataStatus> getAllPublic() {
        List<EntityA> listA = repository.getListOfPublicAndLive(publicListSize);
        return entityToResponseDS(listA);
    }

    @Override
    public ResponseUrl create(RequestDataTimeStatus request) {
        EntityA o = new EntityA();
        o.setData(request.getData());
        o.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
//        o.setLifetime(LocalDateTime.now().getSecond() + request.getTimeSecond());
        o.setLifetime(LocalDateTime.now().plusSeconds(request.getTimeSecond()));
        String hash = repository.add(o);

        return new ResponseUrl(host + "/" + hash);
    }

    private List<ResponseDataStatus> entityToResponseDS(List<EntityA> listA) {
        List<ResponseDataStatus> list = new ArrayList<>();
        for (EntityA x : listA)
            list.add(new ResponseDataStatus(x.getData(), x.isPublic()));
        return list;
    }
}

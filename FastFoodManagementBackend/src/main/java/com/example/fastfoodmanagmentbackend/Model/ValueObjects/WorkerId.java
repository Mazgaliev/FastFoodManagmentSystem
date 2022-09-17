package com.example.fastfoodmanagmentbackend.Model.ValueObjects;

import com.example.fastfoodmanagmentbackend.Model.base.DomainObjectId;

public class WorkerId extends DomainObjectId {
    public WorkerId(String uuid) {
        super(uuid);
    }

    public static WorkerId valueOf(String uuid) {
        return new WorkerId(uuid);
    }

    private WorkerId() {
        super(WorkerId.randomId(WorkerId.class).getId());
    }
}

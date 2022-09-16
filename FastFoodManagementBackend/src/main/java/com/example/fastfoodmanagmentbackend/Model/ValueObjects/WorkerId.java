package com.example.fastfoodmanagmentbackend.Model.ValueObjects;

import com.example.fastfoodmanagmentbackend.Model.base.DomainObjectId;

public class WorkerId extends DomainObjectId {
    protected WorkerId(String uuid) {
        super(uuid);
    }

    private WorkerId() {
        super(WorkerId.randomId(WorkerId.class).getId());
    }
}

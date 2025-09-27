package com.chris.cmarket.Common.Listener;

import com.chris.cmarket.Common.Contract.HasUuidModel;
import jakarta.persistence.PrePersist;

import java.util.UUID;

public class ModelUuidListener {
    @PrePersist
    public void generateUuid(Object o) {
        if (o instanceof HasUuidModel model) {
            if (null != model.getUuid() && !model.getUuid().isEmpty()) return;

            model.setUuid(UUID.randomUUID().toString());
        }
    }
}

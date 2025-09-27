package com.chris.cmarket.Common.Contract;

/**
 * Used on entity that has {@code uuid} field
 * Model must implement getter and setter of uuid
 */
public interface HasUuidModel {

    String getUuid();

    void setUuid(String uuid);
}
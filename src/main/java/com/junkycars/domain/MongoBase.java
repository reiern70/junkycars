package com.junkycars.domain;

import org.springframework.data.annotation.Id;

public class MongoBase {

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Id
    private String uuid;

	public MongoBase() {
	}

    public MongoBase(String uuid) {
        this.uuid = uuid;
    }
}

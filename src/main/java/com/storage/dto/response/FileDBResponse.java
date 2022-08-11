package com.storage.dto.response;

import lombok.Data;

@Data
public class FileDBResponse {
    private String id;
    private String name;
    private String url;
    private String type;
    private long size;

    public FileDBResponse(String id, String name, String url, String type, long size) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }
}

package com.example.demo.core.model.request;

import java.util.UUID;

public class BaseRequest {
    private final String requestId = UUID.randomUUID().toString();
}

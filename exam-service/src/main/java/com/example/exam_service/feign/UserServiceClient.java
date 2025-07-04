package com.example.exam_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/user/get/{id}")
    Object getUserById(@PathVariable("id") Integer id); // Replace Object with UserWrapper if available
} 
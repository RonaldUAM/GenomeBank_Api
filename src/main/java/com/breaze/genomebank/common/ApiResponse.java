package com.breaze.genomebank.common;

public record ApiResponse<T>(String status, String message, T data) {
}

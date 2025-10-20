package com.genomebank.common;

public record ApiResponse<T>(String status, String message, T data) {
}

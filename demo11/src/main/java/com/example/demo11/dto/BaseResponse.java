package com.example.demo11.dto;

public class BaseResponse<Status, Data> {
    private Status status;
    private Data data;

    public BaseResponse() {
    }

    public BaseResponse(Status status, Data data) {
        this.status = status;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}

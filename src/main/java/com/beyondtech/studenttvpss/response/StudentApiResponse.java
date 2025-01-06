package com.beyondtech.studenttvpss.response;

import com.beyondtech.studenttvpss.model.Student;

public class StudentApiResponse {
    private boolean success;
    private String message;
    private Student data;

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StudentApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

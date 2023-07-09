package com.example.pdfsplitter;

public class FeedbackViewModel {
    public String name;
    public String email;
    public String feedback;

    public String getFeedback() {
        return feedback;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}

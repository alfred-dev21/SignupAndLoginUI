package com.example.signupandloginui.gallery;

public class Celebrity {
    private String name;
    private String jobTitle;
    private String imageUrl;

    public Celebrity(String name, String jobTitle, String imageUrl) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package com.numeracy.termuxide.models;

public class CodeFile {
    private int id;
    private int projectId;
    private String filename;
    private String path;
    private String content;
    private long lastModified;
    private boolean isModified;

    public CodeFile(String filename, String path) {
        this.filename = filename;
        this.path = path;
        this.content = "";
        this.lastModified = System.currentTimeMillis();
        this.isModified = false;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public String getContent() { return content; }
    public void setContent(String content) { 
        this.content = content;
        this.isModified = true;
        this.lastModified = System.currentTimeMillis();
    }

    public long getLastModified() { return lastModified; }
    public void setLastModified(long lastModified) { this.lastModified = lastModified; }

    public boolean isModified() { return isModified; }
    public void setModified(boolean modified) { isModified = modified; }
}

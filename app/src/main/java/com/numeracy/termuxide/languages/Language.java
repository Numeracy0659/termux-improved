package com.numeracy.termuxide.languages;

import com.numeracy.termuxide.models.ExecutionResult;

public abstract class Language {
    protected String name;
    protected String version;
    protected String[] fileExtensions;
    protected boolean isCompiled;

    public abstract String getName();
    public abstract String getVersion();
    public abstract String[] getFileExtensions();
    public abstract boolean isCompiled();
    public abstract ExecutionResult execute(String sourceFile);
    public abstract ExecutionResult compile(String sourceFile);
    public abstract boolean isInstalled();
    public abstract String getSyntaxHighlightingRules();
    
    public String getLanguageId() {
        return getName().toLowerCase().replaceAll("\\s+", "_");
    }
}

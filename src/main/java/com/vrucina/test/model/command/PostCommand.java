package com.vrucina.test.model.command;

import javax.validation.constraints.NotNull;

public class PostCommand {
    @NotNull
    private String comment;

    public String getComment() {
        return comment;
    }
}

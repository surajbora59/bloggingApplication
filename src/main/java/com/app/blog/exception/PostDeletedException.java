package com.app.blog.exception;

public class PostDeletedException extends RuntimeException{

        public PostDeletedException() {
            super("post already deleted");
        }
}

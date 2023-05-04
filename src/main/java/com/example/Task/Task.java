package com.example.Task;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
  @JsonProperty
  private Long id;
  @JsonProperty
  private String title;
  @JsonProperty
  private String description;
  @JsonProperty
  private Boolean completed;

  public Task(Long id, String title, String description, Boolean completed) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.completed = completed;
  }
  void setId(Long id) {
    this.id = id;
  }
  Long getId() {
    return this.id;
  }

  void setTitle(String title) {
    this.title = title;
  }

  String getTitle() {
    return this.title;
  }

  void setDescription(String description) {
    this.description = description;
  }

  String getDescription() {
    return this.description;
  }

  void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  Boolean getCompleted() {
    return this.completed;
  }

  public String toString() {
    return this.description;
  }
}
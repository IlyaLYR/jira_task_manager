package com.vsu.odinaev.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets TaskStatus
 */
public enum TaskStatus {
  
  TODO("TODO"),
  
  IN_PROGRESS("IN_PROGRESS"),
  
  REVIEW("REVIEW"),
  
  TO_TEST("TO_TEST"),
  
  IN_TEST("IN_TEST"),
  
  DONE("DONE");

  private String value;

  TaskStatus(String value) {
    this.value = value;
  }

    /**
     * Convert a String into String, as specified in the
     * <a href="https://download.oracle.com/otndocs/jcp/jaxrs-2_0-fr-eval-spec/index.html">See JAX RS 2.0 Specification, section 3.2, p. 12</a>
     */
    public static TaskStatus fromString(String s) {
      for (TaskStatus b : TaskStatus.values()) {
        // using Objects.toString() to be safe if value type non-object type
        // because types like 'int' etc. will be auto-boxed
        if (java.util.Objects.toString(b.value).equals(s)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected string value '" + s + "'");
    }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TaskStatus fromValue(String value) {
    for (TaskStatus b : TaskStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}



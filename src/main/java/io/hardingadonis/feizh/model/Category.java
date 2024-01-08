package io.hardingadonis.feizh.model;

import io.hardingadonis.feizh.model.detail.*;
import java.time.*;

public class Category {

  private int ID;

  private String name;
  private String description;
  private CategoryType type;

  private LocalDateTime createAt;
  private LocalDateTime updateAt;
  private LocalDateTime deleteAt;

  public Category() {}

  public Category(String name, String description, CategoryType type) {
    this.name = name;
    this.description = description;
    this.type = type;
  }

  public Category(int ID, String name, String description, CategoryType type) {
    this.ID = ID;
    this.name = name;
    this.description = description;
    this.type = type;
  }

  public Category(
      int ID,
      String name,
      String description,
      CategoryType type,
      LocalDateTime createAt,
      LocalDateTime updateAt,
      LocalDateTime deleteAt) {
    this.ID = ID;
    this.name = name;
    this.description = description;
    this.type = type;
    this.createAt = createAt;
    this.updateAt = updateAt;
    this.deleteAt = deleteAt;
  }

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CategoryType getType() {
    return type;
  }

  public void setType(CategoryType type) {
    this.type = type;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  public LocalDateTime getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(LocalDateTime updateAt) {
    this.updateAt = updateAt;
  }

  public LocalDateTime getDeleteAt() {
    return deleteAt;
  }

  public void setDeleteAt(LocalDateTime deleteAt) {
    this.deleteAt = deleteAt;
  }

  @Override
  public String toString() {
    return "Category{"
        + "ID="
        + ID
        + ", name="
        + name
        + ", description="
        + description
        + ", type="
        + type
        + ", createAt="
        + createAt
        + ", updateAt="
        + updateAt
        + ", deleteAt="
        + deleteAt
        + '}';
  }
}

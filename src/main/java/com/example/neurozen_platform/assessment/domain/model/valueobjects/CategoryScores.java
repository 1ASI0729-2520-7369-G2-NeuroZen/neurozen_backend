package com.example.neurozen_platform.assessment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class CategoryScores {
  
  private Integer workScore;
  private Integer sleepScore;
  private Integer physicalScore;
  private Integer emotionalScore;

  public CategoryScores() {
    this.workScore = 0;
    this.sleepScore = 0;
    this.physicalScore = 0;
    this.emotionalScore = 0;
  }

  public CategoryScores(Integer workScore, Integer sleepScore, Integer physicalScore, Integer emotionalScore) {
    this.workScore = workScore;
    this.sleepScore = sleepScore;
    this.physicalScore = physicalScore;
    this.emotionalScore = emotionalScore;
  }
}


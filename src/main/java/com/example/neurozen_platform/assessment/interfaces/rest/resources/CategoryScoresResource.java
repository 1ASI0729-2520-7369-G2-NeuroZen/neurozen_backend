package com.example.neurozen_platform.assessment.interfaces.rest.resources;

public record CategoryScoresResource(
  Integer work,
  Integer sleep,
  Integer physical,
  Integer emotional
) {
}


package com.jisutudy.domain.performance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("M")
@Getter
public class Musical extends Item {
}

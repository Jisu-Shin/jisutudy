package com.jisutudy.domain.performance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("S")
@Getter
public class Sport extends Item {
}

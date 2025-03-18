package com.jisutudy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TemplatePlaceholder {

    @EmbeddedId
    private TemplatePlaceholderPK templatePlaceholderPK;
}

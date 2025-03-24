package com.jisutudy.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SmsTmpltVarRelId implements Serializable {

    private Long smsTmpltId;
    private Long tmpltVarId;

}

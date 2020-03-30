package com.caching.inlinecachinggeode1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@IdClass(ResultHolder.ResultKey.class)
@EqualsAndHashCode(of = {"operand", "operator"})
@RequiredArgsConstructor(staticName = "of")
@Table(name = "Calculations")
public class ResultHolder implements Serializable {
    @Id @NonNull
    private Integer operand;

    @Id @NonNull
    @Enumerated(EnumType.STRING)
    private Operator operator;

    @NonNull
    private Integer result;

    protected ResultHolder() {
    }

    @Override
    public String toString() {
        return getOperator().toString(getOperand(), getResult());
    }

    @Getter
    @EqualsAndHashCode
    @RequiredArgsConstructor(staticName = "of")
    public static class ResultKey implements Serializable {

        @NonNull
        private Integer operand;

        @NonNull
        private Operator operator;

        protected ResultKey() {
        }

    }
}

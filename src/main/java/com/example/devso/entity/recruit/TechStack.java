package com.example.devso.entity.recruit;

import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TechStack {
    JAVASCRIPT(1, "JavaScript"),
    TYPESCRIPT(2, "TypeScript"),
    REACT(3, "React"),
    VUE(4, "Vue"),
    NODEJS(5, "Node.js"),
    SPRING(6, "Spring"),
    JAVA(7, "Java"),
    JPA(8, "JPA"),
    NEXTJS(9, "Next.js"),
    NESTJS(10, "Nest.js"),
    EXPRESS(11, "Express"),
    GO(12, "Go"),
    C(13, "C"),
    PYTHON(14, "Python"),
    DJANGO(15, "Django"),
    SWIFT(16, "Swift"),
    KOTLIN(17, "Kotlin"),
    MYSQL(18, "MySQL"),
    MONGODB(19, "MongoDB"),
    PHP(20, "PHP"),
    GRAPHQL(21, "GraphQL"),
    FIREBASE(22, "Firebase"),
    REACTNATIVE(23, "React Native"),
    UNITY(24, "Unity"),
    FLUTTER(25, "Flutter"),
    AWS(26, "AWS"),
    KUBERNETES(27, "Kubernetes"),
    DOCKER(28, "Docker"),
    GIT(29, "Git"),
    FIGMA(30, "Figma"),
    ZEPLIN(31, "Zeplin"),
    JEST(32, "Jest"),
    SVELTE(33, "Svelte");

    private final int value;
    private final String label;

    TechStack(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static TechStack fromValue(int value) {
        for (TechStack stack : values()) {
            if (stack.value == value) {
                return stack;
            }
        }
        throw new CustomException(ErrorCode.INVALID_ENUM_VALUE);
    }
}

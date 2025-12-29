package com.example.devso.entity.recruit;

import com.example.devso.exception.CustomException;
import com.example.devso.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TechStack {
    // 프론트엔드 (FE)
    JAVASCRIPT(1, "JavaScript", "FE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-original.svg"),
    TYPESCRIPT(2, "TypeScript", "FE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/typescript/typescript-original.svg"),
    REACT(3, "React", "FE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg"),
    VUE(4, "Vue", "FE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vuejs/vuejs-original.svg"),
    NEXTJS(9, "Next.js", "FE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nextjs/nextjs-original.svg"),
    SVELTE(33, "Svelte", "FE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/svelte/svelte-original.svg"),

    // 백엔드 (BE)
    JAVA(7, "Java", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"),
    SPRING(6, "Spring", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg"),
    NODEJS(5, "Node.js", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nodejs/nodejs-original.svg"),
    NESTJS(10, "Nest.js", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/nestjs/nestjs-original.svg"),
    EXPRESS(11, "Express", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/express/express-original.svg"),
    GO(12, "Go", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/go/go-original-wordmark.svg"),
    PYTHON(14, "Python", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/python/python-original.svg"),
    DJANGO(15, "Django", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/django/django-plain.svg"),
    PHP(20, "PHP", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/php/php-original.svg"),

    // 데이터베이스 & 인프라 (BE/ETC)
    MYSQL(18, "MySQL", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg"),
    MONGODB(19, "MongoDB", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original.svg"),
    GRAPHQL(21, "GraphQL", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/graphql/graphql-plain.svg"),
    AWS(26, "AWS", "ETC", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/amazonwebservices/amazonwebservices-original-wordmark.svg"),
    DOCKER(28, "Docker", "ETC", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg"),
    KUBERNETES(27, "Kubernetes", "ETC", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kubernetes/kubernetes-plain.svg"),

    // 모바일 (MOBILE)
    SWIFT(16, "Swift", "MOBILE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/swift/swift-original.svg"),
    KOTLIN(17, "Kotlin", "MOBILE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg"),
    FLUTTER(25, "Flutter", "MOBILE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/flutter/flutter-original.svg"),
    REACTNATIVE(23, "React Native", "MOBILE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg"),

    // 디자인 & 기타 (ETC)
    FIGMA(30, "Figma", "ETC", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/figma/figma-original.svg"),
    GIT(29, "Git", "ETC", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg"),
    FIREBASE(22, "Firebase", "ETC", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/firebase/firebase-plain.svg"),

    // 추가 (필요에 따라 분류)
    JPA(8, "JPA", "BE", null),
    C(13, "C", "BE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/c/c-original.svg"),
    UNITY(24, "Unity", "ETC", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/unity/unity-original.svg"),
    ZEPLIN(31, "Zeplin", "ETC", null),
    JEST(32, "Jest", "FE", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jest/jest-plain.svg");

    private final int value;
    private final String label;
    private final String category; // 🌟 FE, BE, MOBILE, ETC 등
    private final String imageUrl; // 🌟 CDN 아이콘 링크

    TechStack(int value, String label, String category, String imageUrl) {
        this.value = value;
        this.label = label;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    @JsonValue
    public int getValue() { return value; }

    @JsonCreator
    public static TechStack fromValue(Integer value) {
        if (value == null) return null;
        for (TechStack stack : values()) {
            if (stack.value == value) {
                return stack;
            }
        }
        throw new CustomException(ErrorCode.INVALID_ENUM_VALUE);
    }
}
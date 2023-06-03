package com.example.archunitdemo;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

public class MyArchitectureTest {
    @Test
    public void controller_authorize_annotation_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.example");

        ArchRule rule = methods().that(methodAnnotatedWithHTTPRequestMapping)
                .and().areDeclaredInClassesThat().areAnnotatedWith(Controller.class)
                .should().beAnnotatedWith(AuthAdmin.class).orShould().beAnnotatedWith(AuthUser.class);

        rule.check(importedClasses);
    }

    @Test
    public void rest_controller_authorize_annotation_interface_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.example");

        ArchRule rule = methods().that(methodAnnotatedWithHTTPRequestMapping)
                .and().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
                .should().beAnnotatedWith(AuthAdmin.class).orShould().beAnnotatedWith(AuthUser.class);

        rule.check(importedClasses);
    }

    DescribedPredicate<JavaMethod> methodAnnotatedWithHTTPRequestMapping =
            new DescribedPredicate<JavaMethod>("@GetMapping、@PostMappingまたは@RequestMappingが付与されたメソッド") {
                @Override
                public boolean test(JavaMethod input) {
                    var method = input.reflect();
                    return isAnnotatedWith(method, GetMapping.class) || isAnnotatedWith(method, PostMapping.class) || isAnnotatedWith(method, RequestMapping.class);
                }

                private <A extends Annotation> boolean isAnnotatedWith(Method method, Class<A> annotationType) {
                    return AnnotationUtils.findAnnotation(method, annotationType) != null;
                }
            };
}
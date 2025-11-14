package org.jnew.features.j24;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.lang.classfile.ClassElement;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.MethodModel;
import java.lang.constant.ClassDesc;
import java.nio.file.Path;

@Slf4j
class ClassFileApiTest {

    @ParameterizedTest
    @CsvSource({
            "build/classes/java/main/org/jnew/features/j25/SimplePojo.class",
            "../java23/build/classes/java/test/org/jnew/features/j23/StructuredConcurrencyJEP480Test.class",
            "../java9/build/classes/java/main/org/jnew/features/j9/process/ListProcesses.class",
    })
    void analyseClass_printMetadata(String classFilePath) throws IOException {

        ClassModel model = ClassFile.of().parse(Path.of(classFilePath));

        log.atInfo().addArgument(model).log("model = [{}]");

        // print the entire structure of elements from the class file
        model.elementStream()
                .forEach(classElement -> log.atInfo().addArgument(classElement).log("[{}]"));


    }

    @Test
    void modifyClass_removeMethod() throws IOException {

        final ClassModel model = ClassFile.of().parse(Path.of("../java23/build/classes/java/test/org/jnew/features/j23/StructuredConcurrencyJEP480Test.class"));

        ClassFile.of().buildTo(Path.of("./build/classes/BetterSCTest.class"), ClassDesc.of("BetterSCTest"), classBuilder -> {
            for (ClassElement ce : model) {

                if (ce instanceof MethodModel mm
                        && mm.methodName().stringValue().startsWith("makeMeAHttpGettingRunnable")) {
                    continue;
                }

                classBuilder.with(ce);
            }
        });


    }
}

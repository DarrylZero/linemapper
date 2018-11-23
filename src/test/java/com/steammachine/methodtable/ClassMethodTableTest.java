package com.steammachine.methodtable;

import com.steammachine.methodtable.ClassMethodTable.SourceCodePosition;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.junit.Test;

public class ClassMethodTableTest {

    @Test
    public void test() throws IOException {

//        ClassWithMethods.class.

//        ClassMethodTable.readClassFromInputStream();

        InputStream classCodeStream = getClassCodeStream(ClassWithMethods.class);
        if (classCodeStream != null) {
            try (InputStream stream = getClassCodeStream(ClassWithMethods.class)) {
                Map<String, SourceCodePosition> positionMap =
                        ClassMethodTable.readClassFromInputStream(stream);

                positionMap.toString();

            }
        }
    }


    private static InputStream getClassCodeStream(Class<?> clazz) throws FileNotFoundException {
        if (clazz.getProtectionDomain() == null) {
            return null;
        }
        if (clazz.getProtectionDomain().getCodeSource() == null) {
            return null;
        }
        if (clazz.getProtectionDomain().getCodeSource().getLocation() == null) {
            return null;
        }

        return new FileInputStream(
                new File(clazz.getProtectionDomain().getCodeSource().getLocation().getFile(),
                        clazz.getPackage().getName().replace(".", "/") + "/" + clazz.getSimpleName()
                                + ".class"));
    }




}
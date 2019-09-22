package itAcademy.ORM.connection.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class ResourceLoader {

    public static InputStream loadResource(String name) throws IOException {
        ClassLoader classLoader = ResourceLoader.class.getClassLoader();
        InputStream inputStream = null;
        if (ObjectUtils.isNotNull(classLoader)) {
            inputStream = classLoader.getResourceAsStream(name);
        }
        if (ObjectUtils.isNull(inputStream)) {
            classLoader = ClassLoader.getSystemClassLoader();
            inputStream = classLoader.getResourceAsStream(name);
        }
        if (ObjectUtils.isNull(inputStream)) {
            File file = new File(name);
            if (file.exists() && file.isFile()) {
                inputStream = new FileInputStream(file);
            }
        }
        return inputStream;
    }

    public static Properties loadProperties(String name) throws IOException {
        InputStream inputStream = null;
        Properties properties = null;
        try {
            inputStream = loadResource(name);
            if (ObjectUtils.isNotNull(inputStream)) {
                properties = new Properties();
                properties.load(inputStream);
            }
        } finally {
            if (ObjectUtils.isNotNull(inputStream)) {
                inputStream.close();
            }
        }
        return properties;
    }

}

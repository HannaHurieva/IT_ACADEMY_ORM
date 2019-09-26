package itAcademy.ORM.reflection;


import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Reflection {


    public static ArrayList<String> getClassNamesFromPackage(String packageName) throws IOException, URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageURL;
        ArrayList<String> names = new ArrayList<>();
        packageName = packageName.replace(".", "/");
        packageURL = classLoader.getResource(packageName);
        if (packageURL != null) {
            if (packageURL.getProtocol().equals("jar")) {
                String jarFileName = URLDecoder.decode(packageURL.getFile(), StandardCharsets.UTF_8);
                jarFileName = jarFileName.substring(5, jarFileName.indexOf("!"));
                JarFile jf = new JarFile(jarFileName);
                Enumeration<JarEntry> jarEntries = jf.entries();
                while (jarEntries.hasMoreElements()) {
                    String entryName = jarEntries.nextElement().getName();
                    if (entryName.startsWith(packageName) && entryName.length() > packageName
                            .length() + 5) {
                        entryName = entryName.substring(
                                packageName.length(), entryName.lastIndexOf('.')
                        );
                        names.add(entryName);
                    }
                }
            } else {
                URI uri = new URI(packageURL.toString());
                java.io.File folder = new java.io.File(uri.getPath());
                java.io.File[] files = folder.listFiles();
                String entryName;
                if (files != null) {
                    for (java.io.File actual : files) {
                        entryName = actual.getName();
                        if (entryName.lastIndexOf('.') != -1) {
                            entryName = entryName.substring(0, entryName.lastIndexOf('.'));
                            names.add(packageName.replace("/", ".") + "." + entryName);
                        } else {
                            ArrayList<String> ret = getClassNamesFromPackage(
                                    packageName.replace("/", ".") + "." + entryName
                            );
                            names.addAll(ret);
                        }

                    }
                }
            }
        }
        return names;
    }

    public static void setField(Object object, String fieldName, Object value) throws IllegalAccessException, ReflectionException {
        boolean noField = true;
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                field.setAccessible(true);
                field.set(object, value);
                noField = false;
            }
        }
        if (noField) {
            throw new ReflectionException("No field");
        }
    }

    public static Object getFieldValue(Object object, String fieldName) throws IllegalAccessException, ReflectionException {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                field.setAccessible(true);
                return field.get(object);
            }
        }
        throw new ReflectionException("No field");
    }
}

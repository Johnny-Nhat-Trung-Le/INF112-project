// Class from:      https://git.app.uib.no/inf112/24v/libgdx-example/-/blob/main/src/main/java/inf112/util/PluginLoader.java?ref_type=heads
// Author:          Anya Helene Bagge
// Downloaded:      16.04.2024
// changes from original:
//      added PluginLoader::makeFactory : Function4
//      modified PluginLoader::listFiles (added support for JAR-files)


package inf112.skeleton.app.utils;

import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Example of how to find and load classes at runtime.
 * <b>Doesn't work with modules or JAR files.</b>
 * <p>
 * Most of the methods accept an <code>origin</code> argument. When searching
 * for classes or files we start in the <em>same package</em> and classloader as
 * <code>origin</code>.
 * <p>
 * For example,
 * <ul>
 * <li><code>listClasses(com.example.Example.class)</code> will find all classes
 * in the <code>com.example</code> package.
 * <li><code>listClasses(com.example.Example.class, "..")</code> will find all
 * classes in the <code>com</code> package.
 * <li><code>listClasses(com.example.Example.class, "/", true)</code> will find
 * all classes recursively.
 * </ul>
 *
 * @author anya
 */
public class PluginLoader {
    static final int CONSTANT_MODS = Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL;
    static Pattern classPattern = Pattern.compile("/?([^$]*)\\.class");

    /**
     * List files in a classpath directory.
     *
     * @param origin  Files/directories are looked up relative to the location of
     *                this class
     * @param path    A path, relative to <code>origin</code>, or "/" or ""
     * @param recurse Whether to search recursively
     * @return A list of file paths.
     */
    public static List<String> listFiles(Class<?> origin, String path, boolean recurse) {
        Path prefix = Path.of("/", origin.getPackageName().replace('.', '/'));
        Path p = prefix.resolve(path).normalize();
        String resourcePath = p.toString();

        // To work with windows
        resourcePath = resourcePath.replace("\\", "/");
        if (resourcePath.startsWith("//")) resourcePath = resourcePath.substring(1);

        URL url = origin.getResource(resourcePath);
        if (url == null) {
//            Gdx.app.error("PluginLoader", "Resource inaccessible: " + p);
            return List.of();
        }
        if (url.getProtocol().equals("jar")) {
            String jarPathStr = url.getFile();
            String jarFileStr = jarPathStr.substring(5, jarPathStr.indexOf('!'));
            String subPath = jarPathStr.substring(jarPathStr.indexOf('!') + 1);

            URL jarURL = origin.getProtectionDomain().getCodeSource().getLocation();
            try {
                Path jarPath = Paths.get(jarURL.toURI());
                // Open the JAR file
                try (JarFile jarFile = new JarFile(jarPath.toFile())) {
                    List<String> result = new ArrayList<>();

                    // Iterate through the entries in the JAR file
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        // Check if the entry is a class file in the specified package
                        if (entry.getName().startsWith(subPath.substring(1)) && entry.getName().endsWith(".class")) {
                            // Convert the entry name to a class name
                            String className = "/" + entry.getName();
                            result.add(className);
                        }
                    }
                    return result;
                } catch (SecurityException | IOException e) {
//                    Gdx.app.debug("PluginLoader", "Failed to load classes", e);
                }
            } catch (URISyntaxException e) {
//                Gdx.app.debug("PluginLoader", "Failed to load classes", e);
            }
            return List.of();
        }

        try (var reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(origin.getResourceAsStream(resourcePath))))) {
            List<String> list = reader.lines().toList();// read everything into a list before we return
            List<String> result = new ArrayList<>();
            for (var s : list) {
                if (recurse && !s.contains(".")) {
                    result.addAll(listFiles(origin, path + "/" + s, recurse));
                } else {
                    result.add(p.resolve(s).toString());
                }
            }
//            Gdx.app.debug("PluginLoader", "searching " + p + " → " + result);

            return result;
        } catch (IOException e) {
//            Gdx.app.debug("PluginLoader", "Failed to list plugin classes", e);
            return List.of();
        }
    }

    /**
     * List classes in a package.
     *
     * @param origin Where to start looking
     * @return A stream of fully qualified class names (of classes in the same
     * package as <code>origin</code>)
     */
    public static Stream<String> listClasses(Class<?> origin) {
        return listClasses(origin, "", false);
    }

    /**
     * List classes non-recursively
     *
     * @param origin Where to start looking
     * @param path   A path, relative to <code>origin</code>'s package (or
     *               <code>""</code>)
     * @return A stream of fully qualified class names
     */
    public static Stream<String> listClasses(Class<?> origin, String path) {
        return listClasses(origin, path, false);
    }

    /**
     * List classes
     *
     * @param origin  Where to start looking
     * @param path    A path, relative to <code>origin</code>'s package (or
     *                <code>""</code>)
     * @param recurse Whether to search recursively
     * @return A stream of fully qualified class names
     */
    public static Stream<String> listClasses(Class<?> origin, String path, boolean recurse) {
        return listFiles(origin, path, recurse).stream() //
                .map(classPattern::matcher)//
                .filter(Matcher::matches)// is it *.class?
                .map(m -> m.group(1).replace('/', '.')); // drop .class
    }

    /**
     * Load class if it implements the given interface.
     *
     * @param <T>               The interface type
     * @param fullName          Package and class name
     * @param requiredInterface The interface
     * @return The loaded class, or <code>null</code> if it couldn't be found or
     * didn't implement <code>requiredInterface</code>
     */
    @SuppressWarnings("unchecked") // safe, since we check type dynamically
    public static <T> Class<T> loadClass(String fullName, Class<T> requiredInterface) {
        try {
            Class<?> c = Class.forName(fullName);
            int mods = c.getModifiers();
            if ((mods & (Modifier.ABSTRACT | Modifier.INTERFACE)) != 0)
                return null;
            // check that we actually have a Class<T>
            if (!requiredInterface.isAssignableFrom(c))
                return null;
            return (Class<T>) c;
        } catch (ClassNotFoundException e) {
//            Gdx.app.debug("PluginLoader", "Failed load class " + fullName, e);
            return null;
        } catch (NoClassDefFoundError e) {
            // Fix for windows
            try {
                Class<?> c = Class.forName(fullName.substring(2).replace("\\", "."));
                int mods = c.getModifiers();
                if ((mods & (Modifier.ABSTRACT | Modifier.INTERFACE)) != 0)
                    return null;
                // check that we actually have a Class<T>
                if (!requiredInterface.isAssignableFrom(c))
                    return null;
                return (Class<T>) c;
            } catch (ClassNotFoundException e2) {
//            Gdx.app.debug("PluginLoader", "Failed load class " + fullName, e);
                return null;
            }
        }
    }

    /**
     * Load all classes implementing the given interface.
     *
     * @param <T>               Interface type
     * @param origin            Where to start looking for classes
     * @param requiredInterface An interface the class should implement
     * @return A stream of classes that implement <code>requiredInterface</code>
     */
    public static <T> Stream<Class<T>> loadClasses(Class<?> origin, Class<T> requiredInterface) {
        return listClasses(origin, "", false)//
                .map(s -> loadClass(s, requiredInterface))//
                .filter(Objects::nonNull);
    }

    /**
     * Load all classes implementing the given interface.
     *
     * @param <T>               Interface type
     * @param origin            Where to start looking for classes
     * @param path              A path, relative to origin's package (or "")
     * @param requiredInterface An interface the class should implement
     * @return A stream of classes that implement <code>requiredInterface</code>
     */
    public static <T> Stream<Class<T>> loadClasses(Class<?> origin, String path, Class<T> requiredInterface) {
        return listClasses(origin, path, true)//
                .map(s -> loadClass(s, requiredInterface))//
                .filter(Objects::nonNull);
    }

    /**
     * Make a function that constructs an object of the given class.
     *
     * @param <T>              The class
     * @param <U>              Type of the first constructor argument
     * @param clazz            The class
     * @param constructorParam The class of the first constructor argument
     * @return A <code>(a) -> new clazz(a)</code>
     */
    public static <T, U> Function<U, T> makeFactory(Class<T> clazz, Class<U> constructorParam) {
        Constructor<T> constructor;
        try {
            constructor = clazz.getDeclaredConstructor(constructorParam);
            if (!Modifier.isPublic(constructor.getModifiers())) {
                throw new IllegalAccessException("Constructor not public");
            }
            constructor.setAccessible(true);
            return (param) -> {
                try {
                    return constructor.newInstance(param);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    Gdx.app.debug("PluginLoader", "Constructor failed for " + clazz.getName(), e);
                    return null;
                }
            };
        } catch (NoSuchMethodException | IllegalAccessException | SecurityException e) {
            Gdx.app.debug("PluginLoader", //
                    String.format("No public constructor not found: %s(%s)", clazz.getName(),
                            constructorParam.getName()),
                    e);
            return null;
        }
    }

    /**
     * Make a function that constructs an object of the given class.
     *
     * @param <T>               The class
     * @param <U>               Type of the first constructor argument
     * @param <V>               Type of the second constructor argument
     * @param clazz             The class
     * @param constructorParam1 The class of the first constructor argument
     * @param constructorParam2 The class of the first constructor argument
     * @return A <code>(a) -> new clazz(a)</code>
     */
    public static <T, U, V> BiFunction<U, V, T> makeFactory(Class<T> clazz, Class<U> constructorParam1,
                                                            Class<V> constructorParam2) {
        Constructor<T> constructor;
        try {
            constructor = clazz.getDeclaredConstructor(constructorParam1, constructorParam2);
            if (!Modifier.isPublic(constructor.getModifiers())) {
                throw new IllegalAccessException("Constructor not public");
            }
            constructor.setAccessible(true);
            return (arg1, arg2) -> {
                try {
                    return constructor.newInstance(arg1, arg2);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
//                    Gdx.app.debug("PluginLoader", "Constructor failed for " + clazz.getName(), e);
                    return null;
                }
            };
        } catch (NoSuchMethodException | IllegalAccessException | SecurityException e) {
//            Gdx.app.debug("PluginLoader", //
//                    String.format("No public constructor not found: %s(%s, %s)", clazz.getName(),
//                            constructorParam1.getName(), constructorParam2.getName()),
//                    e);
            return null;
        }
    }

    /**
     * Make a function that constructs an object of the given class.
     *
     * @param <T>               The class
     * @param <U>               Type of the first constructor argument
     * @param <V>               Type of the second constructor argument
     * @param <G>               Type of the third constructor argument
     * @param <H>               Type of the fourth constructor argument
     * @param clazz             The class
     * @param constructorParam1 The class of the first constructor argument
     * @param constructorParam2 The class of the second constructor argument
     * @param constructorParam3 The class of the third constructor argument
     * @param constructorParam4 The class of the fourth constructor argument
     * @return A <code>(a) -> new clazz(a)</code>
     */
    public static <T, U, V, G, H> Function4<U, V, G, H, T> makeFactory(Class<T> clazz, Class<U> constructorParam1,
                                                                       Class<V> constructorParam2, Class<G> constructorParam3, Class<H> constructorParam4) {
        Constructor<T> constructor;
        try {
            constructor = clazz.getDeclaredConstructor(constructorParam1, constructorParam2, constructorParam3, constructorParam4);
            if (!Modifier.isPublic(constructor.getModifiers())) {
                throw new IllegalAccessException("Constructor not public");
            }
            constructor.setAccessible(true);
            return (arg1, arg2, arg3, arg4) -> {
                try {
                    return constructor.newInstance(arg1, arg2, arg3, arg4);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
//                    Gdx.app.debug("PluginLoader", "Constructor failed for " + clazz.getName(), e);
                    return null;
                }
            };
        } catch (NoSuchMethodException | IllegalAccessException | SecurityException e) {
//            Gdx.app.debug("PluginLoader", //
//                    String.format("No public constructor not found: %s(%s, %s)", clazz.getName(),
//                            constructorParam1.getName(), constructorParam2.getName()),
//                    e);
            return null;
        }
    }

    /**
     * Read the value of a constant in a class
     *
     * @param <T>       Type of the constant
     * @param clazz     The class
     * @param fieldName Name of a static final field in <code>clazz</code>
     * @param fieldType Type of the field
     * @return Value of <code>fieldName</code>
     */
    @SuppressWarnings("unchecked") // safe, since we check type dynamically
    public static <T> T getConstant(Class<?> clazz, String fieldName, Class<T> fieldType) {
        try {
            Field field = clazz.getField(fieldName);
            if ((field.getModifiers() & CONSTANT_MODS) != CONSTANT_MODS)
                return null; // it's not public static final
            if (!fieldType.isAssignableFrom(field.getType()))
                return null; // wrong type

            field.setAccessible(true); // override access control
            return (T) field.get(null); // get value

        } catch (NoSuchFieldException e) {
            return null;
        } catch (SecurityException | IllegalAccessException e) {
//            Gdx.app.debug("PluginLoader", //
//                    String.format("Failed to access constant %s::%s", clazz.getName(), fieldName, e));
            return null;
        }
    }
}

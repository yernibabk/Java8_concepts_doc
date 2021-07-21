# Java8_concepts_doc
practice

*Lambda Expression:*

-> To make instances of anonymous classes easier to write and ready.

Let’s use an anonymous class:

```Java
FileFilter fileFilter = new FileFilter() {
  @Override
  public boolean accept(File file) {
    return file.getName().endsWith(".java");
  }
};
```

This is a java 8 lambda expression:

```Java
FileFilter fileFilter = (File file) -> {file.getName().endsWith(".java");
```

**What is the type of a lambda expression?**

Answer: a functional interface (A functional interface is an interface with only one abstract method[run() method of Runnable interface and methods from Object class don't count ])

**Can a lambda be put in a variable?**

Answer: Yes
```Java
Comparator<String> c = (String s1, String s2) ->  Integer.compare(s1.length(), s2.length());
```
Consequences: a lambda can be taken as a method parameter, and can be returned by a method.

**Is a lambda expression an object?**

The answer is complex, but no.
Exact answer: a lambda is an object without an identity.


## Functional Interfaces ToolBox

1. New package: java.util.function
2. With a rich set of functional Interfaces

**4 categories:**

1. Supplier

```Java
@FunctionalInterface
public interface Supplier<T> {
  T get();
}
```
2. Consumer/BiConsumer

```Java
@FunctionalInterface
public interface Consumer<T> {
  void accept(T t);
}
```

```Java
@FunctionalInterface
public interface Consumer<T, U> {
  void accept(T t, U u);
}
```
3. Predicate/BiPredicate

```Java
@FunctionalInterface
public interface Predicate<T> {
  boolean test(T t);
}
```

```Java
@FunctionalInterface
public interface BiPredicate<T, U> {
  boolean test(T t, U u);
}
```
4.Function/ BiFunction

```Java
@FunctionalInterface
public interface Function<T, R> {
  R apply(T t);
}
```

```Java
@FunctionalInterface
public interface BiFunction<T, U, R> {
  R apply(T t, U u);
}
```
4. (i). Function/UnaryOperator

```Java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
}
```

4. (ii). BiFunction/BinaryOperator

```Java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {
}
```

*More Lambda expression syntax*

- Most of the time, parameter types can be omitted

```Java
Comparator<String> c = (String s1, String s2) -> Integer.compare(s1.length(), s2.length());
```

- It becomes:

```Java
Comparator<String> c = (s1, s2) -> Integer.compare(s1.length(), s2.length());
```

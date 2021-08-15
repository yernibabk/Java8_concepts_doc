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

**Method References**

- This Lambda expression:

`Consumer<String> c = s -> System.out.println(s);`

- Can be written like that:

`Consumer<String> c = System.out::println;`

- Method Reference do the same job as lambda function.

- This lambda expression:

`Comparator<Integer> c = (i1, i2) -> Integer.compare(i1, i2);`

- Can be written like that:

`Comparator<Integer> c =Integer::compare;`

## How do we process data in Java?

- Where are our objects?

Most of the time: in a Collection ( or maybe a List, a Set or a Map ) [instance of collection]

- Can I process this data with lambdas?

```Java
List<Customer> list = ....;
list.forEach(customer -> System.out.println(customer));
```

Or:

```Java
List<Customer> list = ....;
list.forEach(System.out::println);
```
### Can I process this data with Lambdas?

The answer is : Yes.

- we can write :

```Java
List<Customer> list = ...;
list.forEach(System.out::println);
```

- Here forEach method is available for all the Iterables of the collection framework.

- But... where does this forEach method come from?

### How to add methods to Iterable?

- Without breaking all the existing implementations?

```Java
public interface Iterable<E> {

  //the usual methods

  void forEach(Consumer<E> consumer);
}
```
- Refactoring these implementations is not an option.

### How to Add Methods to Iterable?

- If we cant put the implementation in ArrayList, then..

```Java
public interface Iterable<E> {

  // the usual Methods

  default void forEach(Consumer<E> consumer) {
    for (E e: this) {
      consumer.accept(e);
    }
  }
}
```

## Default Methods

- This is a new Java 8 Java8_concepts_doc

- It allows to change the old interfaces without breaking the existing implementations

- It also allows new patterns!

- And by the way...

- Static methods are also allowed in Java 8 interfaces!


*Examples of new Patterns*

Predicate:

```Java
Predicate<String> p1 = s -> s.length() < 20;
Predicate<String> p2 = s -> s.length() > 10;

Predicate<String> p3 = p1.and(p2);

Predicate<String> id = Predicate.isEqual(target);

```

# Steam API and Collectors

**Map/Filter/Reduce**

Example:

- Let's take a list a Person

List<Person> list = new ArrayList<>();

- Suppose we want to compute the
<<average of the age of the people older than 20>>

`Step 1:` mapping

- The mapping step takes a List<Person> and return a List<Integer>.

- The size of both lists is the same.

(for the given type of list and returns a list of the another type)

`step2:` filtering

- The filtering step takes a List<Integer> and return a List<Integer>

- But there some elements have filtered out in the process.

(for the given type of list and returns a list of the same type)

`step 3:` average

- This is the reduction step, equivalent to the sql aggregation.

(It's just a simple function that will resume all the integers in one integer )

## [Stream](https://github.com/yernibabk/Java8_concepts_doc/blob/main/Stream.md)

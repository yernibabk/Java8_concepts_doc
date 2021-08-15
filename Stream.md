## Stream

`What is a stream?`

- Technical answer: a typed interface

```Java
public interface Stream<T> extends BaseStream<T, Stream<T>> {
  // ....
}
```
`What does it do?`

It gives ways to efficiently process large amounts of data.. and also smaller ones.

`what does efficiently means?`

Two things:
 - In Parallel, to leverage the computing power of multicore CPUs

 - Pipelined, to avoid unnecessary intermediary computations.

`Why can't a Collection be a Stream?`

- Because Stream is a new concept, and we don't want to change the way the Collection API works.

`What is a stream?`

- An object on which one can define operations.
- An object that doesn't hold any data
- An object that should not change the data it processes.
- An object able to process data in << one pass >>
- An object optimized from the algorithm point of view,  and able to process data in parallel.

Many Patterns:

- First operation: forEach()

```Java
List<Person> persons = ...;

Stream<Person> stream = persons.stream();
stream.forEach(p -> System.out.println(p));
```

- Prints all the elements of the list.
- It takes an instance of Consumer as an argument.

- Only way to have several consumers on a single Stream.

```Java
List<String> result = new ArrayList<>();
List<Person> persons = ...;

Consumer<String> c1 = result::add;
Consumer<String> c2 = System.out::println;

persons.stream().forEach(c1.andThen(c2));
```

- Because forEach() does not return anything.

**A second Operation: Filter**

- Example:

```Java
List<Person> list = ....;
Stream<Person> stream = list.stream();

Stream<Person> filtered = stream.filter(person -> person.getAge() > 20);
```

- Takes a predicate as a parameter:

```
Predicate<Person> p = person -> person.getAge() > 20;
```

- Predicate interface, with default methods:

```Java

public interface Predicate<T> {

  boolean test(T t);

  default Predicate<T> and(Predicate<? super T> other) { ... }

  default Predicate<T> or(Predicate<? super T> other) { ... }

  default Predicate<T> negate() { ... }

  static <T> Predicate<T> isEqual(Object o) { ... }

}
```

- Predicates combinations examples:

```Java
Predicate<Integer> p1 = i -> i > 20;
Predicate<Integer> p1 = i -> i < 30;
Predicate<Integer> p1 = i -> i == 0;

Predicate<Integer> p =  p1.and(p2).or(p3); // (p1 AND p2) OR p3
Predicate<Integer> p =  p3.or(p1).and(p2); //  (p3 OR p1) AND p2
```
- Warning: method calls do not handle priorities.

- Example:

```
Predicate<String> p = Predicate.isEqual("two");
```

- Use case:

```Java
Predicate<String> p = Predicate.isEqual("two");
Stream<String> stream1 = Stream.of("one", "two", "threee");
Stream<String> stream2 = stream1.filter(p);

```
- The filter methods returns a stream.
- This Stream is a new instance.

- Question: what do i have in this new Stream>

- The right answer is: nothing, since a Stream does not hold any data.

- So, what does this code do?

```Java

List<Person> list = ....;
Stream<Person> stream = list.stream();

Stream<Person> filtered = stream.filter(person -> person.getAge() > 20);
```

- Answer is: nothing

  *This call is only a declaration, no data is processed.

- The call to the filter method is lazy.

- And all the methods of Stream that return another Stream are lazy.

- Another way of saying it:

  **An Operation on a stream that returns a Stream is called an intermediary operation.**

  - What does this code do?

  ```Java
      List<String> result = new ArrayList<>();
  		List<String> num = Arrays.asList("one", "two", "threee", "four");
  		Predicate<String> p = n -> n.length() > 3;

  		num.stream().peek(System.out::println).filter(p).peek(result::add);

  ```
 -  Answer: nothing!

 - This code does not print anything.

 - The list <<result>> is empty.

*Summary:*

- The Stream API defines intermediary operations.

- We saw 3 operations:

- forEach(Consumer) (not lazy)

- peek(Consumer) (lazy)

- filter(Predicate) (lazy)

##Mapping operation

Example:

```Java
List<Person> list = ....;
Stream<Person> stream = list.stream();
Stream<Person> names = stream.map(person -> person.getName());
```

- map() returns a Stream, so it is an intermediary operation.

- A Mapper is modeled by the Function interface

- .. with default methods to chain and compose mappings

```Java
public interface Function<T, R> {

  R apply(T t);

  default <V> Function<V, R> compose(Function<V, T> before);

  default <V> Function<T, V> andThen(Function<R, V> after);

  static <T> Function<T, T> identity() {
    return t -> t;
  }
```

*Flatmapping Operation*

- Method flatMap()

- Signature:

```
<R> Stream<R> flatMap(Function<T, Stream<R>> flatMapper);

<R> Stream<R> map(Function<T, R> mapper);
```

- If the flatMap was a regular map, it would return a Stream<Stream<R>>

-But it is a flatMap!

- Thus the <<stream of streams >> is flattened, and becomes a stream.

- Java 8 Stream flatMap() method is used to flatten a Stream of collections to a stream of objects. The objects are combined from all the collections in the original Stream.

- In very layman terms, flattening is referred to as merging multiple collections/arrays into one.

**Reduction**

- And what about the reduction step?

- Two kinds of reduction in the Stream API.

- 1st: aggregation = min, max, sum, etc..

How does it works?

```Java
List<Integer> ages = ...;
Stream<Integer> stream = ages.stream();
Integer sum = stream.reduce(0, (age1, age2) -> age1 + age2);
```

- 1st argument: Identity element of the reduction operation
- 2nd argument: reduction operation, of type BinaryOperator<T>

*BinaryOperator*

- A BinaryOperator is a special case of BiFunction

```Java
@FunctionalInterface
public interface BiFunction<T, U, R> {

    R apply(T t, U u);
    //plus default methods
}
```

```Java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {

    //T apply(T t1, T t2);
    //plus default methods
}
```
*Identity Element*

The BiFunction takes two arguments, so...

- What happens if the Stream is empty?

- The reduction of an empty Stream is the identity element.

- What happens if the Stream has only one element?

- If the Stream has only one element, then the reduction is that element.

Examples:

```Java
Stream<Integer> stream = ...;
BinaryOperation<Integer> sum = (i1, i2) -> i1 + i2;
Integer id = 0; //identity element for the Summary

int red = stream.reduce(id, sum);
```
```Java
Stream<Integer> stream = Stream.of(1, 2, 3, 4);

int red = stream.reduce(id, sum);
System.out.println(red);
```
Will print:
```
10
```

Optionals

- Then what is the return type of this call?

```Java
List<Integer> ages = ...;
Stream<Integer> stream = ages.stream();
Optional<Integer> max = stream.max(Comparator.naturalOrder());
```
- Optional means << there might be no result >>

- How to use an Optional?

```Java
Optional<String> opt = ...;
if(opt.isPresent()) {
  String s = opt.get();
} else {
  ...
}

```

- The method orElse() encapsulates both calls.

String s = opt.orElse("") ; //defines a default value

- The method orElseThrow() defines a thrown exception

```
String s = opt.orElseThrow(MyException::new); //lazy construct.
```

**Reductions**

- Available reductions:

- max(), min(), count().

- Boolean reductions:

- allMatch(), noneMatch(), anyMatch()

- Reductions that return an Optional

- findFirst(), findAny().

- Reductions are terminal operations.

- They trigger the processing of the data.

Example:

```Java
List<Person> persons = ...;

Optional<Integer> minAge = persons.stream()
                    .map(person -> person.getAge()) //Stream<Integer>
                    .filter(age -> age > 20 )   //Stream<Integer>
                    .min(Comparator.naturalOrder()); //terminal operation
```

Example, optimization:

```Java
List<Person> persons = ...;
persons.stream().map(person -> person.getLastName()).allMatch(length < 20);  //termincal op.
```
- The map / filter / reduce operations are evaluated in one pass over the data.

- Reduction seen as an aggregation.

- Optional: needed because default values cant be always defined.

## Collectors

- There is another type of reduction.

- Called <<mutable>> reduction.

- Instead of aggregating elements, this reduction put them in a << container >>.

*Collecting in a String*

- Example:

```Java
List<Person> persons = ...;
String result =
persons.stream().filter(person -> person.getAge()> 20).map(Person::getLastName).collect(Collectors.joining(", "));
```
- Result is a String with all the names of the people in persons, older than 20, separated by a comma.

- Example:

```Java
List<Person> persons = ...;
List<String> result =
persons.stream().filter(person -> person.getAge()> 20).map(Person::getLastName).collect(Collectors.toList());

```
- Result is a List of String with all the names of the people in persons, older than 20.

Example:

```Java
List<Person> persons = ...;
Map<Integer, List<Person>> result =
persons.stream().filter(person -> person.getAge() > 20).map(Person::getLastName).collect(Collectors.groupingBy(Person::getAge));

```
- Result is a Map containing the people of persons, older than 20.

1. The keys are the ages of the people.
2. The values are the lists of the people of that age.

- It is possible to << post-process >> the values, with a downstream collector.

```Java
List<Person> persons = ...;
Map<Integer, Long> result =
persons.stream().filter(person -> person.getAge() > 20).map(Person::getLastName).collect(Collectors.groupingBy(Person::getAge,
Collectors.counting())); // the downstream collector

```
- Collectors.counting() just counts the number of people of each age.

**So What is a Stream?**

- An object that allows one to define processings on data.
  1. There is no limit on the amount of data that can be processed.

- Those processings are typically map/filter/reduce operations.

- Those processings are optimized:
 - First,  we define all the operations.
 - Then, the operations are triggered.

Last Remark:
 - A Stream cannot be << reused >>
 - Once it has been used to process a set of data, it cannot be used again to process another set.
 

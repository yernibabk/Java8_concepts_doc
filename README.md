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

# SOFTWARE ENGINEERING IN JAVA

## Session 3 (11/06/2018)
- Github pull request comments addressing.
- Ways to make the code cleaner and shorter:
	- Fixing indentation.
	- Inverting if statements.
	- Returning inside if statements.
	- Keep methods short if possible.
- Used `ArrayList` to keep the contents of the opened file.
- Manual importing of libraries (jars files).
- Created `file.json` in databasic to test `insert command`

### Notes:

#### Making code cleaner and shorter
Roger created a branch called `javier` in his GitHub, there he made a pull request (aka. PR) addressing to me several precisions in regards to clean code and good practices. In this session we were reviewing some of the Roger's comments on that github's pull request and other subjects that arose while we were reviewing my code:

##### Fixing indentation

In Java generally indentation is of 4 spaces or 1 tab.

In order to fix it, and always be need, use `Alt+Shift+L` or right mouse click over the file (in the left files list of project) and click on `reformat code`. In general is a good practice to do it frequently.

##### Inverting if statements

The use of nested `if-else` statements produce indentation in our code and makes it harder to follow. In order to solve this we can `invert` our if-else statements hitting `Alt+Enter` over the `if` word and pressing `Enter` or `Click` over `Invert the 'if' condition`

This inverting utility also negates the `if` statement if it's true or makes it true if it's false.

Example:

Inverting the following condition:

```java
		if (args[0].equals(DataBasicCommands.INSERT.toString())) {
			validateInsert(args);
		}
```

Results in:
```java
		if (!args[0].equals(DataBasicCommands.INSERT.toString())) {

		} else {
			validateInsert(args);
		}
```
And vice versa

##### Returning inside if statements

In the `validateInput` method of `InputProcessor` Class, we used `return` statements inside the `if` statements (or other methods called from there) in order to avoid use of nested `else if` conditions and make code shorter and nicer to read. This is a nice trick to use because we write short methods. If we used this technique on longer methods it could lead to confusing code. Many (3+) possible returns in a single method is bad.

Finally, that method finished with two if statements to evaluate if `args[0]` corresponds to:
- `help` command
- `insert` command.

In this way, the `query` command option stands as default outside of any `if` and, implicitly, of their `return` instructions. So `query` command is executed when no arguments are provided:
```java
        // Check commands
        if (args[0].equals(DataBasicCommands.HELP.toString())) {
            help();
            return;

        }

        if (args[0].equals(DataBasicCommands.INSERT.toString())) {
            validateInsert(args);
            return;
        }

        // Default, make a query
        // TODO: Validate and process the query
        System.out.println("Querying...");
        return;
```

##### Keep methods short when possible

Is a general rule and good practice to keep short methods length. An estimative of 10-20 lines of code could work, but a good code recommendation from Roger is to maintain 6 lines of code maximum per method.

In order to make methods shorter, we can delegate part of our code to new methods. This can be done in IntelliJ IDEA with `Ctrl+Alt+m` or selecting desired text, right mouse click over it and click in `Refactor/Extract/Method` option. After choose `method name` and `parameters`.

For `Main` method, the recommended length is just 1 line. So this:
```java
InputProcessor inputProcessor = new InputProcessor();
inputProcessor.validateInput(args);
```
Could be written as:
```java
new InputProcessor().validateInput(args);
```
Indeed, in the future this will be:
```java
new Application().start(args);
```

#### Used `ArrayList` to keep the contents of the opened file

For [Reading files] I was using a `Stream`. After read each line with stream, those are been saved into an [ArrayList], to after be concatenated in a single line. In regards there is a pending task (see TODO)

```java
    private List<String> getLines(String fileName) throws IOException {

        Stream<String> linesStream = Files.lines(Paths.get(fileName));
        List<String> lines = new ArrayList<>();
        System.out.println("<!-----Read all lines as a Stream-----!>");
        linesStream.forEach(s -> lines.add(s));
        linesStream.close();
        return lines;

    }
```

`ArrayList` is an implementation of `List`, however the type of the `lines` variable and also the `getLines` method (and returned value) is `List` not `ArrayList`. This is specially important, since this allows us to implement different kind of lists -if desired- and not have to change all the code in our application (methods, varibles, etc.) for this reason.

For other side, when we declare the type to be held inside our `List` in the variable's type declaration, it's not necessary to write the datatype in the `<angle brackets>` of the implemented list `ArrayList`. So we can write:
```jav
        List<String> lines = new ArrayList<>();
```
Instead of:
```java
       List<String> lines = new ArrayList<String>();
```

### Resources:
- [Set input arguments in IntelliJ IDEA](https://stackoverflow.com/a/11159341)
- [Command-Line Arguments](https://docs.oracle.com/javase/tutorial/essential/environment/cmdLineArgs.html)
- [Comparing Strings in Java](https://stackoverflow.com/a/513839)
- [Comparing Strings with 'enum' values](https://stackoverflow.com/a/9858135)
- [Printing all 'enum' values](https://stackoverflow.com/a/14413618)
- [Enums values in lowercase](https://stackoverflow.com/a/26893053)
- [Bolding text](https://stackoverflow.com/a/29109958) and [ANSI escape reference](http://ascii-table.com/ansi-escape-sequences.php)
- [Reading files]
- [ArrayList]
- [Steps for adding external jars in IntelliJ IDEA](https://stackoverflow.com/a/1051705)
- [JSON manipulation library 'jackson'](http://repo1.maven.org/maven2/com/fasterxml/jackson/core/)
- [JSON tutorial](https://www.journaldev.com/2324/jackson-json-java-parser-api-example-tutorial). See `Jackson JSON – Read Specific JSON Key`
- Other JSON tutorials [here](https://www.tutorialspoint.com/jackson/jackson_objectmapper.htm) and [here](http://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/)
- [JAVA Exceptions (spa)](https://users.dcc.uchile.cl/~lmateu/Java/Transparencias/.arch/all.htm)

[Reading files]: https://examples.javacodegeeks.com/core-java/java-8-read-file-line-line-example/
[ArrayList]: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

### TODO

- ~~To apply all the comments and suggestions in Guthub pull's resquest before merge with Javier branch in Roger's Github~~
- ~~To use JSON validator library~~
- Validate and process the query (having some errors)
- ~~Load the file to a single line from the beginning (now doing it in several steps) (JSON is loading now)~~

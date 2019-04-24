# Refactoring Tips

### Amazing IntelliJ (Mac) shortcuts

- `command-option + m`: Extract method - If you spot that a method does more than one thing (breaks `Single Responsibility Principle`)
- `command-option + n`: Inline method/variable if method/variable is used only once, and not going to hinder readability.
- `command-option + c`: Extract constant - for `magic literals`. Variables like this should only have to be changed in one location
- `command-option + v`: Extract local variable from literal or expression. When you realise you need the same value twice.
- `command-option + p`: Extract parameter from variable. When you realise things get cleaner if this is passed in as an argument.

- Main classes should be 1 line. This line should create a `new` something, and immediately call a method on it, passing in the command line arguments if necessary. There is therefore not much need to test it.
- Avoid OR/AND in a method/class names.
- Only 1 public class per file. This class must be named the same as the file it resides in. This is enforced by the compiler.
- Paste class code when in the "Project pane" -> auto generate class.
- POJOs get tested through their use in other classes. Don't write unit tests for POJOs.
- Default considerations: seed data, testing, documentation.

Limit the consequences of change to create a more maintainable design.

Refactoring, make sure that any incomprehensible code becomes comprehensible by extracting a method with a sensible, descriptive name.
Code blocks in for/if statements can be made to be one line long - just the name of a method that describes what is going on.

### Good guidelines for progressing on a programming task

- Don't commit code that doesn't compile.
- Delete redundant tests
- Commit after refactor (not 'green' or 'red')
- Leave the latest push in a green state, especially when there's a PR associated with that latest commit
- Make sure each sprint has its requirements.
- Priority: get a clean master
- A PR should be approved by a reviewer before merging
- Re-add reviewers after addressing review comments
- design should be solid before implementing

Refactoring blog post ideas:
- DRY
- Don't change the semantics (the intention or meaning) of a feature

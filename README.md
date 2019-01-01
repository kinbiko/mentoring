# Syllabus

## Lesson 0

This lesson contains some time-but-not-attention-consuming tasks such as downloading and installing software.
The following steps **must** be done prior to the first lesson.

### Homework

1. If you're running Windows, use a virtual machine:
    1. Install VirtualBox
    1. Download a recent Ubuntu Linux image
    1. Install the image.
1. Download and install Slack
1. Install Java 8 or above.
1. Install IntelliJ. Include the Vim plugin (but immediately disable)

## Lesson 1

### Session

In this lesson you will learn how to meet the other students and communicate with the community using Slack, get familiar with GitHub, and write your first program in Java.

1. Introduction to Slack.
    1. Install and join workspace
    1. Ask for help in the `#help` channel
    1. Share fun and interesting stuff in `#chill-chat`
    1. Listen for announcements in `#general`
    1. Slack etiquette

1. Introduction to GitHub.
    1. Create account if you don't already have one
    1. Edit the `README.md` file and commit
    1. Markdown basics

1. Java: Hello world

### Homework

1. Inform admins about your GitHub user name so that they may create a repository for you under the `kinbiko-mentoring` organisation.
1. Install VS Code (or other good editor)
1. Install Git

## Lesson 2

In this lesson we'll introduce the command line as a tool for.

### Session

1. Getting comfortable with using the command line.
1. Conditionals
1. Data types and variables
1. String concatenation

### Homework

This homework expands upon the hello world from previous lesson.

1. Create variables of each type, and print strings when certain conditions are met
1. Create nested if statements
1. Simplify a given set of nested if-statements

Practice your command line skills:

1. Read [this blog post](https://kinbiko.com/shell/basic-bash-commands/) to make sure you've got the basics of working in the command line covered.

## Lesson 3

### Session

1. Logical `and`, `or`, `not` and `not equal`.
1. Classes and objects (theory)

### Homework

1. Simplify the code from last week's homework even more by using `&&`, `||` and `!`
1. Look at any relatively complex item in your surroundings. How would you describe it in OOP as an object with methods and properties? Be as detailed as you can.

## Lesson 4

### Session

1. Writing classes
1. Using an instance of a class
1. Calling methods
1. Make `main()` a one-line method
1. Constructors

### Homework

Without worrying too much about the accuracy of your program, attempt to implement the item from Lesson 3's homework. In addition to the method and property names of each class, be sure to also think about constructor and method parameters, and method return values.

## Lesson 5

### Session

1. Switch statements
1. Enums
1. Arrays
1. For loops
1. While loops

### Homework

The homework for this lesson is your first mini-project exercise in modelling the real world using object oriented programming. Your task is to write the code that models a stopwatch.

#### Requirements

1. For every second the program should print how many hours, minutes and seconds since the program started executing.
1. When the program is executed the output should look [something like this](homework/outputs/lesson-4.txt)
1. The second 0:0:0 should not be printed.
1. The program need only work until (and including) 12:0:0

#### Hints

1. Imagine an analogue stopwatch. What are the components? Try to model those like you did in the homework for Lesson 3. Strip out all the bits that aren't necessary in order to solve this problem.
1. A solution to this problem can comfortably be written in less than 50 lines of code. If your solution requires more than 100 lines of code, it might be time to reconsider your approach.
1. You may copy the following method for your application without understanding it (it will be covered later on). This method, when called, will pause the execution of the program for the given number of milliseconds.
    ```java
        void pause(int milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                // Intentionally empty
            }
        }
    ```

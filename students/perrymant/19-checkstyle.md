# Checkstyle

Checkstyle is a linter. It helps you stay consistent in terms of the style of your Java code. Sometimes it can even spot bugs, or design smells. It can find class design problems, method design problems. It also has the ability to check code layout and formatting issues.

`.travis.yml`:
```
- ./gradlew test --info --stacktrace
- ./gradlew checkstyleMain
- ./gradlew checkstyleTest
```

`build.gradle`:
```
apply plugin: 'checkstyle'

checkstyle {
    toolVersion = "8.16"
    configFile = new File(rootDir, "checkstyle.xml")
}
```

checkstyle.xml:
These are the checkstyle rules to add to the project.
- Where do you go to get these rules, and how do you find the relevant ones to add/supress?

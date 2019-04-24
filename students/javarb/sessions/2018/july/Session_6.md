# SOFTWARE ENGINEERING IN JAVA

## Session 6 (03/07/2018)

- Notes in Java
- Review of errors handling
- Git merge conflicts and solutions
- Next class in 14 days (more Git tricks)

### Notes:


**Notes in Java**

There is different way to make notes in Java apart than the usual comment blocks `/* */`. We can enter a Java Docs comment writing /** and pressing enter and the IDE 
will create a documentation block for us

```java
    /**
     * 
     * @param args
     */
```

For example 

```java
protected void init(String[] args){

    processInput(args);

}

/**
 * Something
 * @param args
 */
void processInput(String[] args){
}

```

After that, documentation can ve seen placing cursor over the desired method and pressing ctrl+q (view/Quick documentation).

In order to render the documentation to HTML we can press Tools/Generate Java Docs, specifying options and a path, for example `~/javadoc/`. Then, we press ok and this 
will render a website with all documentation visible at `~/javadoc/index.html`

The recommendation is to document (using Java Docs) over public and interface methods, because these are the kind of methods used from outside, so documentation us 
useful.

**Tip: Find method usages/calls**

In order to find the places where a method is used, place cursor over it and Alt+Shift+7 or right click and press *Find Usages*

**Git merge conflicts and solutions**

When two or more persons are working in the same file and a pull is made, there could exist merge problems that cannot be automatically fixed and needs human 
intervention. 

- **First Case**

For example I had some differences between local and GitHub version of the file `.idea/workspace.xml`, so when I tried to do a pull request I couldn't and the system 
said I had to add and commit local changes. So at the end I finished with 2 commits ahead ot the master.

One solution could have been push this changes and not worry about this two commits (no problem in doing so), but the other solution was to `reset` to the state of the 
`master` branch in `origin` (GitHub).

Following the `reset` process, but before of `reset` we made another commit to test, so now we had 3 commits ahead `fb187fb (origin/master, origin/HEAD)`:

```bash
$ git status
On branch master                                                                                                        
Your branch is ahead of 'origin/master' by 2 commits.
  (use "git push" to publish your local commits)    

$ git add .
$ git commit -m "Working in session 6"

$ git log --oneline
91f13ae (HEAD -> master) Working in session 6
2685643 before pull session 5 work
fae1e30 before pull session 5 work
fb187fb (origin/master, origin/HEAD) Cleaning code, added Application class (session 5)
56eaf73 added work in sessions 4 and 5
3cd1ccf Creating project package
...
```

Then, we reset and now we can see how our branch is setted in `fb187fb (HEAD -> master, origin/master, origin/HEAD)`:

```bash
$ git reset fb187fb

$ git log --oneline
fb187fb (HEAD -> master, origin/master, origin/HEAD) Cleaning code, added Application class (session 5)
56eaf73 added work in sessions 4 and 5
3cd1ccf Creating project package

$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
```

And we can follow making changes and pushing to remote:

```bash
$ git add .
$ git commit -m "Working in session 6"
$ git status       
On branch master
Your branch is ahead of 'origin/master' by 1 commit.

$ git push
Counting objects: 12, done.
```

- **Secound Case**

Following, in order to make another test of this, we made a change in the file `src/co/org/osso/databasic/Application.java`, amend the last commit "Working in session 
6", and tried to pull remote changes for cause a merge conflict.

First, we made the local changes and in status we can see it:

```bash
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   .idea/workspace.xml
        modified:   src/co/org/osso/databasic/Application.java
```

After, we amend the commit "Working in session 6" which is the last commit `origin/master`. Then, when we check the status, we can see a message saying our branches 
have `diverged`:

```bash
$ git add . 
$ git commit -m "Working in session 6" --amend
$ git status
On branch master
Your branch and 'origin/master' have diverged,
and have 1 and 1 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)
nothing to commit, working tree clean
```

That means the same commit is different in each branch (local and remote), so we have to merge. But if we try to pull the remote version into our own version (merging 
last push remote file(s) into recently local modified file(s)) this is not possible to make automatically and a merge conflict message is shown:

```bash
$ git pull 
Auto-merging src/co/org/osso/databasic/Application.java
CONFLICT (content): Merge conflict in src/co/org/osso/databasic/Application.java
Auto-merging .idea/workspace.xml
CONFLICT (content): Merge conflict in .idea/workspace.xml
Automatic merge failed; fix conflicts and then commit the result.
```

If we look at the status again, we can see the files involved in the conflict. In this example 2 files:

```bash
$ git status
On branch master
Your branch and 'origin/master' have diverged,
and have 1 and 1 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)
You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
  (use "git add <file>..." to mark resolution)

        both modified:   .idea/workspace.xml
        both modified:   src/co/org/osso/databasic/Application.java
```

In order to see differences between those files, we use `git diff`:

```bash
$ git diff
```

This displays content with `less` depending on the lenght of the differences found.

At this point we can enter into the files and merge changes manually, but there is a trick we can use if we know what version we want to keep. For the first file we 
want to stay with our version and discard remote changes:

```bash
$ git checkout --ours .idea/workspace.xml
```

Now, when we run `git diff` again we can see how the conflicts has been solved for the previous file, but there is still one:

```bash
$ git diff
diff --cc .idea/workspace.xml
index 665de41,2475aeb..0000000
--- a/.idea/workspace.xml
+++ b/.idea/workspace.xml
diff --cc src/co/org/osso/databasic/Application.java
index ebeb8d7,ed3dfbf..0000000
--- a/src/co/org/osso/databasic/Application.java
+++ b/src/co/org/osso/databasic/Application.java
@@@ -28,7 -28,6 +28,10 @@@ class Application
          * why I not need to return anymore from inside try/catch blocks in JacksonObject mapper, etc?
          * I catch all errors here?
          * Handling all errors here
++<<<<<<< HEAD
 +        * 
++=======
++>>>>>>> 3c949e889c90cd5eabb614bfe2665d84e3a9872c
          * */
          // Validate input
          try {
```

The conflicting lines are between some special tags, between `<<<<<<< HEAD` and `=======` are the local changes where `HEAD` refeers to to the last commit made and 
between `=======` and between `>>>>>>> 3c949e889c90cd5eabb614bfe2665d84e3a9872c` is the remote content that is meant to be merged into local commit. The random number 
is the SHA1 git commit hash generated by git, so we could do  `git checkout 3c949e889c90cd5eabb614bfe2665d84e3a9872c` if we would want to go to that exact commit.

And what it's showing us is that remotely the file has a space which we deleted locally.:

```
        * why I not need to return anymore from inside try/catch blocks in JacksonObject mapper, etc?
          * I catch all errors here?
          * Handling all errors here
++<<<<<<< HEAD
 +        * 
++=======
++>>>>>>> 3c949e889c90cd5eabb614bfe2665d84e3a9872c
          * */
```

Now, as we want to stay with the remote version we used:

```bash
$ git checkout --theirs src/co/org/osso/databasic/Application.java
```

And if we check for differences now, they have gone:

```bash
$ git diff
diff --cc .idea/workspace.xml
index 665de41,2475aeb..0000000
--- a/.idea/workspace.xml
+++ b/.idea/workspace.xml
diff --cc src/co/org/osso/databasic/Application.java
index ebeb8d7,ed3dfbf..0000000
--- a/src/co/org/osso/databasic/Application.java
+++ b/src/co/org/osso/databasic/Application.java
```

After, we remove the space we did and add local changes again:

```bash
$ git add .

$ git status
On branch master
Your branch and 'origin/master' have diverged,
and have 1 and 1 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)
All conflicts fixed but you are still merging.
  (use "git commit" to conclude merge)

Changes to be committed:

        modified:   src/co/org/osso/databasic/Application.java
```

And we check again differences between staged changes (before commit)

```bash
$ git diff --cached

diff --git a/src/co/org/osso/databasic/Application.java b/src/co/org/osso/databasic/Application.java
index ebeb8d7..ed3dfbf 100644
--- a/src/co/org/osso/databasic/Application.java
+++ b/src/co/org/osso/databasic/Application.java
@@ -28,7 +28,6 @@ class Application{
         * why I not need to return anymore from inside try/catch blocks in JacksonObject mapper, etc?
         * I catch all errors here?
         * Handling all errors here
-        * 
         * */
         // Validate input
         try {
```

We can see how show us that space has been removed ('-' mark):

```
@@ -28,7 +28,6 @@ class Application{
         * why I not need to return anymore from inside try/catch blocks in JacksonObject mapper, etc?
         * I catch all errors here?
         * Handling all errors here
-        * 
         * */
         // Validate input
         try {
```

Now we need to commit, but we do with the default message, and this open our default editor for edit the commit message and there we uncomment the lines could be of 
help to trace this merge confict. NOTE: The editor setted is under the `EDITOR` environment variable that can be set in our shell config file:

```bash
$ git commit
[master 9877520] merge branch 'master' of github.com:javarb/databasic

$ git push 
Counting objects: 12, done.

$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
```

Now in Github we can see in the list of commits:

```html
merge branch 'master' of github.com:javarb/databasic  …  9877520

@javarb
javarb committed 2 hours ago
 Conflicts:
    .idea/workspace.xml
    src/co/org/osso/databasic/Application.java
```

Finally, is recommended don't make `commit --amend` and `push -f` because that can potentially overwrite the work of others.


### Resources:
- [The finally Block][1]
- [The try-with-resources Statement][2]

[1]: https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html
[2]: https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html

### TODO:

- [x] What the keyword `finally` does in Java (common interview questions with it)

    [The finally Block][1] run code at the end of a try block execution. It's useful to put cleanup code as close streams. There is another way to do it with [The 
try-with-resources Statement][2]

- [x] Change notes in *databasic*

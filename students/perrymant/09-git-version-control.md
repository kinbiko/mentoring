# Git Version Control
Version Control is useful because:
- it's a system that records changes to a file or set of files over time so that you can recall specific versions later.
- important for documents that undergo a lot of revision and redrafting and is particularly important for code files because they can easily be changed by a number of different users.
- good for collaboration
- good for backup

## Common Git Commands
| Command                                    | What it does                                     |
|--------------------------------------------|--------------------------------------------------|
| `git clone <site info>`                    | Clone a remote repo to local                     |
| `git pull`                                 | Fetch from remote repo to local branch           |
| `git status`                               | Show the working tree status                     |
| `git add .`                                | Add all files that have changes to staging       |
| `git commit -m "your message goes here"`   | Make a commit and add message                    |
| `git push`                                 | Send local commit to remote                      |
| `git checkout master`                      | Look at your master branch                       |
| `git checkout <branch-name>`               | Look at a branch                                 |
| `git diff --cached`                        | See what's in the cached area (prior to staging) |
| `git reset --hard`                         | delete all branch progress                       |

## Merge conflicts

Happens when the same lines of a file have been changed on separate branches, and those branches are being merged.

Look like this:

```
<<<<<<<HEAD
current branch
===
Other code
>>>>>>>
```

Typically use a hybrid of both current and other when merging - but you must check carefully!

## See what's in the cached (staging) area

```bash
git diff --cached
```

## Stashing files temporarily without committing them

```bash
git status

(...)

Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

    modified:   .gitignore
    modified:   README.md
    modified:   src/main/java/io/github/perrymant/moneymaker/FileIO.java
    modified:   src/test/java/io/github/perrymant/moneymaker/FileIOTest.java
    deleted:    src/test/resources/dump/file_with_data
    deleted:    src/test/resources/dump/file_with_data_write_test

no changes added to commit (use "git add" and/or "git commit -a")
```

```bash
git add . # always add before stash
git stash
Saved working directory and index state WIP on add-fileIO: d9e4e63 build failed, changed gitignore file
```

```bash
git stash list # See all stashed changes

git pull
(...)
   fca0015..56a70b6  master     -> origin/master
(...)
```

```
git merge origin/master
Auto-merging README.md
CONFLICT (add/add): Merge conflict in README.md
Automatic merge failed; fix conflicts and then commit the result.
```
> Take it sloooooow!!!

```bash
git status

(...)
    both added:      README.md
(...)
```

Open file to resolve merge conflict, add and commit. Once you're done you can do `git stash pop` to return to the changes you were making before merging.

## Git branching

```
           /----------------*SubbranchB~~~~~~~~~~~~~~~~~~~~~~~~~\ Merge to master
          /------------*BranchA(PR)~~~~~~~*\Merge to master      \
···*-----*Master~~~~~~~~~~~~~~~~~~~~~~~~~~~~*~~~~~~~~~~~~~~~~~~~~~*~~~~~~>
                                  Time -->
```

Trunk based development: All branches are exactly one generation away from master.

```
            /-------*FeatureC~~~~~ PR ~~~~~~~~~~~~~~~~~~~~~~~~\ Merge
           /--------*FeatureB~~~~~ PR ~~~~~~~~~~~~~~\ Merge    \
          /---------*FeatureA~~~~~ PR ~~*\ Merge     \          \
···*-----*Master~~~~~~~~~~~~~~~~~~~~~~~~~~*~~~~~~~~~~~*~~~~~~~~~~*~~~~>
                                  Time -->
```

Scenario: I created a feature branch, and then updated Master. I wanted the new stuff from master -> feature branch:

```
          /------------*Feature-BranchA~~~~~~~~~~~~~~~~~~~~*~~~~~~~~~~>
···*-----*Master~~~~~~~~~~~~~~~~~~~~~~~~~~~~UpdatedMaster*/
                                  Time -->
```

### Get the latest master

```bash
git checkout master
git pull #get latest master
git checkout feature-branch
git merge master # merge master -> feature-branch
```

## working on a branch and want to delete all progress. `reset`

```bash
git reset --hard
```

### Rename git repo (issue with renaming directory to lowercase)

#### Basic rename (or move):

```bash
git mv <old name> <new name>
```

#### Case sensitive rename—eg. from casesensitive to CaseSensitive — you must use a two step:

```
git mv casesensitive tmp
git mv tmp CaseSensitive
```

## Git one-liner
When you are sure that you want to add all the changes you just made to the repo. adds/commits/pushes all in one go.

```bash
git add .;git commit -m "review comment: constructors in abstract classes";git push
```

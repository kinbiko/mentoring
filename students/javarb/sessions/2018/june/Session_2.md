# SOFTWARE ENGINEERING IN JAVA

## Session 2 (04/06/2018)
- Fork databasic project from github.com/kinbiko repo and clone locally
- Write the databasic requirements
- Write databasic design
- Initializate Java IntelliJ IDEA project into the cloned directory and commit changes

### Notes:
-  Design is very important, in design we just put the strategy to follow not implementation details, indeed Software engineers are problem solvers so if a problem arise we often amend the design before starting to code. There is a balance here of course. There's no point in doing 1 day of design if the implementation would take 1 hour.

- For this design stage of the project we have to consider what kind of operations are we going to expose in our application.
Initially, these are Insert and Query

- There is two ways to return to a previous state in Git
  - _'[git revert](https://www.atlassian.com/git/tutorials/undoing-changes/git-revert)'_ allows to undo changes made to an specific commit making one new just "inverted": Delete the added lines and add the deleted lines.

  - _'[git reset](https://www.atlassian.com/git/tutorials/undoing-changes/git-reset)'_ undo commits, and change history to make it as if the deleted commits never existed.

Because `git reset` deletes your history, it's generally preferred to use `git revert`, if necessary.

- When is necessary to correct some commit, use:
```bash
git add <exp>
git commit -m '<commit_text>' --amend
git push -f # Only need to force push if the previous commit was already pushed.
```
Where `<commit_text>` is the commit to be corrected and `<exp>` is a file or a glob to match.

**Be careful when using `push -f`** because this will overwrite the entire remote branch. So non `git reset` or `git revert` will work. This means there is a risk of losing data.

- I installed the Markdown plugin in IntelliJ IDEA

- When I was opening the project `databasic` I created from home this not have the src dir, so I made a new project in this folder. This is because Git only tracks **files** and not empty **directories**.

### Suggested lectures:
* [RFC-3999](https://www.ietf.org/rfc/rfc3339.txt) (Datetime standard)
* [Agile Manifesto](http://agilemanifesto.org/)
* [GIT reset, revert, checkout](https://www.atlassian.com/git/tutorials/resetting-checking-out-and-reverting)
* [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) (About interview problems)

### Homework
- ~~Design query and write first design code in Java (input and query).~~

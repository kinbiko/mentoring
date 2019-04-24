# SOFTWARE ENGINEERING IN JAVA

## Session 26 (26/02/2019)

- API design generalities
- Git - Merging lastest master changes in a branch

### Notes

#### API design generalities

##### Class's methods ambit

In the controller, declarin methods as public is a good "convention" to use becasuse they are going to be used from exterior. Even if as they're the starting point of apication they aren't to be called from anywhere.

On the other hand, in a well-designed application, services not need to be public since they're in the same package.

##### Referencing Class's methods in design

When we are writing definitions and want to point out a method we can write for example `Validator#validate(request)`. Here, the `#` character is showing a method inside a class. We can also write `validator.validate()` and we know that `validator` is an object.

If we write `Validator.validate(request)` (using point), means that the class is static, Spring will not have an instance of it, and therefore Spring will have nothing to manage.

So or `validator.validate(request)` or `Validator#validate(request)` are good ways to write methods calls when we are designing. But `Validator.validate(request)` will be understood as a static method call and unless that was the intent this way of writing, should be avoided.

**Note:** What has been said depends on the fact the programmer be following good practices in naming classes and methods, this is, capitalizing first letter in class names but not first letter of method names.

#### Git - Merging lastest master changes in a branch

When we're in a branch that isn't master and we want to integrate changes that were merged to master, we have to fetch (in really not matters from which branch) and then we merge from `origin/master`. In this way, all that is in `origin/master` will be in the desired branch (except those changes have been made in the branch particularly and are not in master):

```bash
$ git fetch 
remote: Enumerating objects: 1, done.
remote: Counting objects: 100% (1/1), done.
remote: Total 1 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (1/1), done.
From github.com:javarb/wallet
   23b9ef7..cce1d84  master     -> origin/master

$ git branch
  create-list-accounts
* create-list-accounts-eng-design
  master

$ git merge origin/master 
Auto-merging design/product-design-list-all-accounts.md
Merge made by the 'recursive' strategy.
 design/product-design-list-all-accounts.md | 42 +++++++++++++++++++++++++++++++++++++-----
 1 file changed, 37 insertions(+), 5 deletions(-)
```

After this, I can surely merge (trought pull request) from my branch to master branch without conflicts.

### Homework

- [x] Draw the Architectural Diagram
- [ ] Complete the Engineering Design for`new account` requirement.

# Notes on using Git & GitHub

## What exactly is the difference between the two?

From [StackOverflow](https://stackoverflow.com/questions/13321556/difference-between-git-and-github):

> **Git** is a revision control system, a tool to manage your source code history.
>
> **GitHub** is a hosting service for Git repositories.
>
> So they are not the same thing: **Git** is the **tool**, **GitHub** is the **service for projects that use Git**.
>
> *N.B. GitHub is a consequence of the existence of git and not the only hosting service.*

To install Git (on *Debian*-based linux systems), run the following commands in Terminal:

```shell
$ sudo apt-get update
$ sudo apt-get upgrade
$ sudo apt-get install git
```

Configure your GitHub credentials:

```shell
$ git config --global user.name "John Doe"
$ git config --global user.email johndoe@example.com
```

With the `--global` option, this needs only be done once.

## Getting *(cloning)* a Git Repository

You typically obtain a Git repository in one of two ways:

- Take a local directory that is currently not under version control, and turn it into a Git repository, or

- Clone an existing Git repository from elsewhere.

In either case, you end up with a Git repository on your local machine, ready for work.

For an existing folder:
```shell
$ cd /home/user/my_project
$ git init
```

This creates a *Git repository skeleton* - a new subdirectory named `.git` that contains all of the necessary files Git needs to manage the directory.

To clone a repo (over https):

```shell
$ git clone https://github.com/username/repo
or
$ git clone https://github.com/username/repo myrepo
```

creates a directory called `myrepo/`.

Similarly, over SSH:

```shell
$ git clone git@github.com:username/repo.git
or
$ git clone git@github.com:username/repo.git myrepo
```

For more detailed information, see [here](https://git-scm.com/book/en/v2/Git-Basics-Getting-a-Git-Repository).

## The Git life cycle

Check status of files, including any changes with:

```shell
$ git status
```

Use this **liberally**.

Example - creating a simple file called `README`

```shell
$ echo 'My Project' > README
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
Untracked files:
  (use "git add <file>..." to include in what will be committed)

    README

nothing added to commit but untracked files present (use "git add" to track)
```

To *stage* the untracked file:

```shell
$ git add README
or
$ git add . # adds all unstaged files in current directory
```

Once staged, create a commit, then push this commit to a GitHub repo you have write access too.

```shell
$ git commit -m "Type a message < 60 characters"
$ git push # pushes changes to GitHub
```

Enter credentials when prompted (HTTPS only).

## Tracking changes

In addition to GitHub, it's possible to track all changes you're about to commit within terminal, using the `--cached` flag to see everything that's in staging.

```shell
$ git add my-file-1 my-file-2
$ git diff --cached
```

and use keys `j` and `k` to navigate up and down.

Press `q` to exit.

## Checking out branches

Typically, pushing changes to GitHub is done through a "branch", and typically, this will be a branch off the `master` branch.

To see a list of all the branches available on the repo you are using, use:

```shell
$ git pull # updates your local system to reflect GitHub repo
$ git branch # lists branches in repo
```

N.B. Adding the `-a` modifier after `branch` displays *all* branches, both *local* and *remote*.

Choose a branch you wish to work in from those that are available, and *checkout*:

```shell
$ git checkout mybranch
```

Now, any changes pushed will be pushed to the branch with the same name in GitHub.

## Creating pull requests

> `master` is holy.
> \- RG, 2019

Once a change has been pushed to GitHub, it's now time to create a **pull request**. Here, a reviewer (a colleague or collaborator) will check any changes that have been made, and decide whether the content you have pushed is suitable for merging.

*Merging* combines the changes from your branch into the `master` branch (or whichever branch you were working off).

After the merge, `master` or the relevant branch will now contain the changes from the branch you were working on.

Creating pull requests and having someone review your work is a *much safer* way of making changes to `master` compared to making the changes directly.

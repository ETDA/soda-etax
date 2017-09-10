# How to contribute !!!
Thanks to every contributors that help making our source code and to make ETDA community project even better than it is
today! Here are the guidelines we'd like you to follow:

## Rule of thumbs !!!
- Please communicate in English :D
- Please have descriptive detail for every Pull Request. 
- Every Pull Request should have test accompanied the code changes
- Please +1 the comment you like rather than post "me too"
- Please comment LGTM if you read the PR and it is good to you so the contributor knows ( LGTM: Looks good to me, Let's get that moving, Let's get this merged :D )
- Please wait for review 1-2 days or 2 LGTM to get merge

## A longer version guide for contributors

 - [Submitting issues & bugs](#issue)
 - [Feature requests](#feature)
 - [Coding Rules](#rules)
 - [Commit Message Guidelines](#commit)
 - [Submitting a Pull Request](#submit)
 - [Got a question or problem ?](#question)
 - [Further Info](#info)


## <a name="issue"></a> Submitting issues & bugs
Before you submit your issue search the archive, maybe your question was already answered.

If your issue appears to be a bug, and hasn't been reported, open a new issue. Help us to maximize
the effort we can spend fixing issues and adding new features, by not reporting duplicate issues.
Providing the following information will increase the chances of your issue being dealt with
quickly:

* **Overview of the Issue** - if an error is being thrown a non-minified stack trace helps
* **Motivation for or Use Case** - explain why this is a bug for you
* **Language and Operating System** - is this a problem with all browsers or only specific ones?
* **Reproduce the Error** - provide a live example or an unambiguous set of steps.
* **Related Issues** - has a similar issue been reported before?
* **Suggest a Fix** - if you can't fix the bug yourself, perhaps you can point to what might be
  causing the problem (line of code or commit)

## <a name="feature"></a> Feature requests

You can request a new feature by submitting an issue to our [GitHub Repository][github].  If you
would like to implement a new feature then consider what kind of change it is:

* **Major Changes** that you wish to contribute to the project should be discussed first on our
  Facebook Group: "ETDA Opensource Community" https://www.facebook.com/groups/etda.opensource.community. so that we can better coordinate our efforts,
  prevent duplication of work, and help you to craft the change so that it is successfully accepted
  into the project.
* **Small Changes** can be crafted and submitted to the [GitHub Repository][github] as a Pull
  Request.
  
## <a name="rules"></a> Coding Rules

To ensure consistency throughout the source code, keep these rules in mind as you are working:

* All features or bug fixes **must be tested** by unit-testing.
* All public API methods **must be documented** .


## <a name="commit"></a> Git Commit Guidelines

We have very precise rules over how our git commit messages can be formatted.  This leads to **more
readable messages** that are easy to follow when looking through the **project history**.

### Commit Message Format
Each commit message consists of a **header**, and a **body**.  The header has a special
format that includes a **type**, a **scope** and a **subject**. The **header** is mandatory and the **scope** of the header is optional.
Any line of the commit message cannot be longer 100 characters! This allows the message to be easier
to read on GitHub as well as in various git tools.

```
<type>(<scope>): <subject>
<BLANK LINE>
<body>
```
### Type
Must be one of the following:

* **feature**: A new feature
* **fix**: A bug fix
* **docs**: Documentation only changes
* **style**: Changes that do not affect the meaning of the code (white-space, formatting, missing
  semi-colons, etc)
* **refactor**: A code change that neither fixes a bug nor adds a feature
* **perf**: A code change that improves performance
* **test**: Adding missing or correcting existing tests
* **core**: Changes to the build process or auxiliary tools and libraries such as documentation
  generation

### Scope
The scope could be anything specifying place of the commit change. For example `schema`,
`processor`, `controller`, `action`, `helper`, etc...

You can use `*` when the change affects more than a single scope.

### Subject
The subject contains succinct description of the change:

* use the imperative, present tense: "change" not "changed" nor "changes"
* don't capitalize first letter
* no dot (.) at the end

### Body
Just as in the **subject**, use the imperative, present tense: "change" not "changed" nor "changes".
The body should include the motivation for the change and contrast this with previous behavior.

### Sample commit message
```
feature(schema): Add automate schema validator

To make it easuer to validate every change in schema, so we add automate schema validator to validate it every time we accept new PR.

```

### Reverts
If the commit reverts a previous commit, it should begin with `revert: `, followed by the header of the reverted commit.
In the body it should say: `This reverts commit <hash>.`, where the hash is the SHA of the commit being reverted.

```
fix(schema): revert:remove redundant element from XML schema

In previous commit we merge a schema with redundant element in main schema,this is fix by revert commits from latest PR and merge.

```

## <a name="submit"></a>Submitting a Pull Request
Before you submit your pull request consider the following guidelines

* Search [GitHub Repository][github] for an open or closed Pull Request
  that relates to your submission. You don't want to duplicate effort.
* Make your changes in a new git branch:

    ```shell
    git checkout -b my-fix-branch master
    ```

* Fix the issue/implement your code **including appropriate test cases**.
* Follow our [Coding Rules](#rules).
* Run the full test suite, if any.
* Commit your changes using a descriptive commit message that follows our
  [commit message conventions](#commit) . Adherence to the [commit message conventions](#commit) is required,
  because release notes are automatically generated from these messages.

    ```shell
    git commit -a
    ```
  Note: the optional commit `-a` command line option will automatically "add" and "rm" edited files.

* Push your branch to GitHub:

    ```shell
    git push origin my-fix-branch
    ```

In GitHub, send a pull request to `upstream:master`.
If we suggest changes, then:

* Make the required updates.
* Re-run the test suite to ensure tests are still passing.
* Commit your changes to your branch (e.g. `my-fix-branch`).
* Push the changes to your GitHub repository (this will update your Pull Request).

If the PR gets too outdated we may ask you to rebase and force push to update the PR:

```shell
git rebase master -i
git push origin my-fix-branch -f
```

_WARNING: Squashing or reverting commits and force-pushing thereafter may remove GitHub comments
on code that were previously made by you or others in your commits. Avoid any form of rebasing
unless necessary._

That's it! Thank you for your contribution!

#### After your pull request is merged

After your pull request is merged, you can safely delete your branch and pull the changes
from the main (upstream) repository:

* Delete the remote branch on GitHub either through the GitHub web UI or your local shell as follows:

    ```shell
    git push origin --delete my-fix-branch
    ```

* Check out the master branch:

    ```shell
    git checkout master -f
    ```

* Delete the local branch:

    ```shell
    git branch -D my-fix-branch
    ```

* Update your master with the latest upstream version:

    ```shell
    git pull --ff upstream master
    ```




## <a name="question"></a> Got a question or problem 

If you found something ambigous or facing problem in contributing process please go ahead to Facebook group: "ETDA Opensource Community" https://www.facebook.com/groups/etda.opensource.community and leave your question there. Our community leader and/or community facilitator will help you to find solution to your question/problem.

**Please see the [Submission Guidelines](#submit) below.**

## <a name="info"></a> Further Information
Not yet defined
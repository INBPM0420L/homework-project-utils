homework-project-utils
======================

Utility classes for the Software Engineering and Technologies homework project.

If your project is about a logic puzzle, the class implementing the state-space representation must implement the [puzzle.State](https://inbpm0420l.github.io/homework-project-utils/javadoc/puzzle/State.html) interface.

If your project is about a two-player game, the class implementing the state-space representation must implement the [game.State](https://inbpm0420l.github.io/homework-project-utils/javadoc/game/State.html) interface.

## Documentation

* [Javadoc](https://inbpm0420l.github.io/homework-project-utils/javadoc/)

## Download

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>hu.unideb.inf</groupId>
    <artifactId>homework-project-utils</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

The artifact can be downloaded from [GitHub Packages](https://docs.github.com/en/packages) that requires the following element to be added to your `pom.xml` file:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/INBPM0420L/homework-project-utils</url>
    </repository>
</repositories>
```

Note that GitHub Packages requires authentication using a personal access token (classic) that can be created [here](https://github.com/settings/tokens).

> [!IMPORTANT]
> You must create a personal access token (PAT) with the `read:packages` scope.

Finally, you need a `settings.xml` file with the following content to store your PAT: 

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>github</id>
            <username><!-- Your GitHub username --></username>
            <password><!-- Your GitHub personal access token (classic) --></password>
        </server>
    </servers>
</settings>
```

The `settings.xml` file must be placed in the `.m2` directory in your home directory, i.e., in the same directory that stores your local Maven repository.

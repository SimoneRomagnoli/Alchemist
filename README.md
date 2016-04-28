# Alchemist

Alchemist is a simulator built upon the kinetic Monte Carlo idea, but heavily enriched in order to work as a developing platform for pervasive computing. Please refer to [the Alchemist main website][Alchemist].


## Build Status
[![Build Status](https://drone.io/github.com/AlchemistSimulator/Alchemist/status.png)](https://drone.io/github.com/AlchemistSimulator/Alchemist/latest)

## Import via Maven / Gradle

Alchemist is available on Maven Central. You can import all the components by importing the `it.unibo.alchemist:alchemist` artifact.

### Maven

Add this dependency to your build

```xml
<dependency>
    <groupId>it.unibo.alchemist</groupId>
    <artifactId>alchemist</artifactId>
    <version>2.3.1</version>
</dependency>
```

### Gradle

Add this dependency to your build (change the scope appropriately if you need Alchemist only for runtime or testing)

```groovy
compile 'it.unibo.alchemist:alchemist:2.3.1'
```

### Importing a subset of the modules

If you do not need the whole Alchemist machinery but just a sub-part of it, you can restrict the set of imported artifacts by using as dependencies the modules you are actually in need of.

### Javadocs

Javadocs for latest nightly build is available [here][Javadoc]. Please note that such documentation may be not in sync with the version you are importing in your project, even if you point at the latest release, since such documention is re-generated by our nightly build system and is updated with the latest commit (if the build passes).
The documentation for any specific version of this library is released on Maven Central along with the code and the compiled jar file.


### Downloads

The latest artifacts for this project can be downloaded [here][Jars]. This page includes three artifacts:
* A jar file containing the compiled class files
* A jar file containing the source code
* A jar file containing the generated javadoc

Complete build reports can be downloaded [here][reports]

## Notes for Developers


### Importing the project
The project has been developed using Eclipse, and can be easily imported in such IDE.

#### Recommended configuration
* Download [the latest Eclipse for Java SE developers][eclipse]. Arch Linux users can use the package extra/eclipse-java, which is rather up-to-date.
  * The minimum version required for a smooth import is Eclipse Mars.1, which integrates Gradle Buildship
  * Previous Eclipse versions are okay, provided that the Gradle Buildship plugin is installed
* Install the code quality plugins:
  * In Eclipse, click Help -> Eclipse Marketplace...
  * In the search form enter "findbugs", then press Enter
  * One of the retrieved entries should be "FindBugs Eclipse Plugin", click Install
  * Click "< Install More"
  * In the search form enter "pmd", then press Enter
  * One of the retrieved entries should be "pmd-eclipse-plugin". **Do not** confuse it with eclipse-pmd. click Install
  * In the search form enter "checkstyle", then press Enter
  * One of the retrieved entries should be "Checkstyle Plug-in" with a written icon whose text is "eclipse-cs", click Install
  * Click "Install Now >"
  * Wait for Eclipse to resolve all the features
  * Click "Confirm >"
  * Follow the instructions, accept the license, wait for Eclipse to download and install the product, accept the installation and restart the IDE.

#### Import Procedure
* Install git on your system, if you haven't yet
* Pull up a terminal, and `cd` to the folder where you want the project to be cloned (presumably, your Eclipse workspace)
* Clone the project with `git clone git@github.com:AlchemistSimulator/alchemist.git`
  * If you are a Windows user, you might find easier to import via HTTPS: `git clone https://github.com/AlchemistSimulator/Alchemist.git`
* Open Eclipse
* Click File -> Import -> Gradle -> Gradle Project -> Next
* Select the project root directory, namely, the `Alchemist` folder located inside the folder where you have cloned the repository. Do not point to the folder containing this `README.md` file, but to the `Alchemist` folder on the same level.
* Next
* Make sure that "Gradle weapper (recommended)" is selected
* Next
* Wait for Eclipse to scan the project, then make sure that the Gradle project structure can be expanded, and contains an external `alchemist` project and many `alchemist-*` subprojects. If it does not, you have pointed to the wrong folder while importing, go back and select the correct one.
* Finish
* When asked about the existing Eclipse configuration, select "Keep" (so that all the default development options are imported)
* The projects will appear in your projects list.
* Checkstyle, PMD and FindBugs should be pre-configured.

### Developing the project
Contributions to this project are welcome. Just some rules:
0. We use [git flow](https://github.com/nvie/gitflow), so if you write new features, please do so in a separate `feature-` branch.
0. We recommend forking the project, developing your stuff, then contributing back via pull request directly from GitHub
0. Commit often. Do not throw at me pull requests with a single giant commit adding or changing the world. Split it in multiple commits and request a merge to the mainline often.
0. Do not introduce low quality code. All the new code must comply with the checker rules (that are quite strict) and must not introduce any other warning. Resolutions of existing warnings (if any is present) are very welcome instead.


#### Building the project
While developing, you can rely on Eclipse to build the project, it will generally do a very good job.
If you want to generate the artifacts, you can rely on Gradle. Just point a terminal on the project's root and issue

```bash
./gradlew
```

This will trigger the creation of the artifacts the executions of the tests, the generation of the documentation and of the project reports.


#### Release numbers explained
We release often. We are not scared of high version numbers, they are just numbers in the end.
We use a three levels numbering:

* **Update of the minor number**: there are some small changes, and no backwards compatibility is broken. Probably, it is better saying that there is nothing suggesting that any project that depends on this one may have any problem compiling or running. Raise the minor version if there is just a bug fix, or a code improvement, such that no interface, constructor, or non-private member of a class is modified either in syntax or in semantics. Also, no new classes should be provided.
	* Example: switch from 1.2.3 to 1.2.4
* **Update of the middle number**: there are changes that should not break any backwards compatibility, but the possibility exists. Raise the middle version number if there is a remote probability that projects that depend upon this one may have problems compiling if they update. For instance, if you have added a new class, since a depending project may have already defined it, that is enough to trigger a mid-number change. Also updating the version ranges of a dependency, or adding a new dependency, should cause the mid-number to raise. As for minor numbers, no changes to interfaces, constructors or non-private member of classes are allowed. If mid-number is update, minor number should be reset to 0.
	* Example: switch from 1.2.3 to 1.3.0
* **Update of the major number**: *non-backwards-compatible change*. If a change in interfaces, constructors, or public member of some class have happened, a new major number should be issued. This is also the case if the semantics of some method has changed. In general, if there is a high probability that projects depending upon this one may experience compile-time or run-time issues if they switch to the new version, then a new major number should be adopted. If the major version number is upgraded, the mid and minor numbers should be reset to 0.
	* Example: switch from 1.2.3 to 2.0.0


[Alchemist]: http://alchemist-simulator.github.io/
[Javadoc]: http://hephaestus.apice.unibo.it/alchemist-build/Alchemist/build/docs/javadoc/
[Jars]: http://hephaestus.apice.unibo.it/alchemist-build/Alchemist/build/libs/
[reports]: http://hephaestus.apice.unibo.it/alchemist-build/Alchemist/build/reports/buildDashboard/
[eclipse]: https://eclipse.org/downloads/

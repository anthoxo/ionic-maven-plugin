# ionic-maven-plugin
A Maven plugin to help your Ionic implements in a maven project.

### What is this plugin meant to do ?
* Install the latest version of Ionic and your dependencies in package.json
* Let you separate source and compiled code by using these plugin goals.

## Requirements
* _Maven 3_ and _Java 8_
* _Node 8_ and _Npm 5_

## Installation
To make sure you use correctly this Maven plugin, please follow these steps :

* You have to clone this project in your computer
```
cd PATH_WHERE_PLUGIN_WILL_BE_CLONED
git clone https://github.com/anthoxo/ionic-maven-plugin.git
```

* Then, install this plugin in your maven repository
```
cd ionic-maven-plugin
mvn clean install
```

* In your Ionic project, include the plugin as a dependency in pom.xml
```
<plugins>
    <plugin>
        <groupId>com.maven.ionic.project</groupId>
        <artifactId>ionic</artifactId>
        <version>1.0-SNAPSHOT</version>
        <configuration>
            <arguments>${args}</arguments>
            <workingDirectory>${workingDirectory}</workingDirectory>
            <platform>${platform}</platform>
            <component>${component}</component>
            <name>${component.name}</name>
            <npmGoal>${npm.goal}</npmGoal>
            <force>${force}</force>
        </configuration>
        ...
    </plugin>
    ...
</plugins>
```

* In your Ionic project, include theses resources in pom.xml
```
<resources>
    <resource>
        <directory>${project.basedir}</directory>
        <targetPath>${project.build.directory}</targetPath>
        <includes>
            <include>**/**</include>
        </includes>
        <excludes>
            <exclude>target/</exclude>
            <exclude>config.xml</exclude>
        </excludes>
        <filtering>false</filtering>
    </resource>
    <resource>
        <directory>${project.basedir}</directory>
        <targetPath>${project.build.directory}</targetPath>
        <includes>
            <include>config.xml</include>
        </includes>
        <filtering>true</filtering>
    </resource>
</resources>
```

**Notice:** it is the most important things to add in your Maven project

## Usage
* [Preparing your environment Ionic](#preparing-your-environment-ionic)
* [Clean you project](#clean-your-project)
* [Generate a component](#generate-a-component)
* [Serve your project](#serve-your-project)
* [Build your project](#build-your-project)
* [Run your project](#run-your-project)
* [Run a NPM goal](#run-a-npm-goal)

### Preparing your environment Ionic
During this process, the plugin checks if node and npm are installed :
* if it is the case, then it runs ```npm install``` in your build directory
* if it isn't the case, then it throws an error.

You can add this code in your pom.xml to process the installation during maven phase (mvn install for example)
```
<execution>
    <id>ionic install</id>
    <goals>
        <goal>install</goal>
    </goals>
    <!-- optionnal : default phase = process-resources -->
    <phase>process-resources</phase>
</execution>
```

### Clean your project

Before doing anything, you have to add this plugin to disable the default behavior :
```
<plugin>
    <artifactId>maven-clean-plugin</artifactId>
    <version>2.4.1</version>
    <executions>
        <execution>
            <id>default-clean</id>
            <phase>none</phase>
        </execution>
    </executions>
</plugin>
```
When you run ```mvn clean```, it will delete :
* in your root file :
    * node_modules
    * platforms
    * plugins
    * www
* in target folder, everything else :
    * node_modules
    * platforms
    * plugins
    * www

If you run ```mvn clean -Dforce```, it will delete target folder.

Please you have to add this code in your pom.xml to use properly this maven plugin :
```
<execution>
    <id>ionic clean</id>
    <goals>
        <goal>clean</goal>
    </goals>
</execution>
```

### Generate a component

To generate a component, and to use this command
```
ionic generate page login
```
with this plugin, you can use this command :
```
mvn ionic:generate -Dcomponent=page -Dname=login
```
* -Dcomponent is mandatory. This option is to set your type component.
* -Dname is mandatory. This option is to set your component name.
* -Dargs is optional. This option is to add more options for ionic serve.

### Serve your project
To use this command
```
ionic serve
```
with this plugin, you can use this command :
```
mvn ionic:serve
```
* -DworkingDirectory is optional. This option is to set where you want to serve your application. Default value is build directory (target)
* -Dargs is optional. This option is to add more options for ionic serve.

### Build your project
To use this command
```
ionic cordova build android
```
with this plugin, you can use this command :
```
mvn ionic:build -Dplatform=android
```
* -Dplatform is mandatory. This option is to set your build platform.
* -DworkingDirectory is optional. This option is to set where you want to serve your application. Default value is build directory (target)
* -Dargs is optional. This option is to add more options for ionic serve.

### Run your project
To run your project and use this command
```
ionic cordova run android
```
with this plugin, you can use this command :
```
mvn ionic:run -Dplatform=android
```
* -Dplatform is mandatory. This option is to set your build platform.
* -DworkingDirectory is optional. This option is to set where you want to serve your application. Default valu is build directory (target)
* -Dargs is optional. This option is to add more options for ionic serve.

### Run a NPM goal
In Ionic, you can use NPM goal. This plugin allows you to use goal that define in your package.json.

To use a NPM goal like this 
```
npm run goal
```
with this plugin, you can use this command :
```
mvn ionic:npmRun -Dgoal=goal
```
* -Dgoal is mandatory. This option is to set your npm goal.
* -DworkingDirectory is optional. This option is to set where you want to serve your application. Default valu is build directory (target)
* -Dargs is optional. This option is to add more options for ionic serve.

Moreover, you can add an execution in your pom.xml like this :
```
<execution>
    <id>ionic npmRun</id>
    <goals>
        <goal>npmRun</goal>
    </goals>
    <phase>process-resources</phase>
    <configuration>
        <npmGoal>goal</npmGoal>
        <arguments>-- --lab</arguments>
    </configuration>
</execution>
```


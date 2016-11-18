# SikuliServer
Java application with an embedded jetty server that exposes the core functionality of Sikuli through a REST API to execute Sikuli commands remotely on any platform. Based on https://sourceforge.net/projects/sikulirestapi/ project.

Used by SikuliClient


## Execution
This project does not require the installation of SikuliX on the computer.

### Windows
In order to execute this project in Windows, you'll need to switch between Java version (set it to JRE 6 (x86) in JAVA_HOME at environment variables)
### macOS and Unix based systems
In these platforms you can keep the x64 version of Java

### How to initialize the Sikuli Server?
At command-line/terminal, move to the path that contain the sikulirestapi.jar and execute the following:

```bash
java -jar sikulirestapi-1.0.jar 9000
```

Note: You can find the sikulirestapi-1.0.jar at /new/ folder of the project.


## Maven building
To resolve the dependencies through Maven, do the following sequentially:

```bash
mvn install:install-file -Dfile=C:\SikuliServer\target\sikulirestapi-1.0.jar -DpomFile=C:\SikuliServer\target\com.googlecode.addjars.mojo.AddJarsMojo\pom.xml
```

and after:

```bash
mvn install:install-file -Dfile=C:\SikuliServer\target\sikulirestapi-1.0.jar -DpomFile=C:\SikuliServer\sikulirestapi-code\pom.xml
```


### Notes
1 - After building this application you'll need to perform the merge between the package that contains the Mac dylib's from SikuliX official project (located at new\sikulixlibs\mac\libs64\) and the generated sikulirestapi.jar so that this project work properly in MacOS platform.

2 - To build this project you'll need a x64 version of JDK (preferably version 8)

# SikuliServer
Java application with an embedded jetty server that exposes the core functionality of Sikuli through a REST API to execute Sikuli commands remotely on any platform. Based on https://sourceforge.net/projects/sikulirestapi/ project.

Used by SikuliClient


## Maven building
To resolve the dependencies through Maven:

```bash
mvn install:install-file -Dfile=C:\SikuliServer\target\sikulirestapi-1.0.jar -DpomFile=C:\SikuliServer\target\com.googlecode.addjars.mojo.AddJarsMojo\pom.xml

and

```bash
mvn install:install-file -Dfile=C:\SikuliServer\target\sikulirestapi-1.0.jar -DpomFile=C:\SikuliServer\sikulirestapi-code\pom.xml
```

### Note
After building this application you'll need to perform the merge between the package that contains the Mac dylib's from SikuliX official project (located at sikulixlibs\mac\libs64\) and the generated sikulirestapi.jar in order to this project work properly in MacOS platform.

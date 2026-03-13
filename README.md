NetP: A BeepBeep palette for network packets
============================================

This project is an extension to the [BeepBeep
3](https://liflab.github.io/beepbeep-3), event stream processing engine,
called a *palette*, that provides functionalities for processing network
captures.

Building this palette
---------------------

To compile the palette, make sure you have the following:

- The Java Development Kit (JDK) to compile. The palette complies
  with Java version 6; it is probably safe to use any later version.
- [Ant](http://ant.apache.org) to automate the compilation and build process

The palette also requires the following Java libraries:

- The latest version of [BeepBeep 3](https://liflab.github.io/beepbeep-3)
- The latest version of [JNetPcap](https://sourceforge.net/projects/jnetpcap)

These dependencies can be automatically downloaded and placed in the
`dep` folder of the project by typing:

    ant download-deps

From the project's root folder, the sources can then be compiled by simply
typing:

    ant

This will produce a file called `netp.jar` in the folder. This file
is *not* runnable and stand-alone. It is meant to be used in a Java project
alongside `beepbeep-3.jar`

## Native dependencies (jNetPcap 1.x)

This project relies on the legacy **jNetPcap 1.x** library, which uses JNI and therefore requires native libraries to be installed manually.  
Unlike modern Java libraries, simply adding the `.jar` file to the classpath is not sufficient.

You must ensure that:

1. the native jNetPcap library is visible to the JVM  
2. the system packet capture library (`libpcap` / WinPcap / Npcap) is installed  
3. the architecture of Java and the native library match (32-bit vs 64-bit)

The instructions below describe the minimal setup for each operating system.

### Linux (Ubuntu / Debian)

Install the libpcap runtime:

```bash
sudo apt install libpcap-dev
```

Place the native library somewhere visible to the dynamic loader, for example:

```bash
/usr/lib/libjnetpcap.so
```

or use a custom directory and set the library path:

```bash
export LD_LIBRARY_PATH=/path/to/jnetpcap:$LD_LIBRARY_PATH
java -Djava.library.path=/path/to/jnetpcap ...
```

You can verify that all dependencies are present with:

```bash
ldd libjnetpcap.so
```

No line should contain `not found`.

### Windows

jNetPcap depends on the WinPcap / libpcap runtime.  
The recommended solution today is to install **Npcap** in WinPcap compatibility mode.

1. Download and install Npcap  
   https://npcap.com/

2. During installation, enable:

```
Install Npcap in WinPcap API-compatible Mode
```

This installs the required system libraries:

```
wpcap.dll
Packet.dll
```

3. Make sure `jnetpcap.dll` is visible to the JVM.

Possible options:

- Put it in the same directory as the program
- Add its folder to `PATH`
- Use:

```bash
java -Djava.library.path=C:\path\to\native ...
```

Avoid copying files into `System32` unless strictly necessary.

### macOS

macOS already provides `libpcap`, but the JNI library must still be visible.

Place:

```
libjnetpcap.dylib
```

in a directory such as:

```
/usr/local/lib
```

or run Java with:

```bash
export DYLD_LIBRARY_PATH=/path/to/jnetpcap:$DYLD_LIBRARY_PATH
java -Djava.library.path=/path/to/jnetpcap ...
```

Recent macOS versions restrict dynamic library loading; using
`java.library.path` is usually more reliable than modifying system folders.


### Architecture mismatch

The native library and the JVM must have the same architecture.

Check Java:

```bash
java -version
```

Check the native library (Linux / macOS):

```bash
file libjnetpcap.so
```

If Java is 64-bit, the native library must also be 64-bit.

Most old jNetPcap builds are 32-bit, so using a 32-bit JVM may be required.

### Why this manual setup is required

jNetPcap 1.x predates modern Java foreign-function APIs and relies on JNI.  
For pedagogical purposes this project keeps that version, which makes the
dependency setup more manual than with recent libraries.


## Note about newer jNetPcap versions

Recent versions of jNetPcap no longer rely on handwritten JNI wrappers and instead use the **Foreign Function & Memory API** introduced in recent Java versions.

The architecture becomes:

```
Java → Foreign Function API → libpcap → operating system
```

instead of:

```
Java → JNI → jnetpcap native wrapper → libpcap → operating system
```

However, even with these newer versions:

- `libpcap` (Linux/macOS) or `Npcap` (Windows) must still be installed
- a recent Java version (21–22+) is required
- additional JVM flags such as `--enable-native-access` may be needed

For this project, the older **1.x** library is used because it remains simple,
stable, and adequate for pedagogical exercises involving PCAP parsing.

Contributors
------------

The first version of this palette has been written in 2016 by [Pierre-Louis
Faure](https://www.linkedin.com/in/plfaure) and [Théo
Ménard](https://www.linkedin.com/in/theomenard). Further tweaks have been
added by [Sylvain Hallé](https://leduotang.ca/sylvain), Full Professor at
[Université du Québec à Chicoutimi](http://www.uqac.ca) and head of
[Laboratoire d'informatique formelle](http://liflab.ca) (LIF).

<!-- :maxLineLen=76: -->
<div align="center">
<img src="assets/Centurion.png" width="250">
</div>

-----------------

# Geo-Studios: Powerful Java Development Kit

## What is it?

**Centurion** is to be a Java Development Kit to supply the user with a huge library, and a functional tool
to use when developing java projects, programs, and much more! Centurion is starting to be built right now 
and is going to take many months to craft a functional installable program for its users.

## Table of Contents

- [Main Features](#main-features)
- 

## Main Features
A few things that Centurion can do:

 - A cipher that boasts support for the widely acclaimed [Blowfish](https://github.com/GeoStudios/Centurion/blob/main/src/ja/core/net/geostudios/crypto/provider/ChaCha20Cipher.java) algorithm in feedback mode, which conforms to the standards set forth by [RFC 7539](https://datatracker.ietf.org/doc/html/rfc7539). For an in-depth understanding of this cutting-edge technology, I would highly recommend perusing this link. Furthermore, the cipher leverages the CTS mode that extends the power of the [CipherBlockChaining](https://github.com/GeoStudios/Centurion/blob/main/src/ja/core/net/geostudios/crypto/provider/CipherFeedback.java) class, while also utilizing its own unique type of bytes, padding, and [internal engine](https://github.com/GeoStudios/Centurion/blob/main/src/ja/core/net/geostudios/crypto/provider/CipherCore.java) with symmetric algorithms, all of which make it a formidable player in the world of modern cryptography.
 - A [console](https://github.com/GeoStudios/Centurion/blob/main/src/ja/core/ja/io/Console.java) that can access character based methods to use in the current virtual machine. This console can also start even whe the interactive command line does not exist, and is typically connected to the keyboard and display when it launches. The read and write operations are synchronized to guarantee the atomic completion of critical operations and will invoke several methods. 

## Contributions

If you want to contribute to this repository, you can make a pull request. We have lots of classes and files
that can be enhanced so much, and also have so much potential. 

If you are optimizing some sort of class, add your list to the @author in the class description. <br>
If you are adding a new class, add a description of what that file does. And your name if you 
want to be credited. <br>

If you really want to contribute, add something you think should be inside, if you want to be a project 
lead for a topic, contact M4x(M4ximumpizza#4712) on discord. There are plenty of stuff to add.

## Devlopers

[M4ximumpizza](https://github.com/M4ximumPizza) is the Project Director. </br>
[KaranpreetRaja](https://github.com/KaranpreetRaja) is the secondary dev on this project

## Installation

Download the Version Jar you want, then put it into any external library inside your code editor. (It will
soon be an installable JDK for the user).

### License

Centurion is licensed under GNU Public License. For more info, please see the [license file](https://github.com/GeoStudios/Centurion/blob/main/License.rtf)

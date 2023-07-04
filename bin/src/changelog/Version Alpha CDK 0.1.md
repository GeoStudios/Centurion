# Alpha CDK-0.1

This is the first version of Centurion with functional classes for the user to use.

## Classes?

Centurion has a total of 6 functional classes including <br>

    -ArrayByteInputStream 
    -Bit <
    -DataOutput 
    -Flushable 
    -IOReputation
    -StreamInput 

### ArrayByteInputStream

This class contains an internal buffer that then contains bytes that are used 
for reading the stream. It uses a counter to log the next byte that id read. 

Closing it has no effect due to the methods can be called after the stream has 
been closed.

### Bit 

A method to pack and unpack utility primitive values using big-endian byte ordering 
in input and output byte arrays. {@code a}

### DataOutput

A interface that enables conversion of data from Java primitive types to a sequence 
of bytes, which can then be written to a binary stream.

### Flushable

Soon to be a way to flush bytes faster. (Coming in version 0.2).

### IOReputation

This class represents I/O reputations (exceptions) that occur as a result of failed or interrupted 
I/O operations.

### StreamInput

An input stream that will come in version 0.2 that makes inputs into a stream like manor.

## Contributers
    -Logan Abernathy

Version Alpha CDK-0.2 is coming on July 25th, with several new implemations and way more classes.
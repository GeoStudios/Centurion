# Alpha CDK-0.2

Centurion has now 4 more classes including

    - FilterWriter
    - Writer
    - Reader
    - ReaderBuffer

### FilterWriter

This code represents an
abstract class, {@code 
FilterWriter}, that 
facilitates writing 
filtered character 
streams. Its default 
methods pass requests to 
the contained stream, and 
subclasses can customize 
these methods while adding 
extra functionality if 
required.

### Writer

This is an abstract class designed 
for writing to character streams. 
The required methods that a subclass 
must implement are write(char[], 
int, int), flush(), and close(). 
While most subclasses are expected 
to override some of the provided 
methods to enhance efficiency and 
offer additional functionality, 
if needed.

### Reader

This abstract class forms the basis 
for reading character streams. 
Subclasses are mandated to implement 
the read(char[], int, int) and close()
methods, and have the option to 
override additional methods provided in 
this class. This design empowers subclasses 
to optimize efficiency, introduce 
supplementary functionality, or achieve a 
combination of both, offering extensive 
customization and performance optimization 
possibilities based on specific requirements.

### ReaderBuffer

BufferedReader performs efficient reading of 
characters, arrays, and lines by intelligently 
buffering the input from a character-input stream. 
It allows flexibility in specifying buffer size or 
utilizing a default size suitable for most purposes. 
Wrapping a BufferedReader around Readers involving 
potentially expensive read() operations, such as 
FileReaders and InputStreamReaders, optimizes 
performance by minimizing overhead and improving
efficiency. Additionally, substituting 
DataInputStream instances with BufferedReader enables e
asy localization and compatibility with different 
languages and character encodings, enhancing 
versatility and adaptability.

## Changes from last version
- Added Copyright to everyfile
- Added a reader to the ArrayByteInputStream class 
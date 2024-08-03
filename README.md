# Multithreaded Web Server
This is a basic non-blocking, multi-threaded web server written in Java, capable of handling multiple client requests concurrently.. It uses the native ServerSocket and Socket classes. In the case of HTTP request, the server uses a buffer which allows for more efficient responses and writing to clients' output stream. Also, it uses a thread pool to manage incoming requests, ensuring efficient use of system resources and improved responsiveness. At the Moment, it only serves a GET Request.


### A Basic Design of Multithreaded Server
![MultiThreaded Server UML](https://github.com/user-attachments/assets/57854008-7ddf-4ce6-957d-8b7f30046f00)

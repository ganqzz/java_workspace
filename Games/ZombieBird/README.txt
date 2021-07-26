This sample project accompanies the book 
"The Beginner's Guide to Android Game Development" by James S. Cho.

= Notes =

1. There is no MenuState in this sample. The MenuState has been combined with the PlayState to allow a direct transition.

2. The game resolution does not equal the size of the window (screen resolution). Instead, we must
   scale the gameImage to fit the window while double buffering. This concept is similar to how
   our Android game development framework behaves.

3. The following resource is a good place to start thinking about the logic for the game:
   http://www.kilobolt.com/day-1-flappy-bird---an-in-depth-analysis.html
   
   Although I wrote the above for a different game development framework (libGDX), most of the
   concepts and code have been reused.

For more information, please see:
http://jamescho7.com/book

Adam Farnsworth
Myles Adams

###### **a)** A brief description of the project. Here, I’m looking for a short description: probably 1 sentence, 2-3 at most.
The project is a flash card simulator for studying.  It can create, cards and help to learn new material.

###### b)  a set of user stories (as a X I can Y so that Z) that describe what the current software in its current state can do.  First, review how User Stories are supposed to be written.
As a user I can create cards with custom input so that I can enter questions and answers.  As a user i can shuffle the deck, flip cards to the front and back, view the next card in the deck, save decks of cars to the loader so that I can study efficiently.

###### c) a brief assessment of whether the software runs or not. If it runs, briefly describe what it does
The software does run, but has problems with sound.  When it opens there are buttons to flip the card over, go to the next card, create a new card, go to the main menue and save.

###### d) a set of user stories (at least 2, but you are encouraged to write up to 4 or more if you can, as many as you think is reasonable) about features that COULD be added to the software to make it more useful, fun, better, etc.  Again, review the preferred way to write User Stories.
As a user I can add a better gui for a more enjoyable study experence so that more studying can get done.  As a user I can add cross platform capability so that more users can have access to it.  As a user I can add touch screen capability to it for modern divices.

###### e) An assessment of the current quality of the README.md. What information could be added to make it easier for the next generation of folks maintaining this code to use the software, and/or maintain the software?
The readme could be more descriptive of the program.  It may be because the program does not have much to it, but I belive there is more to this program then the readme says.

###### f) An assessment of the current state of the build.xml file if applicable, or if the project has been converted to Maven or Gradle, note this. If it’s based on Ant, Are there targets that need descriptions? Is there old legacy JWS stuff that needs to be removed? (More on this below). It it’s based on Maven or Gradle, is there sufficient documentation in the README.md that someone new to those tools has the information they need to get started?
ant is well maintained and has little to no work that needs to be done for it, bellow is what's available:
..*clean    Removes the build, javadoc, dist, and temp directories
..*compile  Compiles the project
..*jar      Makes a jar file that runs this project
..*javadoc  Compiles the javadoc and publishes it to a github page
..*run      Runs the project through the main method in FlashCardApplication
..*test     Runs the JUnitTests in the Test classes 

###### g) An assessment of the current “issues”. Are there enough issues that you could earn 1000 points by working on this project? Are the issues clear in terms of what the expectations are?
There are a good amount of issues like being unable to launch with sound.  In addion, there are many features that could be added like the mute button.  There should be enough work to earn 1000 points, and the expectaions are clear.

###### h) A list of additional issues that you may have added, if any. For each, a link to the issue is good enough.
There are many features that can be added to enhance the program like a better gui, cross platform capability, and adding sound that actually works.

###### i) Most important: an assessment of the actual code. Write a bit about how the code is organized. Are the purposes of the classes, and their methods clear? Is it obvious how the classes relate to one another? Is the code easy to read and understand? If you had to give someone else that was going to work on the code just “one screenful of text” to help that programmer get up to speed quickly, what information would you convey?
The classes are named well, and their purposes are to modify: the Deck, Flash card, and Quiz.  They have started on the sound controller as the next classes to implement.  Some of the code is written and commented legibly, other parts need some improvment. In general, the code is pretty easy to understand though, its pretty well self-documented. Most of the class names are good except there is one that I think is poorly named, CreateDeckFrame.java.

###### j) Related to code quality, but factored out into a separate issue because it is so important: how is the test coverage? Are there JUnit tests at all? If so, how much of the project is covered by testing? Are there opportunities to expand test coverage, and if so, how would you go about it?
There is two testing classes, DeckTest.java and FlashCardTest.java. They are not very extensive but seem to cover the basic uses for the Deck and FlashCard. They don't test any of the other classes individually but I guess they just test them through the FlashCard and Deck tests. If those run, then they are assuming that all the other classes that those implement work properly.

# Chess-clock

MY task while taking __System modeling__ course in my university was to design a chess clock based on the specification that is in **en.pdf** file above.

## The chess clock

The task of the chess clock is to specify the time to think for the participants.
Both players (White and Black) have a given amount of time to think, wich the
chess clock displays. The time of the current player constantly decreases. If the
time runs out, the player loses and the clock beeps to indicate the termination of
the game due to timeout. If the current player moves in time, the player should
press the approriate button. As a result the decreasing stops and (depending on
the game’s setup) the time increases by a certain amout of reward time and the
other player comes instantly. The player can use the remaining time (increased
with reward time) to think in the next turn.

## The Homework Software Environment

The main tool I used is called the Yakindu Statechart Tool. It is a tool widely used in industrial projects,
but (as it usually happens to such tools) its installation is not very simple. Please be very careful about version numbers
if you decide to install the tool for yourself.

The software environment that I used for my assignments, and which my testing framework will use when evaluating the submission is:

OpenJDK 11
Eclipse 18-12
Yakindu SCT 3.5.2

##  Yakindu binding

We start buliding the chess clock’s model in Yakindustate modeling tool by
defining the system’s interfaces to the outside world. To represent them in Yakindu
we use interface events and variables (these are also used by the GUI, that
will be provided later for support). Thus, our model can react to a keypress,
and we can display the values of the variables on the screen.

### The result can be seen by running **app.launch**



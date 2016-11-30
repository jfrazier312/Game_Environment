# API Review 
Author: George Bernard - GHB5
Partner: Pratiksha Sharma - ps179

## Part 1
 
### What about your API/design is intended to be flexible?

We have the benefit of never being called by any other group. So our design should be as flexible as the game data class.

### How is your API/design encapsulating your implementation decisions?

We have no "public" API, as no other modules ever directly call us. We only push out an xml file with all of the serializable game data. As such, our 

### How is your part linked to other parts of the project?

We are linked only by depositing a game xml file.

### What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

User input issues don't need exceptions if we construct our input fields properly. We can either monitor user input and in our code construct games properly, or player can throw exceptions for poorly handled inputs.

### Why do you think your API/design is good (also define what your measure of good is)?

I would not say that our API is good, but also I would not say it is bad. We have internal API's which are more fluid than I like,  but since we don't have a public access port, we're allowed creative freedoms.

## Part 2

### What feature/design problem are you most excited to work on?

I'm very excited to build more encapsulated javafx menus for editing specific game state.

### What feature/design problem are you most worried about working on?

Assigning events to interactions / collisions between entities

### What is do you plan to implement this weekend?

Finish and document my sprite editing code

### Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?

Yes.


### Do you have use cases for errors that might occur?
No.
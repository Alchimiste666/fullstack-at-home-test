 1) Make sure you have nodejs latest version. In order to have `yarn` enabled do `corepack enable`.
 2) Run `yarn install` to build the application that displays a list of categories and nominees. Please follow the design in the wireframe below.
 3) Run `yarn start` to start the application.
 4) You should use the API `GET /artists` route to get all the artists from api, and show them in the correct categories with their full name above their picture.
 5) When you click on a nominee (either button or tile), it should highlight the nominee card and save the selections. A user can only select one nominee per category, and we should be able to see all of their selections highlighted. The selected nominee card should follow the style guides below.
 6) Make the layout responsive with at least one breakpoint (your choice as to how it looks on a smaller screen width).
 7) Once the user is finished making their selections, they can click on a submit button that displays a results modal screen. A user can dismiss the modal by clicking on the close button (follow the wireframe below).

## Requirements:
 1) All navigation should happen in the same page
 2) Demonstrate use of React hooks
 3) Demonstrate knowledge of component modularization
 4) Utilize CSS to create the layout of the page. Add hover styles to the items the user is interacting with.
 5) Create components as you feel is best suited for your solution

![Ballot Wireframe](src/take-home-wire.jpg?raw=true "Ballot Wireframe")
![Ballot Success Modal Wireframe](src/take-home-success.jpg?raw=true "Ballot Success Modal Wireframe")

## Styling Guidelines

- Use the "Roboto" Google font   
- Use the following colors   
	- Page background: - ![#0D2436](https://via.placeholder.com/15/0D2436/000000?text=+) `#0D2436`
	- Default/normal font color: ![#FFFFFF](https://via.placeholder.com/15/FFFFFF/000000?text=+) `#FFFFFF`
  - Hover font color: ![#CCCCCC](https://via.placeholder.com/15/CCCCCC/000000?text=+) `#CCCCCC`
	- Nominee card / submit button background: ![#009B86](https://via.placeholder.com/15/009B86/000000?text=+) `#009B86`
  - Selected Nominee card: ![#009B86](https://via.placeholder.com/15/009B86/000000?text=+) `#009B86`
  - Nominee card background hover / submit button background hover: ![#34AC9C](https://via.placeholder.com/15/#34AC9C/000000?text=+) `#34AC9C`
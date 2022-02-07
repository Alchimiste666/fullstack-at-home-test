import './App.css';
import Ballot from './Components/Ballot/Ballot';

function App() {
  // Feel free to remove the contents of the header tag to make more room for your code
  return (
    <div className="App">
      <header className="App-header">
        <img src={'/logo.svg'} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.jsx</code> and <code>src/Components/Ballot/Ballot.jsx</code> and save to reload.
        </p>
      </header>
      <Ballot />
    </div>
  );
}

export default App;

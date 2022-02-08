import "./App.css";
import Ballot from "./components/Ballot";

function App() {
  return (
    <div>
      <nav className="navbar navbar-dark bg-dark">
        <span className="navbar-brand">
          <img id="app-logo" src="/logo.svg" alt="logo" /> Awards 2022
        </span>
      </nav>
      <div id="main-content" className="container">
        <Ballot />
      </div>
    </div>
  );
}

export default App;

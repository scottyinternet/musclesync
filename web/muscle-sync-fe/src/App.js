import logo from './logo.svg';
import './App.css';
import Client from './components/Client'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <Client />
      </header>
    </div>
  );
}

export default App;

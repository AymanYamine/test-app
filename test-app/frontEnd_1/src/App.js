import React, { Component } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import NavBar from "./components/NavBar";

import { BrowserRouter as Router, Route } from "react-router-dom";

import Second from "./components/Second";
class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <NavBar />
          <Route exact path="/" component={Second} />
        </div>
      </Router>
    );
  }
}
export default App;

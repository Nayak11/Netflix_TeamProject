import React, { Component } from 'react';
import Main from './Components/Main';
import logo from './logo.svg';
import {Router} from 'react-router-dom';
import history from './Components/history'

class App extends Component {
  render() {
    return (
      //Use Browser Router to route to different pages
      <Router history={history}>
        <div>
          {/* App Component Has a Child Component called Main*/}
          <Main/>
        </div>
      </Router>
    );
  }
}

export default App;
